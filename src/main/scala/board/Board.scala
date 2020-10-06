package board

import point._

class Board(val xExtent: Int = 5, val yExtent: Int = 5) {

  def inBounds(point: Point): Boolean = 
    ((point.x > 0 && point.x <= this.xExtent)
    && (point.y > 0 && point.y <= this.yExtent))

} // Board