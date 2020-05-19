package bgfaq.endpoints


import bgfaq.models.Models._
import io.circe.generic.auto._
import sttp.model.StatusCode
import sttp.tapir._
import sttp.tapir.json.circe._



object Game {

  val addGameEndpoint = endpoint.post
    .in("games" / "add")
    .in(jsonBody[Game])
    .out(statusCode(StatusCode.Created))
    .out(jsonBody[AddSuccess])
    .errorOut(statusCode(StatusCode.BadRequest))
    .errorOut(jsonBody[ErrorMessage])


  val getAllGamesEndpoint = endpoint.get
    .in("games")
    .out(statusCode(StatusCode.Ok))
    .out(jsonBody[GameList])
    .errorOut(statusCode(StatusCode.BadRequest))
    .errorOut(jsonBody[ErrorMessage])

  val queryGamesEndpoint = endpoint.get
    .in("games" / "search")
    .in(jsonBody[GameQuery])
    .out(statusCode(StatusCode.Ok))
    .out(jsonBody[QueryResult])
    .errorOut(statusCode(StatusCode.BadRequest))
    .errorOut(jsonBody[ErrorMessage])

}
