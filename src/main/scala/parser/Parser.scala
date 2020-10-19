package parser

import fastparse._, SingleLineWhitespace._
import commands._
import point._
import orientation._
import orientation.Orientation._
import input._

import scala.collection.mutable.ListBuffer

// companion object
object Parser {
  type DirectionsList = List[Command]
}

// parse to correct syntax, but not correct list logic (yet).
class Parser {

  // mutable ListBuffer only visible outside as immutable List
  type DirectionsListBuffer = ListBuffer[Command]
  // @MUTABLE:
  private var _directionsList: DirectionsListBuffer =
    new DirectionsListBuffer()
  def directionsList: Parser.DirectionsList = _directionsList.toList

  def parse(dl: Input.PreParsedDirectionsList): Boolean = {
    def parserPlace[_: P] = 
      P(Commands.Place.toString.!
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
      P(parserPlace
        | Commands.Move.toString.!
        | Commands.Left.toString.!
        | Commands.Right.toString.!
        | Commands.Report.toString.!
        ~ End)

    // @MUTABLE:
    var result = true

    for (command <- dl) {
        fastparse.parse(command, parserCommands(_)) match {
          case Parsed.Success(value, index) => {
            value match {
              case (p: String, x: Int, y: Int, o: String) => 
                _directionsList += Place(new Point(x, y), Orientation.withName(o))
              // @TODO: use enum strings once '.toString' compile
              // error is resolved
              case "MOVE" => _directionsList += Move()
              case "LEFT" => _directionsList += Left()
              case "RIGHT" => _directionsList += Right()
              case "REPORT" => _directionsList += Report()
              case _ => result = false

            } // match
          } // match
          case Parsed.Failure(expected, index, extra) => {
            println(extra.trace().longMsg)
            result = false
          }
          case _ => result = false

        } // match
    } // for

    result

  } // parse
} // Parser
