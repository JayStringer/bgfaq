package bgfaq.endpoints


import bgfaq.models.Game._
import sttp.model.StatusCode
import sttp.tapir._
import sttp.tapir.json.circe._
import io.circe.generic.auto._


object Game {

  val addGameEndpoint = endpoint.post
    .in("game" / "add")
    .in(jsonBody[GameModel])
    .out(statusCode(StatusCode.Ok))
    .out(jsonBody[AddSuccess])
    .errorOut(statusCode(StatusCode.BadRequest))

}
