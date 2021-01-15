package toyrobot.directions

import toyrobot.command._
import toyrobot.board._
import toyrobot.point._
import toyrobot.orientation._
import toyrobot.orientation.Orientation._

import scala.collection.mutable.ListBuffer

// companion object
object Directions {
  type DirectionsList = List[Command]
  type DirectionsListBuffer = ListBuffer[Command]

  type ResultsList = List[String] // @TODO: first cut here??
  type ResultsListBuffer = ListBuffer[String]

  private val ResultPrefix = "Robot.walk: "
}

import Directions._

class Directions(val directionsList: DirectionsList) {

  // @MUTABLE
  private var _results: ResultsListBuffer = new ResultsListBuffer()

  def addResult(placeRobot: PlaceRobot)(inPlace: Boolean, board: Option[Board]) =
    if (inPlace)
      _results += s"${ResultPrefix}PLACEd at ${placeRobot.point}, ${placeRobot.orientation}"
    else
      _results += s"${ResultPrefix}can't PLACE at ${placeRobot.point} on a ${board.getOrElse("(no board!)")} board!"

  def addResult(placeObject: PlaceObject)(inPlace: Boolean) =
    if (!inPlace) _results += s"${ResultPrefix}can't PLACE_OBJECTs until in PLACE!"

  def addResult(move: Move)(point: Point, board: Board) = 
    if (board.inBounds(point) && board.isBlocked(point))
      _results += s"${ResultPrefix}can't MOVE to ${point} 'cos i'm blocked!"
    else
      _results += s"${ResultPrefix}can't MOVE to ${point} on a ${board} board 'cos it's out of bounds!"

  def addResult(report: Report)(inPlace: Boolean, point: Option[Point], orientation: Option[Orientation]) = 
    if (inPlace)
      _results += s"${ResultPrefix}REPORTing from ${point.getOrElse("(no point!)")}, ${orientation.getOrElse("(no orientation!)")}"
    else
      _results += s"${ResultPrefix}REPORTing that i'm not in PLACE!"

  def printResults: Unit = _results.foreach { println }

} // Directions