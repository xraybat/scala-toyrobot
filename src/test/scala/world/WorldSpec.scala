package toyrobot.world

import org.scalatest.FlatSpec

import toyrobot.input._
import toyrobot.input.Input.PreParsedDirectionsList
import toyrobot.board._
import toyrobot.parser._
import toyrobot.directions._
import toyrobot.robot._

//@Ignore
class WorldSpec(/*ignore: String*/) extends FlatSpec {

  "A World" should "accept a directions list and make a robot walk" in {
    val in: PreParsedDirectionsList = "PLACE 1,2,NORTH" :: "MOVE" :: "REPORT" :: Nil
    val p = new Parser
    p.parse(in) match {
      case Some(dl) => 
        val w = new World(new Directions(dl))
        w.robotWalk
        assert(true)
      case _ => assert(false)
    }
  }
} // WorldSpec
