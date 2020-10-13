package input

import scala.io.StdIn.readLine
import scala.io.Source

object Input {
  type PreParsedDirectionsList = List[String]

  def fromStdIn: PreParsedDirectionsList =
    Iterator
      .continually(readLine)
      .takeWhile(Option(_).fold(false)(_.nonEmpty))
      .toList

  // simple list pass-through for testing
  def fromList(l: PreParsedDirectionsList): PreParsedDirectionsList = l

  def fromFile(fileName: String): PreParsedDirectionsList =
    Source.fromFile(fileName).getLines.toList

} // Input
