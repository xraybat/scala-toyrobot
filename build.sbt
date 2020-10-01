name := "helloworld2-with-sbt"

organization := "au.com.carringbushsw"

version := "1.0-SNAPSHOT"

scalaVersion := "2.13.1"

scalacOptions ++= Seq("-unchecked", "-deprecation")

// https://mvnrepository.com/artifact/org.scalatest/scalatest_2.12
libraryDependencies += "org.scalatest" % "scalatest_2.13" % "3.0.8"