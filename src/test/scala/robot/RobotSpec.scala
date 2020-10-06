package robot

import org.scalatest.FlatSpec
import board._
import point._
import directions._

class RobotSpec extends FlatSpec {

  private val b = new Board
  private val p = new Point
  private val in = SourceDirections.fromList("hello," :: "how" :: "are" :: "you?" :: Nil)

  "A simple Robot" should "have a default Board, Point, and DirectionsList" in {
    val r = new Robot(b, p, in)
    assert(
      r.board.xExtent == 5 && r.board.yExtent == 5
      && r.point.x == 0 && r.point.y == 0
      && r.directions == "hello," :: "how" :: "are" :: "you?" :: Nil)
  }

} // RobotSpec