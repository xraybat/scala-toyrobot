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

    for (command <- dl) {
      if (!_inPlace) {
        fastparse.parse(command, parserPlace(_)) match {
          case Parsed.Success(value, index) => {
            println(s"found PLACE, value = ${value}, index = ${index}")
            value match {
              case (p: String, x: Int, y: Int, o: String) => {
                println(s"p = ${p}")
                println(s"x = ${x}")
                println(s"y = ${y}")
                println(s"o = ${o}")
                _inPlace = true
              }
              case _ => println("problem matching PLACE value, ${value}") 
            }
          }
          case Parsed.Failure(expected, index, extra) => println(extra.trace().longMsg)
          case _ => println("Problem parsing for PLACE.")

        } // match
      } // if
      else { // inPlace
        fastparse.parse(command, parserCommands(_)) match {
          case Parsed.Success(value, index) => {
            value match {
              case (p: String, x: Int, y: Int, o: String) => {
                println(s"p = ${p}")
                println(s"x = ${x}")
                println(s"y = ${y}")
                println(s"o = ${o}")
                _inPlace = true
              }
              case c: String => println(s"c = ${c}")
              case _ => println("problem matching command value, ${value}") 

            } // match
          } // match
          case Parsed.Failure(expected, index, extra) => println(extra.trace().longMsg)
          case _ => println("Problem parsing commands.")

        } // match
      } // else
    } // for
  } // parse
} // Directions
