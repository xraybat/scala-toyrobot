package directions

import scala.io.StdIn.readLine
import scala.io.Source

import fastparse._, SingleLineWhitespace._
import commands._
import orientation._

object SourceDirections {
  type DirectionsList = List[String]

  def fromStdIn: DirectionsList =
    Iterator
      .continually(readLine)
      .takeWhile(Option(_).fold(false)(_.nonEmpty))
      .toList

  // simple list pass-through for testing
  def fromList(l: DirectionsList): DirectionsList = l

  def fromFile(fileName: String): DirectionsList =
    Source.fromFile(fileName).getLines.toList

} // SourceDirections

class Directions {
  private var _inPlace: Boolean = false
  def inPlace: Boolean = _inPlace

  def parse(dl: SourceDirections.DirectionsList): Unit = {
    def parserPlace[_: P] = 
      P(Commands.Place.toString.!
        ~ CharIn("0-9").rep(1).! ~ "," ~ CharIn("0-9").rep(1).!
        ~ ","
        ~ (Orientation.North.toString.!
          | Orientation.East.toString.!
          | Orientation.South.toString.!
          | Orientation.West.toString.!)
        ~ End)

    def parserCommands[_: P] = 
      P(Commands.Place.toString.!
        | Commands.Move.toString.!
        | Commands.Left.toString.!
        | Commands.Right.toString.!
        | Commands.Report.toString.!)

    for (command <- dl) {
      if (!_inPlace) {
        fastparse.parse(command, parserPlace(_)) match {
          case Parsed.Success(value, index) => {
             println(s"found PLACE, value = ${value}, index = ${index}")
             /*println(s"value._1 = ${value._1}")
             println(s"value._2 = ${value._2}")
             println(s"value._3 = ${value._3}")
             println(s"value._4 = ${value._4}")*/
            _inPlace = true
          }
          case Parsed.Failure(expected, index, extra) => println(extra.trace().longMsg)
          case _ => println("Problem parsing for PLACE.") 
        } // match
      } // if
      else { // inPlace
        fastparse.parse(command, parserCommands(_)) match {
          case Parsed.Success(value, index) => {
            //println(s"found ${value} at ${index}")
            // @TODO: need to find a way to match on enum string...
            value match {
              case "PLACE" => println("add PLACE")
              case "MOVE" => println("add MOVE")
              case "LEFT" => println("add LEFT")
              case "RIGHT" => println("add RIGHT")
              case "REPORT" => println("add REPORT")

            } // match
          }
          case Parsed.Failure(expected, index, extra) => println(extra.trace().longMsg) // @TODO: ignore
          case _ => println("Problem parsing commands.") 
        } // match
      } // else
    } // for
  } // parse
} // Directions
