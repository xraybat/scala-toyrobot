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
          println(s"Robot.walk: found PLACE in place (${pt.x}, ${pt.y}, ${o})")
          _inPlace = inBounds(pt)
          if (_inPlace) {
            _currPoint = pt
            _currOrientation = o
          }
        }
        case Move() => { if (_inPlace) println(s"Robot.walk: found MOVE in place") }
        case Left() => { if (_inPlace) println(s"Robot.walk: found LEFT in place") }
        case Right() => { if (_inPlace) println(s"Robot.walk: found RIGHT in place") }
        case Report() => { if (_inPlace) println(s"Robot.walk: found REPORT in place") }
      }
    } // for
  } // walk

} // Robot