package commands

object Commands extends Enumeration {
  type Commands = Value
  
  val Place = Value("PLACE")
  val Move = Value("MOVE")
  val Left = Value("LEFT")
  val Right = Value("RIGHT")
  val Report = Value("REPORT")
}

abstract class Command {}
case class Place(val x: Int, val y: Int, val o: String) extends Command {}
case class Move() extends Command {}
case class Left() extends Command {}
case class Right() extends Command {}
case class Report() extends Command {}
