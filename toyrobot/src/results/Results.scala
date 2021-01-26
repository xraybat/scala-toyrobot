package toyrobot.results

import toyrobot.command._
import toyrobot.board._
import toyrobot.point._
import toyrobot.orientation._
import toyrobot.orientation.Orientation._

import scala.collection.mutable.ListBuffer

// companion object
object Results {
  type ResultsList = List[String] // @TODO: first cut here??
  type ResultsListBuffer = ListBuffer[String]

  private val Prefix = "Robot.walk: "
}

import Results._

class Results {

  // @MUTABLE
  private var _list: ResultsListBuffer = new ResultsListBuffer
  def list: ResultsList = _list.toList

  def add(placeRobot: PlaceRobot)(inPlace: Boolean, board: Option[Board]) =
    if (inPlace)
      _list += s"${Prefix}PLACEd at ${placeRobot.point}, ${placeRobot.orientation}"
    else
      _list += s"${Prefix}can't PLACE at ${placeRobot.point} on a ${board.getOrElse("(no board!)")} board!"

  def add(placeObject: PlaceObject)(inPlace: Boolean) =
    if (!inPlace) _list += s"${Prefix}can't PLACE_OBJECTs until in PLACE!"

  def add(move: Move)(point: Point, board: Board) = 
    if (board.inBounds(point) && board.isBlocked(point))
      _list += s"${Prefix}can't MOVE to ${point} 'cos i'm blocked!"
    else
      _list += s"${Prefix}can't MOVE to ${point} on a ${board} board 'cos it's out of bounds!"

  def add(report: Report)(inPlace: Boolean, point: Option[Point], orientation: Option[Orientation]) = 
    if (inPlace)
      _list += s"${Prefix}REPORTing from ${point.getOrElse("(no point!)")}, ${orientation.getOrElse("(no orientation!)")}"
    else
      _list += s"${Prefix}REPORTing that i'm not in PLACE!"

  override def toString: String = _list.mkString("\n")

} // Results