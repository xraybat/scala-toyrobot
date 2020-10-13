import board._
import point._
import directions._
import input._
import input.Input.PreParsedDirectionsList
import directions.Directions.CleanDirectionsList
import robot._

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

    val in: PreParsedDirectionsList =
      if (args.length == 0)
        Input.fromStdIn
      else {
        //try { // @TODO:
          Input.fromFile(args(0))
        //}
        /*catch {
          case ex: java.io.FileNotFoundException =>
            println(s"File '${args(0)}' does not exist.")
        }*/
      } // else
    println(in)

    val d = new Directions()
    d.parse(in)

    val r = new Robot(b, p, d.directionsList)

  } // main
} // Main