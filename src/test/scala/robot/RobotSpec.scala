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

  "A simple Robot" should "have a default Board, and CleanDirectionsList" in {
    val p = new Parser
    val r = new Robot(b, p.directionsList)
    assert(
      r.board.xExtent == 5 && r.board.yExtent == 5
      && r.directions.length == 0)
  }
  "A simple Robot" should "start as not inPlace" in {
    val p = new Parser
    val r = new Robot(b, p.directionsList)
    assert(!r.inPlace)
  }

  "A simple Robot" should "be able to walk an empty CleanDirectionsList without exception" in {
    val p = new Parser
    val r = new Robot(b, p.directionsList)
    r.walk
    assert(true)
  }

  "A Robot" should "be able to walk a correct PARSED CleanDirectionsList without exception" in {
    val in: PreParsedDirectionsList = "PLACE 1,2,NORTH" :: "REPORT" :: Nil
    val p = new Parser
    if (p.parse(in)) {
      val r = new Robot(b, p.directionsList)
      r.walk
      assert(true)
    }
    else
      assert(false)
  }
  "A Robot" should "NOT walk an incorrect CleanDirectionsList" in {
    val in: PreParsedDirectionsList = "LEFT" :: "RIGHT" :: "REPORT" :: Nil
    val p = new Parser
    if (p.parse(in)) {
      assert(false)
      //val r = new Robot(b, pt, p.directionsList)
      //r.walk
    }
    else
      assert(true)
  }
  "A Robot" should "handle multiple PLACEs" in {
    val in: PreParsedDirectionsList = "PLACE 2,3,NORTH" :: "REPORT" :: "PLACE 3,4,SOUTH" :: "REPORT" :: Nil
    val p = new Parser
    if (p.parse(in)) {
      val r = new Robot(b, p.directionsList)
      r.walk
      assert(true)
    }
    else
      assert(false)
  }
  "A Robot" should "turn LEFT and RIGHT" in {
    val in: PreParsedDirectionsList = "PLACE 0,0,NORTH" :: "REPORT" :: "LEFT" :: "REPORT" :: "LEFT" :: "REPORT" :: "RIGHT" :: "REPORT" :: "RIGHT" :: "REPORT" :: Nil
    val p = new Parser
    if (p.parse(in)) {
      val r = new Robot(b, p.directionsList)
      r.walk
      assert(true)
    }
    else
      assert(false)
  }

} // RobotSpec