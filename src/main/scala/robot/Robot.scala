// @TODO: turn into a castor actor; a robot *is* an actor after all...
package robot

import board._
import point._
import directions.SourceDirections.DirectionsList

import fastparse._, NoWhitespace._
import commands._

class Robot(val board: Board, val point: Point, val directions: DirectionsList) {

  private var _inPlace: Boolean = false
  def inPlace: Boolean = _inPlace

  def inBounds: Boolean = if (_inPlace) board.inBounds(point) else false
  def outBounds: Boolean = !inBounds
  
  def walk(): Unit = {
    def parser[_: P] = P(Commands.Place.toString)

    for (command <- directions) { 
      if (fastparse.parse(command, parser(_)) == Parsed.Success((), 5))
        _inPlace = true
      
    } // for
  } // walk

} // Robot