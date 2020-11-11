package toyrobot.command

import org.scalatest.FlatSpec

import toyrobot.command.Command._

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
} // CommandSpec