package point

import orientation._
import orientation.Orientation._

class Point(val x: Int = -1, val y: Int = -1) {

} // Point

// companion object
object Point {
  def move(pt: Point, o: Orientation): Point = {
    o match {
      case Orientation.North => new Point(pt.x, pt.y+1)
      case Orientation.East => new Point(pt.x+1, pt.y)
      case Orientation.South => new Point(pt.x, pt.y-1)
      case Orientation.West => new Point(pt.x-1, pt.y)
    }
  }
} // Point
