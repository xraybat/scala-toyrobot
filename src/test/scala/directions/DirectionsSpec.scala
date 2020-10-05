package directions

import org.scalatest.FlatSpec

class DirectionsSpec extends FlatSpec {
  "A Directions list" should "be returned unchanged (for testing)" in {
    val l = "hello," :: "how" :: "are" :: "you?" :: Nil
    val in = Directions.fromList(l)
    assert(l == in)
  }

  "The hello.txt file" should "return List('hello', 'how', 'are', 'you?')" in {
    val l = "hello," :: "how" :: "are" :: "you?" :: Nil
    val in = Directions.fromFile((os.pwd/"src"/"resources"/"hello.txt").toString)
    assert(l == in)
  }

} // DirectionsSpec