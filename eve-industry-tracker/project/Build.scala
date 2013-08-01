import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "eve-industry-tracker"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean,
    "org.webjars" % "angularjs" % "1.1.5-1",
    "org.webjars" % "requirejs" % "2.1.1",
    "org.webjars" % "webjars-play" % "2.1.0-1",
    "org.webjars" % "bootstrap" % "2.3.2"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
  )

}
