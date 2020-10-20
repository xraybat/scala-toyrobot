# scala-toy-robot

a scala toy robot. minimal commentrary as smaller source files (Point, Board, Command, Orientation) are self-explanatory (with tests), while larger source files have some comments and rely on larger test suites as explanatory document.

## to run (in general)

from OS CLI (either):

    $ sbt "run <filename>"
    $ sbt run

from within  sbt:

    sbt> run <filename>
    sbt> run

and similar for testing:

    $ sbt test
    sbt> test

## to see the three examples required from PROBLEM.md

    $sbt "run resources/eg1.txt"
    $sbt "run resources/eg2.txt"
    $sbt "run resources/eg3.txt"

## some TODOs/QUs (for further investigation)

Searching 171 files for "@TODO:"
```
/home/psc/src/scala/REA/toyrobot/src/main/scala/robot/Robot.scala:
    1: // @TODO: turn into a castor actor; a robot *is* an actor
    2  // after all...
    3  package robot
    .
   29: // @TODO: make walk() recursive walk(point)?? @ANS: no need, too
   30  // simple
   31  def walk(): Unit = {
```
5 matches across 2 files

## requirements

built using:

    scala 2.13.x
    sbt 1.3.x

see [installing scala](https://www.scala-lang.org/download/)

tested on:

    Debian 10 (buster)
    macOS 10.15.x (catalina)

## general notes

* have used scala as a general purpose language
* with certain scala-ecosystem additions, most notably:
    - `"org.scalatest" % "scalatest_2.13" % "3.0.8"` (standard scala test framework)
    - `"com.lihaoyi" %% "os-lib" % "0.7.1"` (see [os-lib](https://github.com/lihaoyi/os-lib))
    - `"com.lihaoyi" %% "fastparse" % "2.2.2"` (see [fastparse](http://www.lihaoyi.com/fastparse/))