package toyrobot.board

import toyrobot.point._
import toyrobot.orientation._
import toyrobot.orientation.Orientation._

import scala.collection.mutable.ListBuffer

// companion object
object Board {
  // @TODO: move PointsList type to Point class??
  type PointsList = List[Point] // @TODO: sparse list??
}

class Board(val xExtent: Int = 5, val yExtent: Int = 5) {

  def inBounds(pt: Point): Boolean = 
    (pt.x >= 0 && pt.x < this.xExtent) && (pt.y >= 0 && pt.y < this.yExtent)

  def outBounds(pt: Point): Boolean = !inBounds(pt)

  // mutable ListBuffer only visible outside as immutable List
  type PointsListBuffer = ListBuffer[Point]   // @TODO: sparse list??
  // @MUTABLE:
  private var _pointsList: PointsListBuffer = new PointsListBuffer()
  def pointsList: Board.PointsList = _pointsList.toList

  def Block(pt: Point, o: Orientation) = ???  // sparse list of Points???
  def isBlocked(pt: Point): Boolean = false

  override def toString: String = s"${xExtent}x${yExtent}"

} // Board