package parser

import org.scalatest.FlatSpec

import input._

class ParserSpec(/*ignore: String*/) extends FlatSpec {
  "A PreParsedDirectionsList parse" should "not parse rubbish" in {
    val rubbish = Input.fromList(
      "hello," :: "how" :: "are" :: "you?" :: Nil)
    val p = new Parser
    assert(!p.parse(rubbish))
  }
  "A PreParsedDirectionsList parse" should "parse PLACE X,Y,F" in {
    val place = Input.fromList(
      "PLACE 1,2,NORTH" :: Nil)
    val p = new Parser
    assert(p.parse(place))
  }
  "A PreParsedDirectionsList parse" should "even when no PLACE" in {
    val noPlace = Input.fromList(
      "LEFT" :: "RIGHT" :: "REPORT" :: Nil)
    val p = new Parser
    assert(p.parse(noPlace))
  }
  "A PreParsedDirectionsList parse" should "parse subsequent PLACE and REPORT" in {
    val place = Input.fromList(
      "LEFT" :: "RIGHT" :: "PLACE 1,2,NORTH" :: "REPORT" :: Nil)
    val p = new Parser
    assert(p.parse(place))
  }
  "A PreParsedDirectionsList parse" should "parse multiple PLACEs" in {
    val place = Input.fromList(
      "PLACE 2,3,NORTH" :: "REPORT" :: "PLACE 3,4,SOUTH" :: Nil)
    val p = new Parser
    assert(p.parse(place))
  }
} // ParserSpec
