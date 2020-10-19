package commands

import org.scalatest.FlatSpec
import commands.Commands._

//@Ignore
class CommandsSpec(/*ignore: String*/) extends FlatSpec {
  "The Commands" should "have uppercase values" in {
    assert(Place.toString == "PLACE"
           && Move.toString == "MOVE"
           && Left.toString == "LEFT"
           && Right.toString == "RIGHT"
           && Report.toString == "REPORT")
  }
} // CommandsSpec