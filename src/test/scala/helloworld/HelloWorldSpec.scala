/**
  * Created by psc on 30/03/17.
  */

package helloworld

import org.scalatest.FlatSpec

class HelloWorldSpec extends FlatSpec {
  "A HelloWorld" should "say 'hello, world!'" in {
    assert(new HelloWorld().say === "hello, world!")
  }

  it should "throw Exception if I call throwup" in {
    //val helloworld = new HelloWorld()
    assertThrows[Exception] {
      new HelloWorld().throwup
    }
  }

  it should "return 'done!' if I call sayThis('done!')" in {
    assert(new HelloWorld().sayThis("done!") === "done!")
  }
} // HelloWorldSpec