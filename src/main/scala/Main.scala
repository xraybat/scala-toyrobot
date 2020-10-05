import board.Board
import point.Point
import scala.io.StdIn.readLine

object Main extends App {
//object Main {
//  def main(args: Array[String]): Unit = {
    val b = new Board
    println(s"${b.xExtent}x${b.yExtent}")

    val p = new Point
    println(s"(${p.x}, ${p.y})")

    val in = Iterator
              .continually(readLine)
              .takeWhile(Option(_).fold(false)(_.nonEmpty))
              .toList
    print(in)
//  }
} // Main