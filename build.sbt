import scala.sys.process.Process

name := "sbt-test"

version := "0.1"

scalaVersion := "2.12.4"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"

val gitHeadCommitSha = taskKey[String]("Determines the current git commit SHA")
gitHeadCommitSha := Process("git rev-parse HEAD").lineStream.head

