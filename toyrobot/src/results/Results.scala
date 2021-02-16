package toyrobot.results

import toyrobot.command._
import toyrobot.board._
import toyrobot.point._
import toyrobot.orientation._
import toyrobot.orientation.Orientation._

import scala.collection.mutable.ListBuffer

// companion object
object Results {
  type ResultsList = List[String]
  type ResultsListBuffer = ListBuffer[String]
}

import Results._

class Results {

  // @MUTABLE
  private var _list: ResultsListBuffer = new ResultsListBuffer
  
  def list: ResultsList = _list.toList
  def add(s: String): Unit = _list += s

  override def toString: String = _list.mkString("\n")

} // Results
