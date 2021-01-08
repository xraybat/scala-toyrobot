package toyrobot.directions

import toyrobot.command._

import scala.collection.mutable.ListBuffer

// companion object
object Directions {
  type DirectionsList = List[Command]
  type DirectionsListBuffer = ListBuffer[Command]

  type ResultsList = List[String] // @TODO: first cut here??
  type ResultsListBuffer = ListBuffer[String]
}

class Directions(val directionsList: Directions.DirectionsList) {

  // @MUTABLE
  private var _resultsList: Directions.ResultsListBuffer = new Directions.ResultsListBuffer()

  def addResult(s: String) = _resultsList += s
  def printResults: Unit = _resultsList.foreach { println }

} // Directions