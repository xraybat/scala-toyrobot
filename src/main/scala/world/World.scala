package toyrobot.world

import toyrobot.input._
import toyrobot.input.Input.PreParsedDirectionsList
import toyrobot.board._
import toyrobot.parser._
import toyrobot.directions._
import toyrobot.directions.Directions.DirectionsList
import toyrobot.robot._

// leave input and parse outside of World, but handle
// robot here.
class World(val directions: DirectionsList) {

  private val board = new Board
  private val robot = new Robot(board, directions)

  def robotWalk: Unit = {
    robot.walk
  }
} // World