package point

import orientation._
import orientation.Orientation._

class Point(val x: Int = -1, val y: Int = -1) {
  override def toString: String = s"(${x}, ${y})"

  // "equals" logic
  def canEqual(a: Any) = a.isInstanceOf[Point]

  override def equals(that: Any): Boolean =
    that match {
      case that: Point => {
        that.canEqual(this) &&
        this.x == that.x &&
        this.y == that.y
      }
      case _ => false
    }

  override def hashCode: Int = {
    val prime = 31
    var result = 1
    result = prime * result + x
    result = prime * result + y
    result
  }
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
