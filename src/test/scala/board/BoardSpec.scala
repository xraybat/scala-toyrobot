package board

import org.scalatest.FlatSpec
import point._

class BoardSpec extends FlatSpec {
  "A default Board" should "have extent 5x5" in {
    val b = new Board
    assert(b.xExtent == 5 && b.yExtent == 5)
  }
  "A 10x10 Board" should "have extent 10x10" in {
    val b = new Board(10, 10)
    assert(b.xExtent == 10 && b.yExtent == 10)
  }

  "A default Board" should "have Point (1, 1) inBounds" in {
    assert(new Board().inBounds(new Point(1, 1)))
  }
  "A default Board" should "have a default Point (0, 0) not inBounds" in {
    assert(!new Board().inBounds(new Point))
  }
  "A default Board" should "have Point (5, 5) inBounds" in {
    assert(new Board().inBounds(new Point(5, 5)))
  }
  "A default Board" should "have Point (6, 5) not inBounds" in {
    assert(!new Board().inBounds(new Point(6, 5)))
  }
  "A default Board" should "have Point (5, 6) not inBounds" in {
    assert(!new Board().inBounds(new Point(5, 6)))
  }
  "A 10x10 Board" should "have Point (1, 1) inBounds" in {
    assert(new Board(10, 10).inBounds(new Point(1, 1)))
  }
  "A 10x10 default Board" should "have a default Point (0, 0) not inBounds" in {
    assert(!new Board(10, 10).inBounds(new Point))
  }
  "A 10x10 default Board" should "have a default Point (11, 11) not inBounds" in {
    assert(!new Board(10, 10).inBounds(new Point(11, 11)))
  }

  "A default Board" should "have Point (1, 1) not outBounds" in {
    assert(!new Board().outBounds(new Point(1, 1)))
  }
  "A default Board" should "have a default Point (0, 0) outBounds" in {
    assert(new Board().outBounds(new Point))
  }

} // BoardSpec