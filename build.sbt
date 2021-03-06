name := "slick-postgres-testkit"

organization := "com.snapswap"

version := "1.0.6"

scalaVersion := "2.11.8"

scalacOptions := Seq(
  "-feature",
  "-unchecked",
  "-deprecation",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-Xfatal-warnings",
  "-Xlint",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Xfuture",
  "-Ywarn-unused-import",
  "-encoding",
  "UTF-8"
)

libraryDependencies ++= {
  val akkaV = "2.4.11"
  Seq(
    "org.flywaydb" % "flyway-core" % "5.0.7",
    "com.opentable.components" % "otj-pg-embedded" % "0.12.0",
    "com.typesafe.slick" %% "slick" % "3.2.1" % "provided",
    "com.typesafe" % "config" % "1.3.1" % "provided",
    "org.scalatest" %% "scalatest" % "3.0.1" % "test",
    "ch.qos.logback" % "logback-classic" % "1.1.7" % "test"
  )
}

fork in Test := true
