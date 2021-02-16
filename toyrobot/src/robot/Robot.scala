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
  var _state: Robot.State = (false, Point(0, 0), Orientation.North, None)
  def inPlace: Boolean = _state._1
  def point: Point = _state._2
  def orientation: Orientation = _state._3

  def inBounds(pt: Point): Boolean = board.inBounds(pt)
  def outBounds(pt: Point): Boolean = !inBounds(pt)
  
  def isBlocked(pt: Point): Boolean = board.isBlocked(pt)

  val _results = new Results

  def walk: Results = {

    directions.list.foreach { cmd =>
      processCommand(cmd) match {
        case scala.Right(state) =>
          _state = state
          val (ip, pt, o, info) = _state
          info match {
            case Some(msg) => _results.add(msg)
            case _ =>
          }
        case scala.Left(err) => _results.add(err)
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