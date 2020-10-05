package orientation

import org.scalatest.FlatSpec

class OrientationSpec extends FlatSpec {
  "The Orientation enumeration" should "have uppercase values" in {
    assert(Orientation.North.toString == "NORTH"
           && Orientation.East.toString == "EAST"
           && Orientation.South.toString == "SOUTH"
           && Orientation.West.toString == "WEST")
  }
} // OrientationSpec