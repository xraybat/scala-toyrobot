import board._
import point._
import directions._

//object Main extends App {
object Main {
  def main(args: Array[String]): Unit = {

    if (args.length > 1) {
      println("Too many arguments.\n"
              + "\n$ sbt run [filename]")
      return
    }

    val b = new Board
    println(s"${b.xExtent}x${b.yExtent}")

    val p = new Point(0, 0)
    println(s"(${p.x}, ${p.y})")

    if (args.length == 0)
      SourceDirections.fromStdIn
    else {
      try {
        val in =
          if (args.length == 0) SourceDirections.fromStdIn
          else SourceDirections.fromFile(args(0))

        println(in)
      }
      catch {
        case ex: java.io.FileNotFoundException =>
          println(s"File '${args(0)}' does not exist.")
      }
    } // else
  } // main
} // Main