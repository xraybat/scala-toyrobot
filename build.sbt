name := "toyrobot"

organization := "au.com.example"

version := "1.0-SNAPSHOT"

scalaVersion := "2.13.1"

scalacOptions ++= Seq("-unchecked", "-deprecation")

// https://mvnrepository.com/artifact/org.scalatest/scalatest_2.12
libraryDependencies += "org.scalatest" % "scalatest_2.13" % "3.0.8"
libraryDependencies += "com.lihaoyi" %% "os-lib" % "0.7.1"
libraryDependencies += "com.lihaoyi" %% "fastparse" % "2.2.2"