package toyrobot.robot

import org.scalatest.FlatSpec

import toyrobot.board._
import toyrobot.point._
import toyrobot.orientation._
import toyrobot.orientation.Orientation._
import toyrobot.input._
import toyrobot.input.Input.PreParsedDirectionsList
import toyrobot.parser._
import toyrobot.directions._

import scala.util.{Try,Success,Failure}

// TDD-ing through the Robot setup and walk()
//@Ignore
class RobotSpec(ignore: String) extends FlatSpec {

  private val _b = new Board

  "A simple Robot" should "have a default Board, and DirectionsList" in {
    val in: PreParsedDirectionsList = Nil
    val p = new Parser
    p.parse(in) match {
      case Success(dl) =>
        val r = new Robot(_b, new Directions(dl))
        assert(
          r.board.xExtent == 5 && r.board.yExtent == 5
          && r.directions.list.length == 0)
      case _ => assert(false)
    }
  }
  "A simple Robot" should "start as not inPlace" in {
    val in: PreParsedDirectionsList = Nil
    val p = new Parser
    p.parse(in) match {
      case Success(dl) =>
        val r = new Robot(_b, new Directions(dl))
        assert(!r.inPlace)
      case _ => assert(false)
    }
  }

  "A simple Robot" should "be able to walk an empty DirectionsList without exception" in {
    val in: PreParsedDirectionsList = Nil
    val p = new Parser
    p.parse(in) match {
      case Success(dl) =>
        val r = new Robot(_b, new Directions(dl))
        r.walk
        assert(!r.inPlace)
      case _ => assert(false)
    }
  }

  "A Robot" should "be able to walk a correct PARSED DirectionsList without exception" in {
    val in: PreParsedDirectionsList = "PLACE 1,2,NORTH" :: "REPORT" :: Nil
    val p = new Parser
    p.parse(in) match {
      case Success(dl) =>
        val r = new Robot(_b, new Directions(dl))
        r.walk
        assert(
          r.inPlace
          && r.point == Point(1, 2)
          && r.orientation == Orientation.North)
      case _ => assert(false)
    }
  }
  "A Robot" should "walk a DirectionsList with no PLACE" in {
    val in: PreParsedDirectionsList = "LEFT" :: "RIGHT" :: "REPORT" :: Nil
    val p = new Parser
    p.parse(in) match {
      case Success(dl) =>
        val r = new Robot(_b, new Directions(dl))
        r.walk
        assert(!r.inPlace)
      case _ => assert(false)
    }
  }
  "A Robot" should "handle multiple PLACEs" in {
    val in: PreParsedDirectionsList = "PLACE 2,3,NORTH" :: "REPORT" :: "PLACE 3,4,SOUTH" :: "REPORT" :: Nil
    val p = new Parser
    p.parse(in) match {
      case Success(dl) =>
        val r = new Robot(_b, new Directions(dl))
        r.walk
        assert(
          r.inPlace
          && r.point == Point(3, 4)
          && r.orientation == Orientation.South)
      case _ => assert(false)
    }
  }
  "A Robot" should "NOT be PLACEd out of bounds" in {
    val in: PreParsedDirectionsList = "PLACE 5,5,NORTH" :: "MOVE" :: "REPORT" :: Nil
    val p = new Parser
    p.parse(in) match {
      case Success(dl) =>
        val r = new Robot(_b, new Directions(dl))
        r.walk
        assert(!r.inPlace)
      case _ => assert(false)
    }
  }
  "A Robot" should "turn LEFT and RIGHT" in {
    val in: PreParsedDirectionsList = "PLACE 0,0,NORTH" :: "REPORT" :: "LEFT" :: "REPORT" :: "LEFT" :: "REPORT" :: "RIGHT" :: "REPORT" :: "RIGHT" :: "REPORT" :: Nil
    val p = new Parser
    p.parse(in) match {
      case Success(dl) =>
        val r = new Robot(_b, new Directions(dl))
        r.walk
        assert(
          r.inPlace
          && r.point == Point(0, 0)
          && r.orientation == Orientation.North)
      case _ => assert(false)
    }
  }
  "A Robot" should "walk NORTH and SOUTH, all in bounds" in {
    val in: PreParsedDirectionsList = "PLACE 0,0,NORTH" :: "REPORT" :: "MOVE" :: "REPORT" :: "MOVE" :: "REPORT" :: "MOVE" :: "REPORT" :: "LEFT" :: "LEFT" :: "REPORT" :: "MOVE" :: "REPORT" :: "MOVE" :: "REPORT" :: "MOVE" :: "REPORT" :: Nil
    val p = new Parser
    p.parse(in) match {
      case Success(dl) =>
        val r = new Robot(_b, new Directions(dl))
        r.walk
        assert(
          r.inPlace
          && r.point == Point(0, 0)
          && r.orientation == Orientation.South)
      case _ => assert(false)
    }
  }
  "A Robot" should "walk NORTH and SOUTH, ignoring out of bounds" in {
    val in: PreParsedDirectionsList = "PLACE 0,0,NORTH" :: "REPORT" :: "MOVE" :: "REPORT" :: "MOVE" :: "REPORT" :: "MOVE" :: "REPORT" :: "MOVE" :: "REPORT" :: "MOVE" :: "REPORT" :: "LEFT" :: "LEFT" :: "REPORT" :: "MOVE" :: "REPORT" :: "MOVE" :: "REPORT" :: "MOVE" :: "REPORT" :: "MOVE" :: "REPORT" :: "MOVE" :: "REPORT" :: Nil
    val p = new Parser
    p.parse(in) match {
      case Success(dl) =>
        val r = new Robot(_b, new Directions(dl))
        r.walk
        assert(
          r.inPlace
          && r.point == Point(0, 0)
          && r.orientation == Orientation.South)
      case _ => assert(false)
    }
  }
} // RobotSpec

