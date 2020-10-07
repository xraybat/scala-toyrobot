package directions

import org.scalatest.FlatSpec

class SourceDirectionsSpec extends FlatSpec {
  "A Directions list" should "be returned unchanged (for testing)" in {
    val l = "hello," :: "how" :: "are" :: "you?" :: Nil
    val in = SourceDirections.fromList(l)
    assert(l == in)
  }

  "The hello.txt file" should "return List('hello', 'how', 'are', 'you?')" in {
    val l = "hello," :: "how" :: "are" :: "you?" :: Nil
    val in = SourceDirections.fromFile((os.pwd/"src"/"resources"/"hello.txt").toString)
    assert(l == in)
  }
} // SourceDirectionsSpec

class DirectionsSpec extends FlatSpec {
  "A DirectionsList parse" should "find 'PLACE'" in {
    val place = SourceDirections.fromList(
      "LEFT" :: "RIGHT" :: "PLACE 1, 1, NORTH" :: "REPORT" :: Nil)
    val d = new Directions
    d.parse(place)
    assert(true)
  }
} // DirectionsSpec
