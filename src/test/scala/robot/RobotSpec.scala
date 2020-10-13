package robot

import org.scalatest.FlatSpec
import board._
import point._
import directions._
import directions.Directions.CleanDirectionsList

// TDD-ing through the Robot setup and walk()
//@Ignore
class RobotSpec(/*ignore: String*/) extends FlatSpec {

  private val b = new Board
  private val p = new Point(0, 0)
  private val dl: CleanDirectionsList = new Directions().directionsList

  "A simple Robot" should "have a default Board, Point, and CleanDirectionsList" in {
    val r = new Robot(b, p, dl)
    assert(
      r.board.xExtent == 5 && r.board.yExtent == 5
      && r.point.x == 0 && r.point.y == 0
      && r.directions.length == 0)
  }
  "A simple Robot" should "start as not inPlace, not inBounds, and outBounds" in {
    val r = new Robot(b, p, dl)
    assert(!r.inPlace && !r.inBounds && r.outBounds)
  }

  /*"A simple Robot" should "be able to walk the CleanDirectionsList without exception" in {
    val r = new Robot(b, p, in)
    r.walk
    assert(true)
  }*/

} // RobotSpec