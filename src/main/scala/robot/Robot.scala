// @TODO: turn into a castor actor; a robot *is* an actor
// after all...
package robot

import board._
import point._
import parser._
import parser.Parser.DirectionsList
import commands._
import orientation._
import orientation.Orientation._

// leave parse outside of Robot, but handle correct list logic here.
class Robot(
  val board: Board,
  val directions: DirectionsList) {

  // @MUTABLE:
  var _currPoint: Point = new Point                       // @TODO: ok to default??
  var _currOrientation: Orientation = Orientation.North   // @TODO: ok to default??

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
            println(s"Robot.walk: PLACEd at (${pt.x}, ${pt.y}, ${o})")
            _currPoint = pt
            _currOrientation = o
          }
          else
            println(
              s"Robot.walk: can't PLACE at point (${pt.x}, ${pt.y})"
              + s" on a ${board.xExtent}x${board.xExtent} board!")
        }
        case Move() => {
          if (_inPlace) {
            //println(s"Robot.walk: MOVE in place")
            val pt = Point.move(_currPoint, _currOrientation)
            if (inBounds(pt))
              _currPoint = pt
            else
              println(
                s"Robot.walk: can't MOVE to point (${pt.x}, ${pt.y})"
                + s" on a ${board.xExtent}x${board.xExtent} board!")
          }
        }
        case Left() => {
          if (_inPlace) {
            //println(s"Robot.walk: LEFT in place")
            _currOrientation = Orientation.turnLeft(_currOrientation)
          }
        }
        case Right() => {
          if (_inPlace) {
             //println(s"Robot.walk: RIGHT in place")
            _currOrientation = Orientation.turnRight(_currOrientation)
          }
        }
        case Report() => {
          if (_inPlace)
            println(s"Robot.walk: REPORTing from"
              + s" point (${_currPoint.x}, ${_currPoint.y})"
              + s", orientation ${_currOrientation}")
          else
            println(s"Robot.walk: REPORTing that i'm not in PLACE!")
        }
      }
    } // for
  } // walk
} // Robot