name := "adventofcode2019"

version := "0.1"

scalaVersion := "2.13.1"

// Production
libraryDependencies ++= Seq(
  "org.scalactic" %% "scalactic" % "3.1.0",
  "org.apache.commons" % "commons-math3" % "3.6.1"
)

// Test
libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.1.0" % "test",
  "com.github.fulrich" %% "test-charged" % "0.1.16" % "test"
)
