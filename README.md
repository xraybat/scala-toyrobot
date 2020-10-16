# scala-toy-robot

scala toy robot

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

## to see the three examples in PROBLEM.md

    $sbt "run src/resources/eg1.txt"
    $sbt "run src/resources/eg2.txt"
    $sbt "run src/resources/eg3.txt"

## todo (for further investigation)

## requirements

## general notes

* have used scala as a general purpose language
* with certain scala-ecosystem additions, most notably:
    - "org.scalatest" % "scalatest_2.13" % "3.0.8" (standard scala test framework)
    - "com.lihaoyi" %% "os-lib" % "0.7.1" (see [os-lib](https://github.com/lihaoyi/os-lib))
    - "com.lihaoyi" %% "fastparse" % "2.2.2" (see [fastparse](http://www.lihaoyi.com/fastparse/))