// @TODO: turn into a castor actor; a robot *is* an actor after all...
package robot

import board._
import point._
import parser._
import parser.Parser.CleanDirectionsList
import commands._
import orientation._

// @TODO: keep point arg for recursion???
class Robot(
  val board: Board,
  val point: Point,
  val directions: CleanDirectionsList) {  // leave parse outside Robot

  // @TODO: remove, and rely on Parser.inPlace??
  private var _inPlace: Boolean = false
  def inPlace: Boolean = _inPlace // @TODO: derive from Parser.inPlace()??...

  def inBounds: Boolean = if (_inPlace) board.inBounds(point) else false
  def outBounds: Boolean = !inBounds
  
  // @TODO: make walk() recursive walk(point)?? or, no need too simple...
  def walk(): Unit = {
    for (command <- directions) { // cleaned
      command match {
        case Place(x, y, o) => println(s"Robot.walk :: found PLACE ${x}, ${y}, ${o}")
        case Move() => println("Robot.walk :: found MOVE")
        case Left() => println("Robot.walk :: found LEFT")
        case Right() => println("Robot.walk :: found RIGHT")
        case Report() => println("Robot.walk :: found REPORT")
      }
    } // for
  } // walk

} // Robot