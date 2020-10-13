package parser

import org.scalatest.FlatSpec

import input._

class ParserSpec(/*ignore: String*/) extends FlatSpec {
  "A PreParsedDirectionsList parse" should "parse 'PLACE X,Y,F'" in {
    val place = Input.fromList(
      "PLACE 1,2,NORTH" :: Nil)
    val p = new Parser
    assert(p.parse(place) && p.inPlace)
  }
  "A PreParsedDirectionsList parse" should "ignore all when no 'PLACE'" in {
    val noPlace = Input.fromList(
      "LEFT" :: "RIGHT" :: "REPORT" :: Nil)
    val p = new Parser
    assert(!p.parse(noPlace) && !p.inPlace)
  }
  "A PreParsedDirectionsList parse" should "find 'PLACE' first and add 'REPORT'" in {
    val place = Input.fromList(
      "LEFT" :: "RIGHT" :: "PLACE 1,2,NORTH" :: "REPORT" :: Nil)
    val p = new Parser
    assert(p.parse(place) && p.inPlace)
  }
  "A PreParsedDirectionsList parse" should "handle multiple 'PLACE's" in {
    val place = Input.fromList(
      "PLACE 1,2,NORTH" :: "REPORT" :: "PLACE 3,4,SOUTH" :: Nil)
    val p = new Parser
    assert(p.parse(place) && p.inPlace)
  }
} // ParserSpec
