package robot

import org.scalatest.FlatSpec
import board._
import point._
import directions._

// TDD-ing through the Robot setup and walk()
class RobotSpec extends FlatSpec {

  private val b = new Board
  private val p = new Point(0, 0)
  private val in = SourceDirections.fromList("hello," :: "how" :: "are" :: "you?" :: Nil)

  "A simple Robot" should "have a default Board, Point, and DirectionsList" in {
    val r = new Robot(b, p, in)
    assert(
      r.board.xExtent == 5 && r.board.yExtent == 5
      && r.point.x == 0 && r.point.y == 0
      && r.directions == "hello," :: "how" :: "are" :: "you?" :: Nil)
  }
  "A simple Robot" should "start as not inPlace, not inBounds, and outBounds" in {
    val r = new Robot(b, p, in)
    assert(!r.inPlace && !r.inBounds && r.outBounds)
  }

  "A simple Robot" should "be able to walk the DirectionsList without exception" in {
    val r = new Robot(b, p, in)
    r.walk
    assert(true)
  }

  "A Robot" should "ignore directions until PLACE" in {
    val place = SourceDirections.fromList("LEFT" :: "RIGHT" :: "PLACE 1, 1, NORTH" :: Nil)
    val r = new Robot(b, p, place)
    r.walk
    assert(r.inPlace)
  }

} // RobotSpec