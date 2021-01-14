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
}

class Directions(val directionsList: Directions.DirectionsList) {

  private val _prefix = "Robot.walk: "

  // @MUTABLE
  private var _resultsList: Directions.ResultsListBuffer = new Directions.ResultsListBuffer()

  def addResult(placeRobot: PlaceRobot, inPlace: Boolean, board: Option[Board]) = {
    if (inPlace)
      _resultsList += s"${_prefix}PLACEd at ${placeRobot.point}, ${placeRobot.orientation}"
    else
      _resultsList += s"${_prefix}can't PLACE at ${placeRobot.point} on a ${Some(board)} board!"
  }

  def addResult(placeObject: PlaceObject, inPlace: Boolean) =
    if (!inPlace) _resultsList += s"${_prefix}can't PLACE_OBJECTs until in PLACE!"

  def addResult(move: Move, point: Point, board: Board) = 
    if (board.inBounds(point) && board.isBlocked(point))
      _resultsList += s"${_prefix}can't MOVE to ${point} 'cos i'm blocked!"
    else
      _resultsList += s"${_prefix}can't MOVE to ${point} on a ${board} board 'cos it's out of bounds!"

  def addResult(report: Report, inPlace: Boolean, point: Option[Point], orientation: Option[Orientation]) = 
    if (inPlace)
      _resultsList += s"${_prefix}REPORTing from ${point.getOrElse(Point(-1, -1))}, ${orientation.getOrElse(Orientation.North)}"
    else
      _resultsList += s"${_prefix}REPORTing that i'm not in PLACE!"

  def printResults: Unit = _resultsList.foreach { println }

} // Directions