// @TODO: turn into a castor actor; a robot *is* an actor after all...
package robot

import board._
import point._
import directions.SourceDirections.DirectionsList

class Robot(val board: Board, val point: Point, val directions: DirectionsList) {

  private var _inPlace: Boolean = false
  def inPlace: Boolean = _inPlace

  def inBounds: Boolean = if (_inPlace) board.inBounds(point) else false
  def outBounds: Boolean = !inBounds
  
  def walk(): Unit = {
    directions.foreach { 
      println
    }
  } // walk

} // Robot