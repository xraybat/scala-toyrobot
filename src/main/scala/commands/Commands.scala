package commands

import point._
import orientation._
import orientation.Orientation._

// for use in directions list to be parsed
object Commands extends Enumeration {
  type Commands = Value
  
  val Place = Value("PLACE")
  val Move = Value("MOVE")
  val Left = Value("LEFT")
  val Right = Value("RIGHT")
  val Report = Value("REPORT")
}

// for use in cleaned directions list
abstract class Command {}
case class Place(val pt: Point, val o: Orientation) extends Command {}
case class Move() extends Command {}
case class Left() extends Command {}
case class Right() extends Command {}
case class Report() extends Command {}
