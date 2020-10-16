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

  "A NORTH Orientation" should "be EAST after turning RIGHT" in {
    assert(Orientation.turnRight(Orientation.North) == Orientation.East)
  }
  "An EAST Orientation" should "be SOUTH after turning RIGHT" in {
    assert(Orientation.turnRight(Orientation.East) == Orientation.South)
  }
  "A SOUTH Orientation" should "be WEST after turning RIGHT" in {
    assert(Orientation.turnRight(Orientation.South) == Orientation.West)
  }
  "A WEST Orientation" should "be NORTH after turning RIGHT" in {
    assert(Orientation.turnRight(Orientation.West) == Orientation.North)
  }
  "A NORTH Orientation" should "be WEST after turning LEFT" in {
    assert(Orientation.turnLeft(Orientation.North) == Orientation.West)
  }
  "An WEST Orientation" should "be SOUTH after turning LEFT" in {
    assert(Orientation.turnLeft(Orientation.West) == Orientation.South)
  }
  "A SOUTH Orientation" should "be EAST after turning LEFT" in {
    assert(Orientation.turnLeft(Orientation.South) == Orientation.East)
  }
  "An EAST Orientation" should "be NORTH after turning LEFT" in {
    assert(Orientation.turnLeft(Orientation.East) == Orientation.North)
  }

} // OrientationSpec