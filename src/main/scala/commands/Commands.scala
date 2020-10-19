package commands

import point._
import orientation._
import orientation.Orientation._

// for use in input to be parsed; not an enum (for matching)
object Commands {
  val Place = "PLACE"
  val Move = "MOVE"
  val Left = "LEFT"
  val Right = "RIGHT"
  val Report = "REPORT"
}

// for use in parsed directions list
abstract class Command {}
case class Place(val pt: Point, val o: Orientation) extends Command {}
case class Move() extends Command {}
case class Left() extends Command {}
case class Right() extends Command {}
case class Report() extends Command {}
