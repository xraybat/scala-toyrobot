package toyrobot.command

import toyrobot.board._
import toyrobot.point._
import toyrobot.orientation._
import toyrobot.orientation.Orientation._

// for use in parsed directions list
sealed abstract class Command {}

case class PlaceRobot(point: Point, orientation: Orientation) extends Command {
  def place(board: Board): (Boolean, Point, Orientation) =
    if (board.inBounds(point)) (true, point, orientation) else (false, point, orientation)
}

case class PlaceObject() extends Command {
  // use current point and orientation to set blocked point on board
  def place(board: Board, point: Point, orientation: Orientation): Unit =
    board.Block(point, orientation)
}

case class Move() extends Command {
  def move(b: Board, pt: Point, o: Orientation): (Boolean, Point) = {
    // only MOVE is concerned with blocked points
    val movePt = Point.move(pt, o)
    if (b.inBounds(movePt) && !b.isBlocked(movePt)) (true, movePt) else (false, movePt)
  }
}

case class Left() extends Command {
  def turn(orientation: Orientation): Orientation = Orientation.turnLeft(orientation)
}

case class Right() extends Command {
  def turn(orientation: Orientation): Orientation = Orientation.turnRight(orientation)
}

case class Report() extends Command {}

// companion object. for use in input to be parsed; not an
// enum (for 'stable identifier' matching)
object Command {
  val keywordPlaceRobot = "PLACE"
  val keywordPlaceObject = "PLACE_OBJECT"
  val keywordMove = "MOVE"
  val keywordLeft = "LEFT"
  val keywordRight = "RIGHT"
  val keywordReport = "REPORT"
}
