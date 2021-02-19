package toyrobot.parser

import fastparse._, SingleLineWhitespace._

import toyrobot.command._
import toyrobot.point._
import toyrobot.orientation._
import toyrobot.orientation.Orientation._
import toyrobot.input._
import toyrobot.input.Input.PreParsedDirectionsList
import toyrobot.mylist.directions._

import util.control.Breaks._
import scala.util.{Try,Success,Failure }

case class ParseException(private val message: String = "", private val cause: Throwable = None.orNull) extends Exception(message, cause)

// parse to correct syntax, but not correct list logic (yet).
class Parser {

  private val _directions: Directions = new Directions

  def parse(directions: PreParsedDirectionsList): Try[Directions] = {
    def parserPlaceRobot[_: P] = 
      P(Command.KeywordPlaceRobot.!
        ~ CharIn("0-9").rep(1).!.map(_.toInt)
          ~ ","
          ~ CharIn("0-9").rep(1).!.map(_.toInt)
        ~ ","
        ~ (Orientation.North.toString.!
          | Orientation.East.toString.!
          | Orientation.South.toString.!
          | Orientation.West.toString.!)
        ~ End)
    def parserCommands[_: P] = 
      P(parserPlaceRobot
        | Command.KeywordPlaceObject.!
        | Command.KeywordMove.!
        | Command.KeywordLeft.!
        | Command.KeywordRight.!
        | Command.KeywordReport.!
        ~ End)

    var result = true
    var error = ""

    for (command <- directions) breakable {
      fastparse.parse(command, parserCommands(_)) match {
        case Parsed.Success(value, index) =>
          value match {
            case (p: String, x: Int, y: Int, o: String) => 
              _directions.add(PlaceRobot(Point(x, y), Orientation.withName(o)))

            case Command.KeywordPlaceObject => _directions.add(PlaceObject())
            case Command.KeywordMove => _directions.add(Move())
            case Command.KeywordLeft => _directions.add(Left())
            case Command.KeywordRight => _directions.add(Right())
            case Command.KeywordReport => _directions.add(Report())
          }
        case Parsed.Failure(expected, index, extra) =>
          error += extra.trace().longMsg + '\n'
          result = false
      }

      // don't keep parsing
      if (!result) break

    } // for-breakable

    if (result) Success(_directions) else Failure(ParseException(error))

  } // parse
} // Parser
