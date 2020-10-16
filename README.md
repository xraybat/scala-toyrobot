# scala-toy-robot

a scala toy robot

## to run in general

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

    $sbt "run src/resources/eg1.txt"
    $sbt "run src/resources/eg2.txt"
    $sbt "run src/resources/eg3.txt"

## major TODOs (for further investigation)

```
/home/psc/src/scala/REA/toyrobot/src/main/scala/parser/Parser.scala:
   16  
   18:   // @TODO: remove, and rely only on Robot.inPlace()?? @ANS: possibly,
   19    // further investigation...(and let all Commands go thru)

/home/psc/src/scala/REA/toyrobot/src/main/scala/robot/Robot.scala:
    1: // @TODO: turn into a castor actor; a robot *is* an actor
    2  // after all...
    .
   22:   // @TODO: derive from Parser.inPlace()?? @ANS: no, leave logic
   23    // here and remove Parser.inPlace() logic...
   24    // @MUTABLE:
   30    
   31:   // @TODO: make walk() recursive walk(point)?? @ANS: no need, too
   32    // simple
   33    def walk(): Unit = {
```

## requirements

built using:

    scala 2.13.x
    sbt 1.3.x

see [installing scala](https://www.scala-lang.org/download/)

## general notes

* have used scala as a general purpose language
* with certain scala-ecosystem additions, most notably:
    - "org.scalatest" % "scalatest_2.13" % "3.0.8" (standard scala test framework)
    - "com.lihaoyi" %% "os-lib" % "0.7.1" (see [os-lib](https://github.com/lihaoyi/os-lib))
    - "com.lihaoyi" %% "fastparse" % "2.2.2" (see [fastparse](http://www.lihaoyi.com/fastparse/))