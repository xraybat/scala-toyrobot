package commands

import org.scalatest.FlatSpec

//@Ignore
class CommandsSpec(/**/ignore: String/**/) extends FlatSpec {
  "The Commands enumeration" should "have uppercase values" in {
    assert(Commands.Place.toString == "PLACE"
           && Commands.Move.toString == "MOVE"
           && Commands.Left.toString == "LEFT"
           && Commands.Right.toString == "RIGHT"
           && Commands.Report.toString == "REPORT")
  }
} // CommandsSpec