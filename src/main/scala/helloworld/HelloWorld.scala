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