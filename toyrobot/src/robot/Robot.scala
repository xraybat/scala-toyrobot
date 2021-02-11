package toyrobot.robot

import toyrobot.board._
import toyrobot.point._
import toyrobot.parser._
import toyrobot.directions._
import toyrobot.results._
import toyrobot.command._
import toyrobot.orientation._
import toyrobot.orientation.Orientation._

// companion object
object Robot {
  type State = (Boolean, Point, Orientation, Option[String])
  type Error = String
}

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
      processCommand(cmd) match {
        /* @TODO
        case Right((ip, pt, o, info)) =>
          _inPlace = ip; _currPoint = pt; _currOrientation = o
          info match {
            case Some(msg) => _results.add(msg)
            case _ =>
          }*/
        case scala.Right(state) =>
          _inPlace = state._1; _currPoint = state._2; _currOrientation = state._3
          state._4 match {
            case Some(msg) => _results.add(msg)
            case _ =>
          }
        case scala.Left(error) => _results.add(error)
      }
    }

    def processCommand(cmd: Command): scala.Either[Robot.Error, Robot.State] = {
      cmd match {
        case placeRobot: PlaceRobot =>
          placeRobot.place(board) match {
            case (true, pt, o) => scala.Right((true, pt, o, Some(Results.msg(placeRobot)(true, None))))
            case (false, pt, _) => scala.Left(Results.msg(placeRobot)(false, Some(board)))
          }

        case placeObject: PlaceObject =>
          inPlace match {
            case true =>
              placeObject.place(board, point, orientation)
              scala.Right((true, point, orientation, None))
            case false => scala.Left(Results.msg(placeObject)(inPlace))
          }

        case move: Move =>
          inPlace match {
            case true =>
              move.move(board, point, orientation) match {
                case (true, pt) => scala.Right((true, pt, orientation, None))
                case (false, pt) => scala.Left(Results.msg(move)(pt, board))
              }
            case false => scala.Left("can't move when not inPlace") // @TODO
          }

        case left: Left =>
          inPlace match {
            case true => scala.Right((inPlace, point, left.turn(orientation), None))
            case false => scala.Left("can't turn when not inPlace") // @TODO
          }

        case right: Right =>
          inPlace match {
            case true => scala.Right((inPlace, point, right.turn(orientation), None))
            case false => scala.Left("can't turn when not inPlace") // @TODO
          }

        case report: Report =>
          inPlace match {
            case true => scala.Right((inPlace, point, orientation, Some(Results.msg(report)(inPlace, Some(point), Some(orientation)))))
            case false => scala.Left(Results.msg(report)(inPlace, None, None))
          }
      } // match
    } // processCommand

    _results

  } // walk
} // Robot