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
    .enablePlugins(dockerPlugins: _*)
    .settings(dockerCommonSettings)
    .settings(libraryDependencies ++= Dependencies.Modules.service)
    .dependsOn(endpoints)


lazy val models =
  (project in file("models"))
    .settings(libraryDependencies ++= Dependencies.Modules.models)


lazy val dockerPlugins = Seq(DockerPlugin, AshScriptPlugin, JavaAppPackaging)

lazy val dockerCommonSettings = Seq(
  dockerBaseImage := "openjdk:8u191-alpine3.8",
  dockerExposedPorts := Seq(8080))