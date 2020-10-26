// @TODO: turn into a castor actor; a robot *is* an actor
// after all...
package robot

import board._
import point._
import parser._
import parser.Parser.DirectionsList
import command._
import orientation._
import orientation.Orientation._

// leave parse outside of Robot, but handle correct list logic here.
class Robot(
  val board: Board,
  val directions: DirectionsList) {

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
  
  // @TODO: make walk() recursive walk(point)??
  // @ANS: no need, too simple
  def walk: Unit = {
    for (command <- directions) {
      command match {
        case Place(pt: Point, o: Orientation) => {
          _inPlace = inBounds(pt)
          if (inPlace) {
            _currPoint = pt
            _currOrientation = o
            println(s"Robot.walk: PLACEd at ${point}, ${orientation}")
          }
          else
            println(s"Robot.walk: can't PLACE at ${pt} on a ${board} board!")
        }
        case Move() =>
          if (inPlace) {
            val pt = Point.move(point, orientation)
            if (inBounds(pt))
              _currPoint = pt
            else
              println(s"Robot.walk: can't MOVE to ${pt} on a ${board} board!")
          }
        case Left() =>
          if (inPlace)
            _currOrientation = Orientation.turnLeft(orientation)
        case Right() =>
          if (inPlace)
            _currOrientation = Orientation.turnRight(orientation)
        case Report() =>
          if (inPlace)
            println(s"Robot.walk: REPORTing from ${point}, ${orientation}")
          else
            println(s"Robot.walk: REPORTing that i'm not in PLACE!")

      } // match
    } // for
  } // walk
} // Robot