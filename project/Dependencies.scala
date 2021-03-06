import sbt._


object Dependencies {

  object Versions {

    object scalatest {
      val core = "3.1.1"
    }

    object mongoDB {
      val reactive = "0.20.10"
      val scalaDriver = "2.9.0"
    }

    object tapir {
      val core = "0.13.1"
    }

    object circe {
      val core = "0.13.0"
    }

    object pureconf {
      val core = "0.12.3"
    }

  }


  object Libraries {

    object scalatest {
      val core = "org.scalatest" %% "scalatest" % Versions.scalatest.core % "test"
    }

    object mongoDB {
      val reactive = "org.reactivemongo" %% "reactivemongo" % Versions.mongoDB.reactive
      val scalaDriver = "org.mongodb.scala" %% "mongo-scala-driver" % Versions.mongoDB.scalaDriver
    }

    object tapir {
      val core = "com.softwaremill.sttp.tapir" %% "tapir-core" % Versions.tapir.core
      val akka = "com.softwaremill.sttp.tapir" %% "tapir-akka-http-server" % Versions.tapir.core
      val circe = "com.softwaremill.sttp.tapir" %% "tapir-json-circe" % Versions.tapir.core
    }

    object circe {
      val all = Seq(
        "io.circe" %% "circe-generic",
        "io.circe" %% "circe-parser",
      ).map(_ % Versions.circe.core)
    }

    object pureconf {
      val core = "com.github.pureconfig" %% "pureconfig" % Versions.pureconf.core
    }
  }


  object Modules {
    import Libraries._

    val endpoints = Seq(
      scalatest.core,
      tapir.core,
      tapir.circe,
    ) ++ circe.all

    val service = Seq(
      tapir.akka,
      mongoDB.scalaDriver,
      mongoDB.reactive,
      pureconf.core,
    )

    val models = Seq(
      mongoDB.scalaDriver,
    ) ++ circe.all

  }

}
