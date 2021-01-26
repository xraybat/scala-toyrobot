package toyrobot.directions

import toyrobot.command._

import scala.collection.mutable.ListBuffer

// companion object
object Directions {
  type DirectionsList = List[Command]
  type DirectionsListBuffer = ListBuffer[Command]
}

class Directions(val list: Directions.DirectionsList) {}
