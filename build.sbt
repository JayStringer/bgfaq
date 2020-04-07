name := "bgfaq"

version := "0.1"

scalaVersion := "2.13.1"


lazy val bgfaq =
  (project in file("."))
    .aggregate(endpoints, service)


lazy val endpoints =
  (project in file("endpoints"))
    .settings(libraryDependencies ++= Dependencies.Modules.endpoints)
    .dependsOn(models)


lazy val service =
  (project in file("service"))
    .settings(libraryDependencies ++= Dependencies.Modules.service)
//    .enablePlugins(DockerPlugin)
    .dependsOn(endpoints)

lazy val models = (project in file("models"))


//lazy val dockerSettings = Seq(
//  (packageName in Docker) := "bgfaq",
//  dockerBaseImage := "openjdk:8u191-jre-alpine3.8",
//  dockerExposedPorts := List(8080)
//)
//
//lazy val


