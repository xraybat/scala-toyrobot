package toyrobot.robot

import org.scalatest.FlatSpec

import toyrobot.board._
import toyrobot.point._
import toyrobot.orientation._
import toyrobot.orientation.Orientation._
import toyrobot.input._
import toyrobot.input.Input.PreParsedDirectionsList
import toyrobot.parser._
import toyrobot.parser.Parser.DirectionsList

// TDD-ing through the Robot setup and walk()
//@Ignore
class RobotSpec(/*ignore: String*/) extends FlatSpec {

  private val b = new Board

  "A simple Robot" should "have a default Board, and DirectionsList" in {
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

  "A simple Robot" should "be able to walk an empty DirectionsList without exception" in {
    val p = new Parser
    val r = new Robot(b, p.directionsList)
    r.walk
    assert(!r.inPlace)
  }

  "A Robot" should "be able to walk a correct PARSED DirectionsList without exception" in {
    val in: PreParsedDirectionsList = "PLACE 1,2,NORTH" :: "REPORT" :: Nil
    val p = new Parser
    if (p.parse(in)) {
      val r = new Robot(b, p.directionsList)
      r.walk
      assert(
        r.inPlace
        && r.point == new Point(1, 2)
        && r.orientation == Orientation.North)
    }
    else
      assert(false)
  }
  "A Robot" should "walk a DirectionsList with no PLACE" in {
    val in: PreParsedDirectionsList = "LEFT" :: "RIGHT" :: "REPORT" :: Nil
    val p = new Parser
    if (p.parse(in)) {
      val r = new Robot(b, p.directionsList)
      r.walk
      assert(!r.inPlace)
    }
    else
      assert(false)
  }
  "A Robot" should "handle multiple PLACEs" in {
    val in: PreParsedDirectionsList = "PLACE 2,3,NORTH" :: "REPORT" :: "PLACE 3,4,SOUTH" :: "REPORT" :: Nil
    val p = new Parser
    if (p.parse(in)) {
      val r = new Robot(b, p.directionsList)
      r.walk
      assert(
        r.inPlace
        && r.point == new Point(3, 4)
        && r.orientation == Orientation.South)
    }
    else
      assert(false)
  }
  "A Robot" should "NOT be PLACEd out of bounds" in {
    val in: PreParsedDirectionsList = "PLACE 5,5,NORTH" :: "MOVE" :: "REPORT" :: Nil
    val p = new Parser
    if (p.parse(in)) {
      val r = new Robot(b, p.directionsList)
      r.walk
      assert(!r.inPlace)
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
      assert(
        r.inPlace
        && r.point == new Point(0, 0)
        && r.orientation == Orientation.North)
    }
    else
      assert(false)
  }
  "A Robot" should "walk NORTH and SOUTH, all in bounds" in {
    val in: PreParsedDirectionsList = "PLACE 0,0,NORTH" :: "REPORT" :: "MOVE" :: "REPORT" :: "MOVE" :: "REPORT" :: "MOVE" :: "REPORT" :: "LEFT" :: "LEFT" :: "REPORT" :: "MOVE" :: "REPORT" :: "MOVE" :: "REPORT" :: "MOVE" :: "REPORT" :: Nil
    val p = new Parser
    if (p.parse(in)) {
      val r = new Robot(b, p.directionsList)
      r.walk
      assert(
        r.inPlace
        && r.point == new Point(0, 0)
        && r.orientation == Orientation.South)
    }
    else
      assert(false)
  }
  "A Robot" should "walk NORTH and SOUTH, ignoring out of bounds" in {
    val in: PreParsedDirectionsList = "PLACE 0,0,NORTH" :: "REPORT" :: "MOVE" :: "REPORT" :: "MOVE" :: "REPORT" :: "MOVE" :: "REPORT" :: "MOVE" :: "REPORT" :: "MOVE" :: "REPORT" :: "LEFT" :: "LEFT" :: "REPORT" :: "MOVE" :: "REPORT" :: "MOVE" :: "REPORT" :: "MOVE" :: "REPORT" :: "MOVE" :: "REPORT" :: "MOVE" :: "REPORT" :: Nil
    val p = new Parser
    if (p.parse(in)) {
      val r = new Robot(b, p.directionsList)
      r.walk
      assert(
        r.inPlace
        && r.point == new Point(0, 0)
        && r.orientation == Orientation.South)
    }
    else
      assert(false)
  }
} // RobotSpec

// test PROBLEM.md examples
class RobotEgSpec(/*ignore: String*/) extends FlatSpec {

  private val b = new Board

  "A Robot" should "handle eg1.txt" in {
    val in = Input.fromFile((os.pwd/"resources"/"eg1.txt").toString)
    val p = new Parser()
    if (p.parse(in)) {
      val r = new Robot(b, p.directionsList)
      r.walk
      assert(
        r.inPlace
        && r.point == new Point(0, 1)
        && r.orientation == Orientation.North)
    }
    else
      assert(false)
  }
  "A Robot" should "handle eg2.txt" in {
    val in = Input.fromFile((os.pwd/"resources"/"eg2.txt").toString)
    val p = new Parser()
    if (p.parse(in)) {
      val r = new Robot(b, p.directionsList)
      r.walk
      assert(
        r.inPlace
        && r.point == new Point(0, 0)
        && r.orientation == Orientation.West)
    }
    else
      assert(false)
  }
  "A Robot" should "handle eg3.txt" in {
    val in = Input.fromFile((os.pwd/"resources"/"eg3.txt").toString)
    val p = new Parser()
    if (p.parse(in)) {
      val r = new Robot(b, p.directionsList)
      r.walk
      assert(
        r.inPlace
        && r.point == new Point(3, 3)
        && r.orientation == Orientation.North)
    }
    else
      assert(false)
  }
} // RobotEgSpec