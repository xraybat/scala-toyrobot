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

  "A placeRobot Command" should "be inplace, with a Point and Orientation, when inbounds" in {
    placeRobot(board, Point(1, 2), Orientation.North) match {
      case (true, pt, o) => assert(pt == Point(1, 2) && o == Orientation.North)
      case _ => assert(false)
    }
  }
  "A placeRobot Command" should "NOT be inplace when NOT inbounds" in {
    placeRobot(board, Point(5, 5), Orientation.North) match {
      case (false, _, _) => assert(true)
      case _ => assert(false)
    }
  }


  

} // CommandSpec