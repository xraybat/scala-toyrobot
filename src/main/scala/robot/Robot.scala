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
  var _currPoint: Point = new Point                       // @TODO: ok to default??
  def point: Point = _currPoint
  var _currOrientation: Orientation = Orientation.North   // @TODO: ok to default??
  def orientation: Orientation = _currOrientation

  // @MUTABLE:
  private var _inPlace: Boolean = false
  def inPlace: Boolean = _inPlace

  def inBounds(point: Point): Boolean = board.inBounds(point)
  def outBounds(point: Point): Boolean = !inBounds(point)
  
  // @TODO: make walk() recursive walk(point)?? @ANS: no need, too
  // simple
  def walk(): Unit = {
    for (command <- directions) {
      command match {
        case Place(pt: Point, o: Orientation) => {
          _inPlace = inBounds(pt)
          if (_inPlace) {
            _currPoint = pt
            _currOrientation = o
            println(s"Robot.walk: PLACEd at ${point}, ${orientation}")
          }
          else
            println(
              s"Robot.walk: can't PLACE at ${pt} on a ${board} board!")
        }
        case Move() => {
          if (_inPlace) {
            val pt = Point.move(_currPoint, _currOrientation)
            if (inBounds(pt))
              _currPoint = pt
            else
              println(
                s"Robot.walk: can't MOVE to ${pt} on a ${board} board!")
          }
        }
        case Left() => {
          if (_inPlace)
            _currOrientation = Orientation.turnLeft(_currOrientation)
        }
        case Right() => {
          if (_inPlace)
            _currOrientation = Orientation.turnRight(_currOrientation)
        }
        case Report() => {
          if (_inPlace)
            println(s"Robot.walk: REPORTing from ${point}, ${orientation}")
          else
            println(s"Robot.walk: REPORTing that i'm not in PLACE!")
        }
      }
    } // for
  } // walk
} // Robot