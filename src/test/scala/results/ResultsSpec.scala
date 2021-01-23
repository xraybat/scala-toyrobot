package toyrobot.results

import org.scalatest.FlatSpec

import toyrobot.command._
import toyrobot.board._
import toyrobot.point._
import toyrobot.orientation._
import toyrobot.orientation.Orientation._

//@Ignore
class ResultsSpec(/*ignore: String*/) extends FlatSpec {

  private val _r = new Results
  private val _b = new Board
  private val _goodPt = Point(2, 2)
  private val _badPt = Point(5, 5)
  private val _o = Orientation.North

  "A Results list" should "add a successful PlaceRobot command result" in {
    val pr = PlaceRobot(_goodPt, _o)
    pr.place(_b) match {
      case (true, _, _) => 
        _r.add(pr)(true, None)
        assert(true)
      case _ => assert(false)
    }
  }
  "A Results list" should "add a failed PlaceRobot command result" in {
    val pr = PlaceRobot(_badPt, _o)
    pr.place(_b) match {
      case (false, _, _) => 
        _r.add(pr)(false, Some(_b))
        assert(true)
      case _ => assert(false)
    }
  }
  "A Results list" should "add a failed PlaceObject command result" in {
    val pr = PlaceRobot(_badPt, _o)
    val po = PlaceObject()
    pr.place(_b) match {
      case (false, _, _) => 
        _r.add(po)(false)
        assert(true)
      case _ => assert(false)
    }
  }
  "A Results list" should "add a failed Move command result" in {
    val pr = PlaceRobot(_badPt, _o)
    val m = Move()
    m.move(_b, _badPt, _o) match {
      case (false, pt) => 
        _r.add(m)(pt, _b)
        assert(true)
      case _ => assert(false)
    }
  }
  "A Results list" should "add a successful Report command result" in {
    val pr = PlaceRobot(_goodPt, _o)
    val r = Report()
    pr.place(_b) match {
      case (true, _, _) => 
        _r.add(r)(true, Some(_goodPt), Some(_o))
        assert(true)
      case _ => assert(false)
    }
  }
  "A Results list" should "add a failed Report command result" in {
    val pr = PlaceRobot(_badPt, _o)
    val r = Report()
    pr.place(_b) match {
      case (false, _, _) => 
        _r.add(r)(false, None, None)
        assert(true)
      case _ => assert(false)
    }
  }
  "A Results list" should "have six entries by the end of this Spec" in {
    _r.print
    assert(_r.list.length == 6)
  }
} // ResultsSpec
