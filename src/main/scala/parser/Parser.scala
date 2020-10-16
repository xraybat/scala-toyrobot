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
  type CleanDirectionsList = List[Command]
}

class Parser {
  // @TODO: remove, and rely only on Robot.inPlace()?? @ANS: possibly,
  // further investigation...(and let all Commands go thru)
  // @TODO: if yes, may allow 'if'-branch to be removed...
  // @MUTABLE:
  private var _inPlace: Boolean = false
  def inPlace: Boolean = _inPlace

  // mutable ListBuffer only visible outside as immutable List
  type CleanDirectionsListBuffer = ListBuffer[Command]
  // @MUTABLE:
  private var _directionsList: CleanDirectionsListBuffer =
    new CleanDirectionsListBuffer()
  def directionsList: Parser.CleanDirectionsList = _directionsList.toList

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
    var result = false

    for (command <- dl) {
      if (!_inPlace) {
        fastparse.parse(command, parserPlace(_)) match {
          case Parsed.Success(value, index) => {
            value match {
              case (p: String, x: Int, y: Int, o: String) => {
                //println(s"Parser.parse: ${p} (${x}, ${y}, ${o})")
                _directionsList += Place(new Point(x, y), Orientation.withName(o))
                _inPlace = true
                result = true
              }
              case _ => result = false
            }
          }
          case Parsed.Failure(expected, index, extra) => {
            println(extra.trace().longMsg)
            result = false
          }
          case _ => result = false

        } // match
      } // if
      else { // inPlace
        fastparse.parse(command, parserCommands(_)) match {
          case Parsed.Success(value, index) => {
            value match {
              case (p: String, x: Int, y: Int, o: String) => {
                //println(s"Parser.parse: ${p} (${x}, ${y}, ${o})")
                _directionsList += Place(new Point(x, y), Orientation.withName(o))
                _inPlace = true
              }
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
      } // else
    } // for

    result

  } // parse
} // Parser
