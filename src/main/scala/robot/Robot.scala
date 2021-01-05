package toyrobot.robot

import toyrobot.board._
import toyrobot.point._
import toyrobot.parser._
import toyrobot.directions._
import toyrobot.directions.Directions.DirectionsList
import toyrobot.command._
import toyrobot.orientation._
import toyrobot.orientation.Orientation._

class Robot(val board: Board, val directions: DirectionsList) {

  // @MUTABLE:
  var _currPoint: Point = _
  def point: Point = _currPoint
  var _currOrientation: Orientation = _
  def orientation: Orientation = _currOrientation

  // @MUTABLE:
  private var _inPlace: Boolean = false
  def inPlace: Boolean = _inPlace

  def inBounds(pt: Point): Boolean = board.inBounds(pt)
  def outBounds(pt: Point): Boolean = !inBounds(pt)
  
  def isBlocked(pt: Point): Boolean = board.isBlocked(pt)

  def walk: Unit = {
    for (command <- directions) {
      command match {
        case PlaceRobot(pt: Point, o: Orientation) => {
          Command.placeRobot(board, pt, o) match {
            case (true, pt, o) =>
              _inPlace = true; _currPoint = pt; _currOrientation = o
              println(s"Robot.walk: PLACEd at ${pt}, ${o}")
            case (false, pt, _) =>
              _inPlace = false
              println(s"Robot.walk: can't PLACE at ${pt} on a ${board} board!")
            case _ => // @QU: ??
          }
        }

        case PlaceObject() =>
          if (inPlace)
            Command.placeObject(board, point, orientation)
          else
            println(s"Robot.walk: can't PLACE_OBJECTs until in PLACE!")

        case Move() =>
          if (inPlace) {
            Command.move(board, point, orientation) match {
              case (true, pt) => _currPoint = pt
              case (false, pt) => 
                if (inBounds(pt) && isBlocked(pt))
                  println(s"Robot.walk: can't MOVE to ${pt} 'cos i'm blocked!")
                else
                  println(s"Robot.walk: can't MOVE to ${pt} on a ${board} board 'cos it's out of bounds!")
              case _ => // @QU: ??
            }
          }

        case Left() =>
          if (inPlace) _currOrientation = Command.left(orientation)
        case Right() =>
          if (inPlace) _currOrientation = Command.right(orientation)

        case Report() =>
          if (inPlace)
            println(s"Robot.walk: REPORTing from ${point}, ${orientation}")
          else
            println(s"Robot.walk: REPORTing that i'm not in PLACE!")

      } // match
    } // for
  } // walk
} // Robot