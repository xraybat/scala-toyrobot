package toyrobot.board

import org.scalatest.FlatSpec

import toyrobot.point._

//@Ignore
class BoardSpec(/*ignore: String*/) extends FlatSpec {
  "A default Board" should "have extent 5x5" in {
    val b = new Board
    assert(b.xExtent == 5 && b.yExtent == 5)
  }
  "A 10x10 Board" should "have extent 10x10" in {
    val b = new Board(10, 10)
    assert(b.xExtent == 10 && b.yExtent == 10)
  }

  "A default Board" should "have a Point (0, 0) inBounds" in {
    assert(!new Board().inBounds(new Point))
  }
  "A default Board" should "have Point (1, 1) inBounds" in {
    assert(new Board().inBounds(new Point(1, 1)))
  }
  "A default Board" should "have Point (4, 4) inBounds" in {
    assert(new Board().inBounds(new Point(4, 4)))
  }
  "A default Board" should "have a default Point (-1, -1) not inBounds" in {
    assert(!new Board().inBounds(new Point))
  }
  "A default Board" should "have Point (5, 5) not inBounds" in {
    assert(!new Board().inBounds(new Point(5, 5)))
  }
  "A 10x10 Board" should "have Point (0, 0) inBounds" in {
    assert(new Board(10, 10).inBounds(new Point(0, 0)))
  }
  "A 10x10 Board" should "have Point (1, 1) inBounds" in {
    assert(new Board(10, 10).inBounds(new Point(1, 1)))
  }
  "A 10x10 Board" should "have Point (9, 9) inBounds" in {
    assert(new Board(10, 10).inBounds(new Point(9, 9)))
  }
  "A 10x10 default Board" should "have a default Point (-1, -1) not inBounds" in {
    assert(!new Board(10, 10).inBounds(new Point))
  }
  "A 10x10 default Board" should "have a default Point (10, 10) not inBounds" in {
    assert(!new Board(10, 10).inBounds(new Point(10, 10)))
  }

  "A default Board" should "have Point (0, 0) not outBounds" in {
    assert(!new Board().outBounds(new Point(0, 0)))
  }
  "A default Board" should "have a default Point (-1, -1) outBounds" in {
    assert(new Board().outBounds(new Point))
  }

} // BoardSpec