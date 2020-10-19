package orientation

import org.scalatest.FlatSpec
import orientation.Orientation._

//@Ignore
class OrientationSpec(/*ignore: String*/) extends FlatSpec {
  "The Orientation enumeration" should "have uppercase values" in {
    assert(Orientation.North.toString == "NORTH"
           && Orientation.East.toString == "EAST"
           && Orientation.South.toString == "SOUTH"
           && Orientation.West.toString == "WEST")
  }

  "A NORTH Orientation" should "be EAST after turning RIGHT" in {
    assert(turnRight(North) == East)
  }
  "An EAST Orientation" should "be SOUTH after turning RIGHT" in {
    assert(turnRight(East) == South)
  }
  "A SOUTH Orientation" should "be WEST after turning RIGHT" in {
    assert(turnRight(South) == West)
  }
  "A WEST Orientation" should "be NORTH after turning RIGHT" in {
    assert(turnRight(West) == North)
  }
  "A NORTH Orientation" should "be WEST after turning LEFT" in {
    assert(turnLeft(North) == West)
  }
  "An WEST Orientation" should "be SOUTH after turning LEFT" in {
    assert(turnLeft(West) == South)
  }
  "A SOUTH Orientation" should "be EAST after turning LEFT" in {
    assert(turnLeft(South) == East)
  }
  "An EAST Orientation" should "be NORTH after turning LEFT" in {
    assert(turnLeft(East) == North)
  }

} // OrientationSpec
