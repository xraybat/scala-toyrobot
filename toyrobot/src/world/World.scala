package toyrobot.world

import toyrobot.input._
import toyrobot.input.Input.PreParsedDirectionsList
import toyrobot.board._
import toyrobot.parser._
import toyrobot.directions._
import toyrobot.robot._
import toyrobot.mylist.results._

// leave input and parse outside of World, but handle
// robot here.
class World(val directions: Directions) {

  private val _board = new Board
  private val _robot = new Robot(_board, directions)

  def robotWalk: Results = _robot.walk

} // World