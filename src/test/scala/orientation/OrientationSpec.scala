package orientation

import org.scalatest.FlatSpec
import orientation.Orientation._

//@Ignore
class OrientationSpec(/**/ignore: String/**/) extends FlatSpec {
  "The Orientation enumeration" should "have uppercase values" in {
    assert(Orientation.North.toString == "NORTH"
           && Orientation.East.toString == "EAST"
           && Orientation.South.toString == "SOUTH"
           && Orientation.West.toString == "WEST")
  }
  "The Orientation enumeration" should "allow var setting" in {
    var orientation: Orientation = Orientation.North
    assert(true)
  }
} // OrientationSpec