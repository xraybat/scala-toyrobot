package board

import point._

class Board(val xExtent: Int = 5, val yExtent: Int = 5) {

  def inBounds(p: Point): Boolean = 
    (p.x >= 0 && p.x < this.xExtent) && (p.y >= 0 && p.y < this.yExtent)

  def outBounds(p: Point): Boolean = !inBounds(p)

} // Board