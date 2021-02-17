package toyrobot.command

import toyrobot.board._
import toyrobot.point._
import toyrobot.orientation._
import toyrobot.orientation.Orientation._

// companion object. for use in input to be parsed; not an
// enum (for 'stable identifier' matching)
object Command {
  val KeywordPlaceRobot = "PLACE"
  val KeywordPlaceObject = "PLACE_OBJECT"
  val KeywordMove = "MOVE"
  val KeywordLeft = "LEFT"
  val KeywordRight = "RIGHT"
  val KeywordReport = "REPORT"

  val msgPrefix = "Robot.walk: "  // @TODO: make private??
}

import Command._

// for use in parsed directions list
sealed abstract class Command

case class PlaceRobot(point: Point, orientation: Orientation) extends Command {
  def place(board: Board): (Boolean, Point, Orientation) =
    if (board.inBounds(point)) (true, point, orientation) else (false, point, orientation)

  def msg: String = s"${msgPrefix}PLACEd at ${point}, ${orientation}"
  def msg(board: Board): String = s"${msgPrefix}can't PLACE at ${point} on a ${board} board!"
}

case class PlaceObject() extends Command {
  // use current point and orientation to set blocked point on board
  def place(board: Board, point: Point, orientation: Orientation): Unit = board.Block(point, orientation)
  def msg: String = s"${msgPrefix}can't PLACE_OBJECTs until in PLACE!"
}

case class Move() extends Command {
  def move(b: Board, pt: Point, o: Orientation): (Boolean, Point) = {
    // only MOVE is concerned with blocked points
    val movePt = Point.move(pt, o)
    if (b.inBounds(movePt) && !b.isBlocked(movePt)) (true, movePt) else (false, movePt)
  }

  def msg(point: Point, board: Board): String = 
    if (board.inBounds(point) && board.isBlocked(point))
      s"${msgPrefix}can't MOVE to ${point} 'cos i'm blocked!"
    else
      s"${msgPrefix}can't MOVE to ${point} on a ${board} board 'cos it's out of bounds!"

  def msg: String = s"${msgPrefix}can't MOVE when not in PLACE!"

} // Move

sealed trait Turn extends Command {
  def msg: String = s"${msgPrefix}can't TURN when not in PLACE!"
}

case class Left() extends Command with Turn {
  def turn(orientation: Orientation): Orientation = Orientation.turnLeft(orientation)
}

case class Right() extends Command with Turn {
  def turn(orientation: Orientation): Orientation = Orientation.turnRight(orientation)
}

case class Report() extends Command {
  def msg(point: Point, orientation: Orientation): String = s"${msgPrefix}REPORTing from ${point}, ${orientation}"
  def msg: String = s"${msgPrefix}REPORTing that i'm not in PLACE!"

}
