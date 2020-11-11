package toyrobot.command

import toyrobot.point._
import toyrobot.orientation._
import toyrobot.orientation.Orientation._

// for use in parsed directions list
abstract class Command {}
case class PlaceRobot(val pt: Point, val o: Orientation) extends Command {}
case class PlaceObject() extends Command {}
case class Move() extends Command {}
case class Left() extends Command {}
case class Right() extends Command {}
case class Report() extends Command {}

// companion object. for use in input to be parsed; not an
// enum (for 'stable identifier' matching)
object Command {
  val PlaceRobot = "PLACE"
  val PlaceObject = "PLACE_OBJECT"
  val Move = "MOVE"
  val Left = "LEFT"
  val Right = "RIGHT"
  val Report = "REPORT"
}
