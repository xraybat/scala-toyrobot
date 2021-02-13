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

  def msg(placeRobot: PlaceRobot)(inPlace: Boolean, board: Option[Board]): String =
    if (inPlace)
      s"${Prefix}PLACEd at ${placeRobot.point}, ${placeRobot.orientation}"
    else
      s"${Prefix}can't PLACE at ${placeRobot.point} on a ${board.getOrElse("(no board!)")} board!"

  def msg(placeObject: PlaceObject)(inPlace: Boolean): String =
    s"${Prefix}can't PLACE_OBJECTs until in PLACE!"

  def msg(move: Move)(point: Point, board: Board): String = 
    if (board.inBounds(point) && board.isBlocked(point))
      s"${Prefix}can't MOVE to ${point} 'cos i'm blocked!"
    else
      s"${Prefix}can't MOVE to ${point} on a ${board} board 'cos it's out of bounds!"

  def msg(report: Report)(inPlace: Boolean, point: Option[Point], orientation: Option[Orientation]): String = 
    if (inPlace)
      s"${Prefix}REPORTing from ${point.getOrElse("(no point!)")}, ${orientation.getOrElse("(no orientation!)")}"
    else
      s"${Prefix}REPORTing that i'm not in PLACE!"

  private val Prefix = "Robot.walk: "

} // Results

import Results._

class Results {

  // @MUTABLE
  private var _list: ResultsListBuffer = new ResultsListBuffer
  def list: ResultsList = _list.toList

  def add(s: String): Unit =  _list += s

  override def toString: String = _list.mkString("\n")

} // Results
