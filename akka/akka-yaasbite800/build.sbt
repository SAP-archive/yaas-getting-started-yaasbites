name := """akka-yaasbite800"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
  "org.pegdown" % "pegdown" % "1.6.0"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

testOptions := Seq(Tests.Argument(TestFrameworks.ScalaTest, "-o", "-h", "target/test-reports"))