/**
  * Created by psc on 30/03/17.
  */

package helloworld

class HelloWorld {
  def print =
    println(say)

  def say : String =
    "hello, world!"

  def sayThis(str: String) : String =
    str

  def throwup =
    throw new Exception

} // HelloWorld