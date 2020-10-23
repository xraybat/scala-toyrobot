package parser

import fastparse._, SingleLineWhitespace._
import command._
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
      P(Command.Place.!
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
        | Command.Move.!
        | Command.Left.!
        | Command.Right.!
        | Command.Report.!
        ~ End)

    var result = true

    for (command <- dl) {
      fastparse.parse(command, parserCommands(_)) match {
        case Parsed.Success(value, index) =>
          value match {
            case (p: String, x: Int, y: Int, o: String) => 
              _directionsList += Place(new Point(x, y), Orientation.withName(o))

            case Command.Move => _directionsList += Move()
            case Command.Left => _directionsList += Left()
            case Command.Right => _directionsList += Right()
            case Command.Report => _directionsList += Report()
            case _ => result = false

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
