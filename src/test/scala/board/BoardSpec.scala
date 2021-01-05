package toyrobot.board

import org.scalatest.FlatSpec

import toyrobot.point._
import toyrobot.orientation._
import toyrobot.orientation.Orientation._

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
    assert(!new Board().inBounds(Point()))
  }
  "A default Board" should "have Point (1, 1) inBounds" in {
    assert(new Board().inBounds(Point(1, 1)))
  }
  "A default Board" should "have Point (4, 4) inBounds" in {
    assert(new Board().inBounds(Point(4, 4)))
  }
  "A default Board" should "have a default Point (-1, -1) not inBounds" in {
    assert(!new Board().inBounds(Point()))
  }
  "A default Board" should "have Point (5, 5) not inBounds" in {
    assert(!new Board().inBounds(Point(5, 5)))
  }
  "A 10x10 Board" should "have Point (0, 0) inBounds" in {
    assert(new Board(10, 10).inBounds(Point(0, 0)))
  }
  "A 10x10 Board" should "have Point (1, 1) inBounds" in {
    assert(new Board(10, 10).inBounds(Point(1, 1)))
  }
  "A 10x10 Board" should "have Point (9, 9) inBounds" in {
    assert(new Board(10, 10).inBounds(Point(9, 9)))
  }
  "A 10x10 default Board" should "have a default Point (-1, -1) not inBounds" in {
    assert(!new Board(10, 10).inBounds(Point()))
  }
  "A 10x10 default Board" should "have a default Point (10, 10) not inBounds" in {
    assert(!new Board(10, 10).inBounds(Point(10, 10)))
  }

  "A default Board" should "have Point (0, 0) not outBounds" in {
    assert(!new Board().outBounds(Point(0, 0)))
  }
  "A default Board" should "have a default Point (-1, -1) outBounds" in {
    assert(new Board().outBounds(Point()))
  }

} // BoardSpec

//@Ignore
class BoardBlockSpec(ignore: String) extends FlatSpec {
  "A new Board" should "not be blocked on Point (0, 0)" in {
    val b = new Board
    assert(!b.isBlocked(Point(0, 0)))
  }

  "A 5x5 Board" should "not allow a block from Point (4, 4), EAST" in {
    val b = new Board
    assert(!b.isBlocked(Point(5, 4)))
  }
  "A 5x5 Board" should "not allow a block from Point (4, 4), NORTH" in {
    val b = new Board
    assert(!b.isBlocked(Point(4, 5)))
  }
  "A 5x5 Board" should "not allow a block from Point (0, 0), WEST" in {
    val b = new Board
    assert(!b.isBlocked(Point(-1, 0)))
  }
  "A 5x5 Board" should "not allow a block from Point (0, 0), SOUTH" in {
    val b = new Board
    assert(!b.isBlocked(Point(0, -1)))
  }

  "A 5x5 Board" should "not be blocked on out of bounds Point (-1, -1)" in {
    val b = new Board
    assert(!b.isBlocked(Point(-1, -1)))
  }
  "A 5x5 Board" should "not be blocked on out of bounds Point (5, 5)" in {
    val b = new Board
    assert(!b.isBlocked(Point(5, 5)))
  }

  "A Board" should "be blocked on Point (1, 0) if PLACE_OBJECT from Point (0, 0), EAST" in {
    val b = new Board
    b.Block(Point(0, 0), Orientation.East)
    assert(b.isBlocked(Point(1, 0)))
  }
  "A Board" should "be blocked on Point (0, 1) if PLACE_OBJECT from Point (0, 0), NORTH" in {
    val b = new Board
    b.Block(Point(0, 0), Orientation.North)
    assert(b.isBlocked(Point(0, 1)))
  }
  "A Board" should "be blocked on Point (3, 4) if PLACE_OBJECT from Point (4, 4), WEST" in {
    val b = new Board
    b.Block(Point(4, 4), Orientation.West)
    assert(b.isBlocked(Point(3, 4)))
  }
  "A Board" should "be blocked on Point (4, 3) if PLACE_OBJECT from Point (4, 4), SOUTH" in {
    val b = new Board
    b.Block(Point(4, 4), Orientation.South)
    assert(b.isBlocked(Point(4, 3)))
  }

  "A Board" should "NOT be blocked on Point (0, 1) if PLACE_OBJECT from Point (0, 0), EAST" in {
    val b = new Board
    b.Block(Point(0, 0), Orientation.East)
    assert(!b.isBlocked(Point(0, 1)))
  }
  "A Board" should "NOT be blocked on Point (1, 0) if PLACE_OBJECT from Point (0, 0), NORTH" in {
    val b = new Board
    b.Block(Point(0, 0), Orientation.North)
    assert(!b.isBlocked(Point(1, 0)))
  }
  "A Board" should "NOT be blocked on Point (4, 3) if PLACE_OBJECT from Point (4, 4), WEST" in {
    val b = new Board
    b.Block(Point(4, 4), Orientation.West)
    assert(!b.isBlocked(Point(4, 3)))
  }
  "A Board" should "NOT be blocked on Point (3, 4) if PLACE_OBJECT from Point (4, 4), SOUTH" in {
    val b = new Board
    b.Block(Point(4, 4), Orientation.South)
    assert(!b.isBlocked(Point(3, 4)))
  }

  "A Board" should "be blocked on lots of Points after lots of PLACE_OBJECTs" in {
    val b = new Board
    b.Block(Point(0, 0), Orientation.East)
    b.Block(Point(1, 0), Orientation.East)
    b.Block(Point(2, 0), Orientation.East)
    b.Block(Point(3, 0), Orientation.East)
    assert(
      b.isBlocked(Point(1, 0))
      && b.isBlocked(Point(2, 0))     
      && b.isBlocked(Point(3, 0))     
      && b.isBlocked(Point(4, 0)))
  }
  "A Board" should "NOT be blocked on *these* Points after lots of PLACE_OBJECTs" in {
    val b = new Board
    b.Block(Point(0, 0), Orientation.East)
    b.Block(Point(1, 0), Orientation.East)
    b.Block(Point(2, 0), Orientation.East)
    b.Block(Point(3, 0), Orientation.East)
    assert(
      !b.isBlocked(Point(0, 1))
      && !b.isBlocked(Point(0, 2))     
      && !b.isBlocked(Point(0, 3))     
      && !b.isBlocked(Point(0, 4)))
  }

} // BoardBlockSpec
