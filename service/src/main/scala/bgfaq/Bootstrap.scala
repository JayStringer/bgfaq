package bgfaq

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.Materializer

import scala.io.StdIn


object Bootstrap extends App {

  implicit val system = ActorSystem("my-system")
  implicit val materializer = Materializer(system)
  implicit val executionContext = system.dispatcher

  val bindingFuture = Http().bindAndHandle(
    Routes.addGameRoute,
    "0.0.0.0",
    8080
  )

  println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
  StdIn.readLine()
  bindingFuture
    .flatMap(_.unbind())
    .onComplete(_ => system.terminate())

}
