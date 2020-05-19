package bgfaq

import akka.actor.ActorSystem
import akka.http.scaladsl._
import akka.http.scaladsl.server.Directives._
import akka.stream.Materializer

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn


object Bootstrap extends App {

  implicit val system: ActorSystem = ActorSystem("my-system")
  implicit val materializer: Materializer = Materializer(system)
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  val bindingFuture = Http().bindAndHandle(
    Routes.addGameRoute
      ~ Routes.getAllGamesRoute
      ~ Routes.queryGamesRoute
      ~ Routes.getGameRoute
      ~ Routes.getQuestionRoute,
    "0.0.0.0",
    8080
  )

  println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
  StdIn.readLine()
  bindingFuture
    .flatMap(_.unbind())
    .onComplete(_ => system.terminate())

}
