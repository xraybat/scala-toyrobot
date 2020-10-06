package point

import org.scalatest.FlatSpec

class PointSpec extends FlatSpec {
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
} // PointSpec