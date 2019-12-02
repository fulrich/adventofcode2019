name := "adventofcode2019"

version := "0.1"

scalaVersion := "2.13.1"

// Production
libraryDependencies ++= Seq(
  "org.scalactic" %% "scalactic" % "3.1.0"
)

// Test
libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.1.0" % "test"
)
