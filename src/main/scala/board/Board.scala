package board

import point._

class Board(val xExtent: Int = 5, val yExtent: Int = 5) {

  def inBounds(pt: Point): Boolean = 
    (pt.x >= 0 && pt.x < this.xExtent) && (pt.y >= 0 && pt.y < this.yExtent)

  def outBounds(pt: Point): Boolean = !inBounds(pt)

  override def toString: String = s"${xExtent}x${yExtent}"

} // Board