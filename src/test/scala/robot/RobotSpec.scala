package robot

import org.scalatest.FlatSpec

import board._
import point._
import input._
import input.Input.PreParsedDirectionsList
import parser._
import parser.Parser.CleanDirectionsList

// TDD-ing through the Robot setup and walk()
//@Ignore
class RobotSpec(/*ignore: String*/) extends FlatSpec {

  private val b = new Board
  private val pt = new Point(0, 0)

  "A simple Robot" should "have a default Board, Point, and CleanDirectionsList" in {
    val p = new Parser
    val r = new Robot(b, pt, p.directionsList)
    assert(
      r.board.xExtent == 5 && r.board.yExtent == 5
      && r.point.x == 0 && r.point.y == 0
      && r.directions.length == 0)
  }
  "A simple Robot" should "start as not inPlace, not inBounds, and outBounds" in {
    val p = new Parser
    val r = new Robot(b, pt, p.directionsList)
    assert(!r.inPlace && !r.inBounds && r.outBounds)
  }
  "A simple Robot" should "be able to walk an empty CleanDirectionsList without exception" in {
    val p = new Parser
    val r = new Robot(b, pt, p.directionsList)
    r.walk
    assert(true)
  }

  "A Robot" should "be able to walk an empty PARSED CleanDirectionsList without exception" in {
    val in: PreParsedDirectionsList = List[String]()
    val p = new Parser
    p.parse(in)
    val r = new Robot(b, pt, p.directionsList)
    r.walk
    assert(true)
  }

} // RobotSpec