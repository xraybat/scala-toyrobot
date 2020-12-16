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
      PlaceRobot.toString == "PLACE"
      && PlaceObject.toString == "PLACE_OBJECT"
      && Move.toString == "MOVE"
      && Left.toString == "LEFT"
      && Right.toString == "RIGHT"
      && Report.toString == "REPORT")
  }
  "There is a new Command" should "called PLACE_OBJECT" in {
    assert(PlaceObject.toString == "PLACE_OBJECT")
  }

  private val board = new Board

  "A Command" should "place a robot in place, with a Point and Orientation, when in bounds" in {
    placeRobot(board, Point(1, 2), Orientation.North) match {
      case (true, pt, o) => assert(pt == Point(1, 2) && o == Orientation.North)
      case _ => assert(false)
    }
  }
  "A Command" should "NOT place a robot when NOT in bounds" in {
    placeRobot(board, Point(5, 5), Orientation.North) match {
      case (false, _, _) => assert(true)
      case _ => assert(false)
    }
  }

  "A Command" should "move when when in bounds and not blocked" in {
    move(board, Point(1, 2), Orientation.North) match {
      case (true, pt) => assert(pt == Point(1, 3))
      case _ => assert(false)
    }
  }
  "A Command" should "NOT move when out of bounds" in {
    move(board, Point(1, 4), Orientation.North) match {
      case (false, _) => assert(true)
      case _ => assert(false)
    }
  }
  "A Command" should "NOT move when blocked" in {
    val blockBoard = new Board
    val pt = Point(1, 2)
    val o = Orientation.North

    assert(!blockBoard.isBlocked(Point(1, 3)))
    blockBoard.Block(pt, o)
    assert(blockBoard.isBlocked(Point(1, 3)))

    move(blockBoard, pt, o) match {
      case (false, _) => assert(true)
      case _ => assert(false)
    }
  }

  "A Command" should "orientate WEST when turning left from NORTH" in {
    assert(left(Orientation.North) == Orientation.West)
  }
  "A Command" should "orientate EAST when turning right from NORTH" in {
    assert(right(Orientation.North) == Orientation.East)
  }
} // CommandSpec