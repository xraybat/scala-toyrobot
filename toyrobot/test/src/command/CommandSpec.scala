package toyrobot.command

import toyrobot.command.Command._

import org.scalatest.FlatSpec
//@Ignore
class CommandSpec(/*ignore: String*/) extends FlatSpec {
  "The Commands" should "have uppercase values" in {
    assert(Place.toString == "PLACE"
           && Move.toString == "MOVE"
           && Left.toString == "LEFT"
           && Right.toString == "RIGHT"
           && Report.toString == "REPORT")
  }
} // CommandSpec