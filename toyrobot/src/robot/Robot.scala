package toyrobot.robot

import toyrobot.board._
import toyrobot.point._
import toyrobot.parser._
import toyrobot.directions._
import toyrobot.results._
import toyrobot.command._
import toyrobot.orientation._
import toyrobot.orientation.Orientation._

class Robot(val board: Board, val directions: Directions) {

  // @MUTABLE:
  var _currPoint: Point = _
  def point: Point = _currPoint
  var _currOrientation: Orientation = _
  def orientation: Orientation = _currOrientation

  // @MUTABLE:
  private var _inPlace: Boolean = false
  def inPlace: Boolean = _inPlace

  def inBounds(pt: Point): Boolean = board.inBounds(pt)
  def outBounds(pt: Point): Boolean = !inBounds(pt)
  
  def isBlocked(pt: Point): Boolean = board.isBlocked(pt)

  val _results = new Results

  def walk: Results = {

    directions.list.foreach { cmd =>
      processCommand(cmd)
    }

    def processCommand(cmd: Command): Unit = {
      cmd match {
        case placeRobot: PlaceRobot =>
          placeRobot.place(board) match {
            case (true, pt, o) =>
              _inPlace = true; _currPoint = pt; _currOrientation = o
              _results.add(Results.msg(placeRobot)(_inPlace, None))
            case (false, pt, _) =>
              _inPlace = false
              _results.add(Results.msg(placeRobot)(_inPlace, Some(board)))
          }

        case placeObject: PlaceObject =>
          if (inPlace)
            placeObject.place(board, point, orientation)
          else
            _results.add(Results.msg(placeObject)(inPlace))

        case move: Move =>
          if (inPlace) {
            move.move(board, point, orientation) match {
              case (true, pt) => _currPoint = pt
              case (false, pt) => 
                _results.add(Results.msg(move)(pt, board))
            }
          }

        case left: Left =>
          if (inPlace) _currOrientation = left.turn(orientation)
        case right: Right =>
          if (inPlace) _currOrientation = right.turn(orientation)

        case report: Report =>
          if (inPlace)
            _results.add(Results.msg(report)(inPlace, Some(point), Some(orientation)))
          else
            _results.add(Results.msg(report)(inPlace, None, None))

      } // match
    } // processCommand

    _results

  } // walk
} // Robot