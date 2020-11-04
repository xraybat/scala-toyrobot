import mill._, scalalib._

object toyrobot extends ScalaModule {
  def artifactName = "toy-robot"
  def scalaVersion = "2.13.1"
  def publishVersion = "0.0.1"

  def ivyDeps = Agg(
    ivy"com.lihaoyi::os-lib:0.7.1",
    ivy"com.lihaoyi::fastparse:2.2.2"
  )

  object test extends Tests {
    def testFrameworks = Seq("org.scalatest.tools.Framework")

    def ivyDeps = Agg(
      ivy"org.scalatest::scalatest::3.0.8"
    )
  }
}
