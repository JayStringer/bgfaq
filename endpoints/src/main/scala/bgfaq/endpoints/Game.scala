package bgfaq.endpoints



import bgfaq.models.Game._
import bgfaq.models.Generic.ErrorMessage

import sttp.model.StatusCode
import sttp.tapir._
import sttp.tapir.json.circe._

import io.circe.generic.auto._
import io.circe.{Decoder, Encoder}

object Game {

  val addGameEndpoint = endpoint.post
    .in("game" / "add")
    .in(jsonBody[GameModel])
    .out(statusCode(StatusCode.Ok))
    .out(jsonBody[AddSuccess])
    .errorOut(statusCode(StatusCode.BadRequest))

}
