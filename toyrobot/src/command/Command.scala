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
}

import Command._

// for use in parsed directions list
sealed abstract class Command {
  protected val msgPrefix = "Robot.walk: "
}

case class PlaceRobot(pt: Point, o: Orientation) extends Command {
  def place(b: Board): (Boolean, Point, Orientation) = if (b.inBounds(pt)) (true, pt, o) else (false, pt, o)
  def msg: String = s"${msgPrefix}PLACEd at ${pt}, ${o}"
  def msg(b: Board): String = s"${msgPrefix}can't PLACE at ${pt} on a ${b} board!"
}

case class PlaceObject() extends Command {
  // use current point and orientation to set blocked point on board
  def place(b: Board, pt: Point, o: Orientation): Unit = b.Block(pt, o)
  def msg: String = s"${msgPrefix}can't PLACE_OBJECTs until in PLACE!"
}

case class Move() extends Command {
  def move(b: Board, pt: Point, o: Orientation): (Boolean, Point) = {
    // only MOVE is concerned with blocked points
    val movePt = Point.move(pt, o)
    if (b.inBounds(movePt) && !b.isBlocked(movePt)) (true, movePt) else (false, movePt)
  }

  def msg(pt: Point, b: Board): String = 
    if (b.inBounds(pt) && b.isBlocked(pt))
      s"${msgPrefix}can't MOVE to ${pt} 'cos i'm blocked!"
    else
      s"${msgPrefix}can't MOVE to ${pt} on a ${b} board 'cos it's out of bounds!"

  def msg: String = s"${msgPrefix}can't MOVE when not in PLACE!"

} // Move

sealed trait Turn extends Command {
  def turn(o: Orientation): Orientation
  def msg: String = s"${msgPrefix}can't TURN when not in PLACE!"
}

case class Left() extends Command with Turn {
  def turn(o: Orientation): Orientation = Orientation.turnLeft(o)
}

case class Right() extends Command with Turn {
  def turn(o: Orientation): Orientation = Orientation.turnRight(o)
}

case class Report() extends Command {
  def msg(pt: Point, o: Orientation): String = s"${msgPrefix}REPORTing from ${pt}, ${o}"
  def msg: String = s"${msgPrefix}REPORTing that i'm not in PLACE!"
}
