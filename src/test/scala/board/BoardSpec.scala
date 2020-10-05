package board

import org.scalatest.FlatSpec

class BoardSpec extends FlatSpec {
  "A default Board" should "have extent 5x5" in {
    val b = new Board
    assert(b.xExtent == 5 && b.yExtent == 5)
  }

  "A 10x10 Board" should "have extent 10x10" in {
    val b = new Board(10, 10)
    assert(b.xExtent == 10 && b.yExtent == 10)
  }
} // BoardSpec