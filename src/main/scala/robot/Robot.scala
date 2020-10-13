// @TODO: turn into a castor actor; a robot *is* an actor after all...
package robot

import board._
import point._
import directions._
import directions.Directions.CleanDirectionsList

import fastparse._, NoWhitespace._
import commands._

// @TODO: keep point arg for recursion???
class Robot(
  val board: Board,
  val point: Point,
  val directions: CleanDirectionsList) {  // @TODO: or, leave parse outside Robot??...better??

  private var _inPlace: Boolean = false
  def inPlace: Boolean = _inPlace

  def inBounds: Boolean = if (_inPlace) board.inBounds(point) else false
  def outBounds: Boolean = !inBounds
  
  // @TODO: make walk() recursive walk(point)?? or, no need to simple...
  def walk(): Unit = {
    for (command <- directions) {
      // once only
      // @TODO: directions as CleanDirectionsList passed in??...
      
    } // for
  } // walk

} // Robot