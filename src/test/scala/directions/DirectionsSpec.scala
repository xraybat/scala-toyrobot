package directions

import org.scalatest.FlatSpec

//@Ignore
class SourceDirectionsSpec(/**/ignore: String/**/) extends FlatSpec {
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
  "A DirectionsList parse" should "parse 'PLACE X,Y,F'" in {
    val place = SourceDirections.fromList(
      "PLACE 1,2,NORTH" :: Nil)
    val d = new Directions
    d.parse(place)
    assert(d.inPlace)
  }
  "A DirectionsList parse" should "ignore all when no 'PLACE'" in {
    val noPlace = SourceDirections.fromList(
      "LEFT" :: "RIGHT" :: "REPORT" :: Nil)
    val d = new Directions
    d.parse(noPlace)
    assert(!d.inPlace)
  }
  "A DirectionsList parse" should "find 'PLACE' first and add 'REPORT'" in {
    val place = SourceDirections.fromList(
      "LEFT" :: "RIGHT" :: "PLACE 1,2,NORTH" :: "REPORT" :: Nil)
    val d = new Directions
    d.parse(place)
    assert(d.inPlace)
  }
  /*"A DirectionsList parse" should "handle multiple 'PLACE's" in {
    val place = SourceDirections.fromList(
      "PLACE 1,2,NORTH" :: "REPORT" :: "PLACE 3,4,SOUTH" :: Nil)
    val d = new Directions
    d.parse(place)
    assert(d.inPlace)
  }*/
} // DirectionsSpec
