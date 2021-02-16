package toyrobot.results

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
