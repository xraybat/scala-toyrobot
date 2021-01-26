package toyrobot.point

import toyrobot.orientation._
import toyrobot.orientation.Orientation._

// case classes have inbuilt equality
case class Point(val x: Int = -1, val y: Int = -1) {
  override def toString: String = s"(${x}, ${y})"
}

// companion object
object Point {
  def move(pt: Point, o: Orientation): Point = {
    o match {
      case Orientation.North => Point(pt.x, pt.y+1)
      case Orientation.East => Point(pt.x+1, pt.y)
      case Orientation.South => Point(pt.x, pt.y-1)
      case Orientation.West => Point(pt.x-1, pt.y)
    }
  }
} // Point
