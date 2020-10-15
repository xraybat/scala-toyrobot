// @TODO: turn into a castor actor; a robot *is* an actor after all...
package robot

import board._
import point._
import parser._
import parser.Parser.CleanDirectionsList
import commands._
import orientation._
import orientation.Orientation._

// leave parse outside of Robot
class Robot(
  val board: Board,
  val directions: CleanDirectionsList) {

  var _currPoint: Point = new Point                       // @TODO: ok to default??
  var _currOrientation: Orientation = Orientation.North   // @TODO: ok to default??

  // @TODO: remove, and rely on Parser.inPlace?? NO...
  private var _inPlace: Boolean = false
  def inPlace: Boolean = _inPlace // @TODO: derive from Parser.inPlace()??...

  def inBounds(point: Point): Boolean = board.inBounds(point)
  def outBounds(point: Point): Boolean = !inBounds(point)
  
  // @TODO: make walk() recursive walk(point)?? or, no need, too simple...
  def walk(): Unit = {
    for (command <- directions) { // cleaned
      command match {
        case Place(pt: Point, o: Orientation) => {
          println(s"Robot.walk: PLACEd at (${pt.x}, ${pt.y}, ${o})")
          _inPlace = inBounds(pt)
          if (_inPlace) {
            _currPoint = pt
            _currOrientation = o
          }
        }
        case Move() => {
          if (_inPlace) {
            println(s"Robot.walk: MOVE in place") 
          }
        }
        case Left() => {
          if (_inPlace) {
            //println(s"Robot.walk: LEFT in place")
            _currOrientation = turnLeft(_currOrientation)
          }
        }
        case Right() => {
          if (_inPlace) {
             //println(s"Robot.walk: RIGHT in place")
            _currOrientation = turnRight(_currOrientation)
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