// test PROBLEM.md examples
class RobotEgSpec(ignore: String) extends FlatSpec {

  private val _b = new Board

  "A Robot" should "handle eg1.txt" in {
    val in = Input.fromFile((os.pwd/"resources"/"eg1.txt").toString)
    val p = new Parser()
    p.parse(in) match {
      case Success(dl) =>
        val r = new Robot(_b, new Directions(dl))
        r.walk
        assert(
          r.inPlace
          && r.point == Point(0, 1)
          && r.orientation == Orientation.North)
      case _ => assert(false)
    }
  }
  "A Robot" should "handle eg2.txt" in {
    val in = Input.fromFile((os.pwd/"resources"/"eg2.txt").toString)
    val p = new Parser()
    p.parse(in) match {
      case Success(dl) =>
        val r = new Robot(_b, new Directions(dl))
        r.walk
        assert(
          r.inPlace
          && r.point == Point(0, 0)
          && r.orientation == Orientation.West)
      case _ => assert(false)
    }
  }
  "A Robot" should "handle eg3.txt" in {
    val in = Input.fromFile((os.pwd/"resources"/"eg3.txt").toString)
    val p = new Parser()
    p.parse(in) match {
      case Success(dl) =>
        val r = new Robot(_b, new Directions(dl))
        r.walk
        assert(
          r.inPlace
          && r.point == Point(3, 3)
          && r.orientation == Orientation.North)
      case _ => assert(false)
    }
  }
} // RobotEgSpec

// PLACE_OBJECT tests
class RobotPlaceObjectSpec(ignore: String) extends FlatSpec {

  "A Robot" should "handle PLACE_OBJECT but not act on it if not in PLACE" in {
    val b = new Board
    val in: PreParsedDirectionsList = "PLACE_OBJECT" :: Nil
    val p = new Parser
    p.parse(in) match {
      case Success(dl) =>
        val r = new Robot(b, new Directions(dl))
        r.walk
        assert(!r.inPlace)
      case _ => assert(false)
    }
  }
  "A Robot" should "handle PLACE_OBJECT and act on it if in PLACE" in {
    val b = new Board
    val in: PreParsedDirectionsList = "PLACE 0,0,NORTH" :: "MOVE" :: "PLACE_OBJECT" :: Nil
    val p = new Parser
    p.parse(in) match {
      case Success(dl) =>
        val r = new Robot(b, new Directions(dl))
        r.walk
        assert(
          r.inPlace && r.isBlocked(Point(0, 2))
          && r.orientation == Orientation.North)
      case _ => assert(false)
    }
  }
  "A Robot" should "be blocked by MOVE after PLACE_OBJECT" in {
    val b = new Board
    val in: PreParsedDirectionsList = "PLACE 0,0,NORTH" :: "PLACE_OBJECT" :: "MOVE" :: Nil
    val p = new Parser
    p.parse(in) match {
      case Success(dl) =>
        val r = new Robot(b, new Directions(dl))
        r.walk
        assert(
          r.inPlace && r.isBlocked(Point(0, 1))
          && r.point == Point(0, 0) // couldn't move, stays at same point
          && r.orientation == Orientation.North)
      case _ => assert(false)
    }
  }
  "A Robot" should "be blocked even if MOVEing around a PLACEd_OBJECT" in {
    val b = new Board
    val in: PreParsedDirectionsList = "PLACE 2,2,EAST" :: "PLACE_OBJECT" :: "LEFT" :: "MOVE" :: "RIGHT" :: "MOVE" :: "MOVE" :: "RIGHT" :: "MOVE" :: "RIGHT" :: "MOVE" :: Nil
    val p = new Parser
    p.parse(in) match {
      case Success(dl) =>
        val r = new Robot(b, new Directions(dl))
        r.walk
        assert(
          r.inPlace && r.isBlocked(Point(3, 2))
          && r.point == Point(4, 2) // couldn't move, stays at same point
          && r.orientation == Orientation.West)
      case _ => assert(false)
    }
  }
} // RobotPlaceObjectSpec
