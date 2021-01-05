package toyrobot.directions

import toyrobot.command._

import scala.collection.mutable.ListBuffer

// companion object
object Directions {
  type DirectionsList = List[Command]

  // mutable ListBuffer only visible outside as immutable List
  type DirectionsListBuffer = ListBuffer[Command]
}
