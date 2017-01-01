packAutoSettings

name := "crud"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "net.databinder" %% "unfiltered-filter" % "0.8.4",
  "net.databinder" %% "unfiltered-netty-server" % "0.8.4",
  "org.json4s" %% "json4s-jackson" % "3.2.11",
  "com.lihaoyi" %% "scalatags" % "0.5.2",
  "org.postgresql" % "postgresql" % "9.4-1200-jdbc41",
  //"org.scalikejdbc" %% "scalikejdbc" % "2.5.0",
  "com.typesafe.play" %% "anorm" % "2.5.2"
)

scalacOptions ++= Seq("-feature", "-deprecation")