package bgfaq


import akka.http.scaladsl.server.Route
import bgfaq.endpoints.Game._
import bgfaq.game.Game._
import sttp.tapir.server.akkahttp._



object Routes {

  val addGameRoute: Route = addGameEndpoint.toRoute(addGame)
  val getAllGamesRoute: Route = getAllGamesEndpoint.toRoute(getAllGames)
  val queryGamesRoute: Route = queryGamesEndpoint.toRoute(queryGames)

}
