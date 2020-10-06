// @TODO: turn into a castor actor; a robot *is* an actor after all...
package robot

import board._
import point._
import directions.SourceDirections.DirectionsList

class Robot(val board: Board, val point: Point, val directions: DirectionsList) {
  def walk() = ???
} // Robot