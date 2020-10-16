package point

import org.scalatest.FlatSpec
import orientation._
import orientation.Orientation._

//@Ignore
class PointSpec(/**/ignore: String/**/) extends FlatSpec {
  "A default Point" should "have coords (-1, -1)" in {
    val p = new Point
    assert(p.x == -1 && p.y == -1)
  }
  "A (0, 0) Point" should "have coords (0, 0)" in {
    val p = new Point(0, 0)
    assert(p.x == 0 && p.y == 0)
  }
  "A (1, 1) Point" should "have coords (1, 1)" in {
    val p = new Point(1, 1)
    assert(p.x == 1 && p.y == 1)
  }

  "A (0, 0) Point" should "have coords (0, 1) after moving NORTH" in {
    val p = Point.move(new Point(0, 0), Orientation.North)
    assert(p.x == 0 && p.y == 1)
  }
  "A (0, 1) Point" should "have coords (0, 0) after moving SOUTH" in {
    val p = Point.move(new Point(0, 1), Orientation.South)
    assert(p.x == 0 && p.y == 0)
  }
  "A (0, 0) Point" should "have coords (0, 1) after moving EAST" in {
    val p = Point.move(new Point(0, 0), Orientation.East)
    assert(p.x == 1 && p.y == 0)
  }
  "A (1, 0) Point" should "have coords (0, 0) after moving WEST" in {
    val p = Point.move(new Point(1, 0), Orientation.West)
    assert(p.x == 0 && p.y == 0)
  }
} // PointSpec