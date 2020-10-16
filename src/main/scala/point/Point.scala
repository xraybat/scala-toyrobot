package point

import orientation._
import orientation.Orientation._

class Point(val x: Int = -1, val y: Int = -1) {

} // Point

// companion object
object Point {
  def move(pt: Point, o: Orientation): Point = {
    o match {
      case North => new Point(pt.x, pt.y+1)
      case East => new Point(pt.x+1, pt.y)
      case South => new Point(pt.x, pt.y-1)
      case West => new Point(pt.x-1, pt.y)
    }
  }
} // Point