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
    def parser[_: P] = P(Commands.Place.toString)

    for (command <- dl) {
      fastparse.parse(command, parser(_)) match {
        case Parsed.Success((), 5) => println("found PLACE")
        case Parsed.Failure(expected, index, extra) => println(extra.trace().longMsg)
        case _ => println("Problem parsing directions.") 
      }
    } // for
  } // parse
} // Directions
