package toyrobot

import toyrobot.board._
import toyrobot.point._
import toyrobot.input._
import toyrobot.input.Input.PreParsedDirectionsList
import toyrobot.parser._
import toyrobot.directions._
import toyrobot.robot._
import toyrobot.world._

import scala.util.{Try,Success,Failure}

object ToyRobot {
  def main(args: Array[String]): Unit = {

    if (args.length > 1) {
      println("Too many arguments.\n"
              + "\n$ sbt run [filename]")
      return
    }

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

    // use the (abstract) world rather than a robot
    val p = new Parser()
    p.parse(in) match {
      case Success(dl) => 
        val d = new Directions(dl)
        val w = new World(d)
        w.robotWalk
        d.printResults
      case Failure(e) => println(s"$e")
    }
  } // main
} // ToyRobot