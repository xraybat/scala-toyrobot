package toyrobot.mylist

import scala.collection.mutable.ListBuffer

class MyList[T] {

  // @MUTABLE
  private var _list: ListBuffer[T] = new ListBuffer[T]
  
  def list: List[T] = _list.toList
  def add(s: T): Unit = _list += s

  override def toString: String = _list.mkString("\n")
}