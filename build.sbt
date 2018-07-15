name := "Eidos-root"

version := "0.0.1"

scalaVersion := "2.12.4"

lazy val root = Project(id = "root", base = file(".")).aggregate(backend, frontend).dependsOn(backend, frontend)

lazy val backend = Project(id = "backend", base = file("backend")).aggregate(share).dependsOn(share)
lazy val frontend = Project(id = "frontend", base = file("frontend")).aggregate(share).dependsOn(share)

lazy val share = Project(id = "share", base = file("share"))