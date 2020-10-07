package directions

import scala.io.StdIn.readLine
import scala.io.Source

import fastparse._, NoWhitespace._
import commands._

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
  def parse(dl: SourceDirections.DirectionsList): Unit = {
    def parser[_: P] = 
      P(Commands.Place.toString.!
        | Commands.Move.toString.!
        | Commands.Left.toString.!
        | Commands.Right.toString.!
        | Commands.Report.toString.!)

    for (command <- dl) {
      fastparse.parse(command, parser(_)) match {
        case Parsed.Success(value, index) => {
          //println(s"found ${value} at ${index}")
          // @TODO: need to find a way to match on enum string...
          value match {
            case "PLACE" => println("add PLACE")
            case "MOVE" => println("add MOVE")
            case "LEFT" => println("add LEFT")
            case "RIGHT" => println("add RIGHT")
            case "REPORT" => println("add MOVE")

          } // match
        }
        case Parsed.Failure(expected, index, extra) => println(extra.trace().longMsg)
        case _ => println("Problem parsing directions.") 
      } // match
    } // for
  } // parse
} // Directions
