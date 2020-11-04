package toyrobot.point

import toyrobot.orientation._
import toyrobot.orientation.Orientation._

import org.scalatest.FlatSpec
//@Ignore
class PointSpec(/*ignore: String*/) extends FlatSpec {
  "A default Point" should "have coords (-1, -1)" in {
    val p = new Point
    assert(p == new Point(-1, -1))
  }
  "A (0, 0) Point" should "have coords (0, 0)" in {
    val p = new Point(0, 0)
    assert(p == new Point(0, 0))
  }
  "A (1, 1) Point" should "have coords (1, 1)" in {
    val p = new Point(1, 1)
    assert(p == new Point(1, 1))
  }

  "A (0, 0) Point" should "have coords (0, 1) after moving NORTH" in {
    val p = Point.move(new Point(0, 0), Orientation.North)
    assert(p == new Point(0, 1))
  }
  "A (0, 1) Point" should "have coords (0, 0) after moving SOUTH" in {
    val p = Point.move(new Point(0, 1), Orientation.South)
    assert(p == new Point(0, 0))
  }
  "A (0, 0) Point" should "have coords (0, 1) after moving EAST" in {
    val p = Point.move(new Point(0, 0), Orientation.East)
    assert(p == new Point(1, 0))
  }
  "A (1, 0) Point" should "have coords (0, 0) after moving WEST" in {
    val p = Point.move(new Point(1, 0), Orientation.West)
    assert(p == new Point(0, 0))
  }

  "A (1, 0) Point" should "equal a (1, 0) point" in {
    val p = new Point(1, 0)
    assert(p == new Point(1, 0))
  }
  "A (1, 0) Point" should "NOT equal a (2, 0) point" in {
    val p = new Point(1, 0)
    assert(p != new Point(2, 0))
  }

} // PointSpec