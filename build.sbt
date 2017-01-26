packAutoSettings

name := "crud"

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies ++= Seq(
  "ws.unfiltered" % "unfiltered-filter_2.12" % "0.9.0-beta2",
  "ws.unfiltered" % "unfiltered-netty-server_2.12" % "0.9.0-beta2",
  "org.json4s" %% "json4s-jackson" % "3.5.0",
  "com.lihaoyi" %% "scalatags" % "0.6.2",
  "org.postgresql" % "postgresql" % "9.4-1200-jdbc41",
  "com.github.takezoe" %% "scala-jdbc" % "1.0.5"
)

scalacOptions ++= Seq("-feature", "-deprecation")