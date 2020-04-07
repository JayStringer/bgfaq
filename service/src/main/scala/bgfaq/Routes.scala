package bgfaq


import sttp.tapir.server.akkahttp._
import bgfaq.endpoints.Game._
import bgfaq.game.Game._



object Routes {

  val addGameRoute = addGameEndpoint.toRoute(addGameLogic)

}
