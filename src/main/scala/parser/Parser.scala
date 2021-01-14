package toyrobot.parser

import fastparse._, SingleLineWhitespace._

import toyrobot.command._
import toyrobot.point._
import toyrobot.orientation._
import toyrobot.orientation.Orientation._
import toyrobot.input._
import toyrobot.input.Input.PreParsedDirectionsList
import toyrobot.directions._
import toyrobot.directions.Directions.DirectionsList
import toyrobot.directions.Directions.DirectionsListBuffer

import util.control.Breaks._

// parse to correct syntax, but not correct list logic (yet).
class Parser {

  // @MUTABLE:
  private var _directionsList: DirectionsListBuffer = new DirectionsListBuffer()

  def parse(dl: PreParsedDirectionsList): Option[DirectionsList] = {
    def parserPlaceRobot[_: P] = 
      P(Command.keywordPlaceRobot.!
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
        | Command.keywordPlaceObject.!
        | Command.keywordMove.!
        | Command.keywordLeft.!
        | Command.keywordRight.!
        | Command.keywordReport.!
        ~ End)

    var result = true

    for (command <- dl)
      breakable {
        fastparse.parse(command, parserCommands(_)) match {
          case Parsed.Success(value, index) =>
            value match {
              case (p: String, x: Int, y: Int, o: String) => 
                _directionsList += PlaceRobot(Point(x, y), Orientation.withName(o))

              case Command.keywordPlaceObject => _directionsList += PlaceObject()
              case Command.keywordMove => _directionsList += Move()
              case Command.keywordLeft => _directionsList += Left()
              case Command.keywordRight => _directionsList += Right()
              case Command.keywordReport => _directionsList += Report()
              case _ => result = false

            } // match
          case Parsed.Failure(expected, index, extra) => {
            println(extra.trace().longMsg)
            result = false
          }
          case _ => result = false

        } // match

        // don't keep parsing
        if (!result) break

      } // breakable

    if (result) Some(_directionsList.toList) else None

  } // parse
} // Parser
