import board._
import point._
import directions._

object Main extends App {
//object Main {
//  def main(args: Array[String]): Unit = {
    val b = new Board
    println(s"${b.xExtent}x${b.yExtent}")

    val p = new Point
    println(s"(${p.x}, ${p.y})")

    print(Directions.fromStdIn)
//  }
} // Main