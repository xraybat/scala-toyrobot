import helloworld.HelloWorld

object Main extends App {
//object Main {
//  def main(args: Array[String]): Unit = {
    new HelloWorld().print

    val helloworld = new HelloWorld
    println(helloworld.sayThis("done!"))
//  }
} // Main