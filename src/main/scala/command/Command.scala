package toyrobot.command

import toyrobot.board._
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

  def placeRobot(b: Board, pt: Point, o: Orientation): (Boolean, Point, Orientation) =
    if (b.inBounds(pt)) (true, pt, o) else (false, pt, o)

  def placeObject(b: Board, pt: Point, o: Orientation): Board = ???

  def move(b: Board, pt: Point, o: Orientation): (Boolean, Point) = {
    // only MOVE is concerned with blocked points
    val movePt = Point.move(pt, o)
    if (b.inBounds(movePt) && !b.isBlocked(movePt)) (true, movePt) else (false, movePt)
  }

  def left(o: Orientation): Orientation = ???
  def right(o: Orientation): Orientation = ???
  def report = ???
}
