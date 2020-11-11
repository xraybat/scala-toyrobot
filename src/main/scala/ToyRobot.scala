package toyrobot

import toyrobot.board._
import toyrobot.point._
import toyrobot.input._
import toyrobot.input.Input.PreParsedDirectionsList
import toyrobot.parser._
import toyrobot.parser.Parser.DirectionsList
import toyrobot.robot._

object ToyRobot {
  def main(args: Array[String]): Unit = {

    if (args.length > 1) {
      println("Too many arguments.\n"
              + "\n$ sbt run [filename]")
      return
    }

    val b = new Board
    //println(s"${b}")

    val in: PreParsedDirectionsList =
      if (args.length == 0)
        Input.fromStdIn
      else {
        try
          Input.fromFile(args(0))
        catch {
          case ex: java.io.FileNotFoundException => {
            println(s"File '${args(0)}' does not exist.")
            return
          }
        }
      } // else
    //println(in)

    val p = new Parser()
    if (p.parse(in)) {
      val r = new Robot(b, p.directionsList)
      r.walk
    }

  } // main
} // ToyRobot