package toyrobot.results

import org.scalatest.FlatSpec

import toyrobot.command._
import toyrobot.board._
import toyrobot.point._
import toyrobot.orientation._
import toyrobot.orientation.Orientation._

//@Ignore
class ResultsSpec(ignore: String) extends FlatSpec {

  private val _r = new Results
  private val _b = new Board
  private val _pt = Point(0, 0)
  private val _o = Orientation.North
  private val _inPlace = true
  private val _notInPlace = false

  "A Results list" should "add a successful PlaceRobot command result" in {
    val pr = PlaceRobot(_pt, _o)
    //assert(pr.place(b) == (inPlace, _, _))
    //r.addResult(pr)(inPlace, None)
    assert(false)
  }
  "A Results list" should "add a failed PlaceRobot command result" in {
    //_results.addResult(placeRobot)(_inPlace, Some(board))
    assert(false)
  }
  "A Results list" should "add a failed PlaceObject command result" in {
    //_results.addResult(placeObject)(inPlace)
    assert(false)
  }
  "A Results list" should "add a failed Move command result" in {
    //_results.addResult(move)(pt, board)
    assert(false)
  }
  "A Results list" should "add a successful Report command result" in {
    //_results.addResult(report)(inPlace, Some(point), Some(orientation))
    assert(false)
  }
  "A Results list" should "add a failed Report command result" in {
    //_results.addResult(report)(inPlace, None, None)
    assert(false)
  }
  "A Results list" should "have six entries by the end of this Spec" in {
    //_results.addResult(report)(inPlace, None, None)
    assert(false)
  }
} // ResultsSpec
