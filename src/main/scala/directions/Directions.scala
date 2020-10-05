package directions

import scala.io.StdIn.readLine
import scala.io.Source

object Directions {
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

} // Directions