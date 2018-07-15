name := "Eidos-backend"

version := "0.0.1"

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  "com.twitter" %% "finatra-http" % "17.10.0",


  "org.mongodb.scala" %% "mongo-scala-driver" % "2.1.0",

  "com.mpatric" % "mp3agic" % "0.9.1",

  "org.scalactic" %% "scalactic" % "3.0.4",
  "org.scalatest" %% "scalatest" % "3.0.4" % "test",


  "net.liftweb" %% "lift-json" % "3.1.1"
)
