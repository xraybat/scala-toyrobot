package toyrobot.mylist

import scala.collection.mutable.ListBuffer

class MyList[T] {

  // mutable list internally for building; still `val`, tho.
  private val _list: ListBuffer[T] = new ListBuffer[T]
  
  def list: List[T] = _list.toList  // immutable
  def add(that: T): Unit = _list += that

  override def toString: String = _list.mkString("\n")
}
