package toyrobot.command

import org.scalatest.FlatSpec

import toyrobot.command.Command._
import toyrobot.board._
import toyrobot.point._
import toyrobot.orientation._
import toyrobot.orientation.Orientation._
  
//@Ignore
class CommandSpec(/*ignore: String*/) extends FlatSpec {
  "The Commands" should "have uppercase values" in {
    assert(
      KeywordPlaceRobot.toString == "PLACE"
      && KeywordPlaceObject.toString == "PLACE_OBJECT"
      && KeywordMove.toString == "MOVE"
      && KeywordLeft.toString == "LEFT"
      && KeywordRight.toString == "RIGHT"
      && KeywordReport.toString == "REPORT")
  }
  "There is a new Command" should "called PLACE_OBJECT" in {
    assert(KeywordPlaceObject.toString == "PLACE_OBJECT")
  }

  private val _b = new Board

  "A Command" should "place a robot in place, with a Point and Orientation, when in bounds" in {
    PlaceRobot(Point(1, 2), Orientation.North).place(_b) match {
      case (true, pt, o) => assert(pt == Point(1, 2) && o == Orientation.North)
      case _ => assert(false)
    }
  }
  "A Command" should "NOT place a robot when NOT in bounds" in {
    PlaceRobot(Point(5, 5), Orientation.North).place(_b) match {
      case (false, _, _) => assert(true)
      case _ => assert(false)
    }
  }

  "A Command" should "move when when in bounds and not blocked" in {
    Move().move(_b, Point(1, 2), Orientation.North) match {
      case (true, pt) => assert(pt == Point(1, 3))
      case _ => assert(false)
    }
  }
  "A Command" should "NOT move when out of bounds" in {
    Move().move(_b, Point(1, 4), Orientation.North) match {
      case (false, _) => assert(true)
      case _ => assert(false)
    }
  }
  "A Command" should "NOT move when an Object has been Place in front of it" in {
    val blockBoard = new Board
    val pt = Point(1, 2)
    val o = Orientation.North

    assert(!blockBoard.isBlocked(Point(1, 3)))
    PlaceObject().place(blockBoard, pt, o)
    assert(blockBoard.isBlocked(Point(1, 3)))

    Move().move(blockBoard, pt, o) match {
      case (false, _) => assert(true)
      case _ => assert(false)
    }
  }
  "A Command" should "move when an Object has been Placed elsewhere" in {
    val blockBoard = new Board
    val pt = Point(1, 2)
    val o = Orientation.North

    assert(!blockBoard.isBlocked(Point(1, 3)))
    PlaceObject().place(blockBoard, pt, o)
    assert(blockBoard.isBlocked(Point(1, 3)))

    Move().move(blockBoard, pt, Right().turn(o)) match {
      case (true, _) => assert(true)
      case _ => assert(false)
    }
  }

  "A Command" should "orientate WEST when turning left from NORTH" in {
    assert(Left().turn(Orientation.North) == Orientation.West)
  }
  "A Command" should "orientate EAST when turning right from NORTH" in {
    assert(Right().turn(Orientation.North) == Orientation.East)
  }
} // CommandSpec
