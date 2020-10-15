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

  var _currPoint: Point = new Point   // default
  var _currOrientation: Orientation = Orientation.North   // default

  // @TODO: remove, and rely on Parser.inPlace?? NO...
  private var _inPlace: Boolean = false
  def inPlace: Boolean = _inPlace // @TODO: derive from Parser.inPlace()??...

  def inBounds(point: Point): Boolean = board.inBounds(point)
  def outBounds(point: Point): Boolean = !inBounds(point)
  
  // @TODO: make walk() recursive walk(point)?? or, no need, too simple...
  def walk(): Unit = {
    for (command <- directions) { // cleaned
      command match {
        case Place(x, y, o) => {
          val pt: Point = new Point(x, y)
          _inPlace = inBounds(pt)
          if (_inPlace) {
            _currPoint = pt
            //_currOrientation = Value(o)
          }
        }
        case Move() => { if (_inPlace) println("Robot.walk :: found MOVE in place") }
        case Left() => { if (_inPlace) println("Robot.walk :: found LEFT in place") }
        case Right() => { if (_inPlace) println("Robot.walk :: found RIGHT in place") }
        case Report() => { if (_inPlace) println("Robot.walk :: found REPORT in place") }
      }
    } // for
  } // walk

} // Robot