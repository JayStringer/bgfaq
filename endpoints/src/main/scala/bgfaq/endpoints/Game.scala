package bgfaq.endpoints


import bgfaq.models.Models._
import io.circe.generic.auto._
import sttp.model.StatusCode
import sttp.tapir._
import sttp.tapir.json.circe._



object Game {

  val addGameEndpoint = endpoint.post
    .in("game" / "add")
    .in(jsonBody[Game])
    .out(statusCode(StatusCode.Created))
    .out(jsonBody[AddSuccess])
    .errorOut(statusCode(StatusCode.BadRequest))
    .errorOut(jsonBody[ErrorMessage])


  val getAllGamesEndpoint = endpoint.get
    .in("game")
    .out(statusCode(StatusCode.Ok))
    .out(jsonBody[GameList])
    .errorOut(statusCode(StatusCode.BadRequest))
    .errorOut(jsonBody[ErrorMessage])

}
