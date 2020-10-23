package input

import org.scalatest.FlatSpec

//@Ignore
class InputSpec(/*ignore: String*/) extends FlatSpec {
  "A PreParsedDirections list" should "be returned unchanged (for testing)" in {
    val l = "hello," :: "how" :: "are" :: "you?" :: Nil
    val in = Input.fromList(l)
    assert(l == in)
  }

  "The hello.txt file" should "return List('hello', 'how', 'are', 'you?')" in {
    val l = "hello," :: "how" :: "are" :: "you?" :: Nil
    val in = Input.fromFile((os.pwd/"resources"/"hello.txt").toString)
    assert(l == in)
  }
} // InputSpec
