package directions

import org.scalatest.FlatSpec

import input._

class DirectionsSpec(/*ignore: String*/) extends FlatSpec {
  "A PreParsedDirectionsList parse" should "parse 'PLACE X,Y,F'" in {
    val place = Input.fromList(
      "PLACE 1,2,NORTH" :: Nil)
    val d = new Directions
    val p = d.parse(place)
    assert(p && d.inPlace)
  }
  "A PreParsedDirectionsList parse" should "ignore all when no 'PLACE'" in {
    val noPlace = Input.fromList(
      "LEFT" :: "RIGHT" :: "REPORT" :: Nil)
    val d = new Directions
    val p = d.parse(noPlace)
    assert(!p && !d.inPlace)
  }
  "A PreParsedDirectionsList parse" should "find 'PLACE' first and add 'REPORT'" in {
    val place = Input.fromList(
      "LEFT" :: "RIGHT" :: "PLACE 1,2,NORTH" :: "REPORT" :: Nil)
    val d = new Directions
    val p = d.parse(place)
    assert(p && d.inPlace)
  }
  "A PreParsedDirectionsList parse" should "handle multiple 'PLACE's" in {
    val place = Input.fromList(
      "PLACE 1,2,NORTH" :: "REPORT" :: "PLACE 3,4,SOUTH" :: Nil)
    val d = new Directions
    val p = d.parse(place)
    assert(p && d.inPlace)
  }
} // DirectionsSpec
