package toyrobot.parser

import org.scalatest.FlatSpec

import toyrobot.input._
import toyrobot.input.Input.PreParsedDirectionsList

import scala.util.{Try,Success,Failure}

class ParserSpec(/*ignore: String*/) extends FlatSpec {
  "A PreParsedDirectionsList parse" should "not parse rubbish" in {
    val rubbish: PreParsedDirectionsList = "hello," :: "how" :: "are" :: "you?" :: Nil
    val p = new Parser
    p.parse(rubbish) match {
      case Success(dl) => assert(false)
      case Failure(e) => assert(true)
    }
  }
  "A PreParsedDirectionsList parse" should "parse PLACE X,Y,F" in {
    val place: PreParsedDirectionsList = "PLACE 1,2,NORTH" :: Nil
    val p = new Parser
    p.parse(place) match {
      case Success(dl) => assert(true)
      case Failure(e) => assert(false)
    }
  }
  "A PreParsedDirectionsList parse" should "even when no PLACE" in {
    val noPlace: PreParsedDirectionsList = "LEFT" :: "RIGHT" :: "REPORT" :: Nil
    val p = new Parser
    p.parse(noPlace) match {
      case Success(dl) => assert(true)
      case Failure(e) => assert(false)
    }
  }
  "A PreParsedDirectionsList parse" should "parse subsequent PLACE and REPORT" in {
    val place: PreParsedDirectionsList = "LEFT" :: "RIGHT" :: "PLACE 1,2,NORTH" :: "REPORT" :: Nil
    val p = new Parser
    p.parse(place) match {
      case Success(dl) => assert(true)
      case Failure(e) => assert(false)
    }
  }
  "A PreParsedDirectionsList parse" should "parse multiple PLACEs" in {
    val place: PreParsedDirectionsList = "PLACE 2,3,NORTH" :: "REPORT" :: "PLACE 3,4,SOUTH" :: Nil
    val p = new Parser
    p.parse(place) match {
      case Success(dl) => assert(true)
      case Failure(e) => assert(false)
    }
  }
  "A PreParsedDirectionsList parse" should "parse PLACE_OBJECT" in {
    val placeObject: PreParsedDirectionsList = "PLACE 2,3,NORTH" :: "MOVE" :: "PLACE_OBJECT" :: Nil
    val p = new Parser
    p.parse(placeObject) match {
      case Success(dl) => assert(true)
      case Failure(e) => assert(false)
    }
  }
} // ParserSpec
