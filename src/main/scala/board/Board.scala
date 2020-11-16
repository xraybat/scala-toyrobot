package toyrobot.board

import toyrobot.point._
import toyrobot.orientation._
import toyrobot.orientation.Orientation._

import scala.collection.mutable.ListBuffer

/* @TODO: don't need until god-World class??
// companion object
object Board {
  // @TODO: move PointsList type to Point class??
  type BlockedPointsList = List[Point] // @TODO: sparse list??
}
*/
class Board(val xExtent: Int = 5, val yExtent: Int = 5) {

  def inBounds(pt: Point): Boolean = 
    (pt.x >= 0 && pt.x < this.xExtent) && (pt.y >= 0 && pt.y < this.yExtent)

  def outBounds(pt: Point): Boolean = !inBounds(pt)

  // mutable ListBuffer only visible outside as immutable List
  type BlockedPointsListBuffer = ListBuffer[Point]   // @TODO: sparse list??
  // @MUTABLE:
  private var _blockedPointsList: BlockedPointsListBuffer =
    new BlockedPointsListBuffer()
  /* @TODO: don't need until god-World class??
  def pointsList: Board.BlockedPointsList = _pointsList.toList
  */

  def Block(pt: Point, o: Orientation): Unit = {
    // use existing move logic to get blocked point
    val blockPoint: Point = Point.move(pt, o)

    // only block in-bound points
    if (inBounds(blockPoint))
      _blockedPointsList += blockPoint
  }
  
  // out-of-bounds points never put into blocked points list
  def isBlocked(pt: Point): Boolean = _blockedPointsList.contains(pt)

  override def toString: String = s"${xExtent}x${yExtent}"

} // Board