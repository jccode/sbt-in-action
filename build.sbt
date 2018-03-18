import scala.sys.process.Process

name := "sbt-in-action"

// custom keys for this build
val gitHeadCommitSha = taskKey[String]("Determines the current git commit SHA")
val makeVersionProperties = taskKey[Seq[File]]("Makes a version.properties file.")

lazy val commonSettings = Seq(
  version := "0.1",
  organization := "com.github.jccode.sbttest",
  scalaVersion := "2.12.4"
)

gitHeadCommitSha in ThisBuild := Process("git rev-parse HEAD").lineStream.head


lazy val common = (project in file("common"))
  .settings(
    commonSettings,
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test",
    makeVersionProperties := {
      val propFile = new File((resourceManaged in Compile).value, "version.properties")
      val content = s"version=${gitHeadCommitSha.value}"
      IO.write(propFile, content)
      Seq(propFile)
    }
  )

lazy val analytics = project.in(file("analytics"))
  .settings(commonSettings)
  .dependsOn(common)


lazy val website = {
  Project("website", file("website"))
    .settings(commonSettings)
    .dependsOn(common)
}


//resourceGenerators in Compile += makeVersionProperties

