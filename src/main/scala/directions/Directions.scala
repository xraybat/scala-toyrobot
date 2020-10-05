package directions

import scala.io.StdIn.readLine
import scala.io.Source

object Directions {

  def fromStdIn: List[String] = Iterator
                                .continually(readLine)
                                .takeWhile(Option(_).fold(false)(_.nonEmpty))
                                .toList

    // simple list pass-through for testing
  def fromList(l: List[String]): List[String] = l
  def fromFile(fileName: String): List[String] = Source.fromFile(fileName).getLines.toList

} // Directions