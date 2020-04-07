package bgfaq.endpoints

import sttp.tapir._
import sttp.model.StatusCode


object Basic {
  val status = endpoint
    .in("status")
    .get
    .out(stringBody("Waffles"))
    .out(statusCode(StatusCode.Ok))
    .errorOut(statusCode(StatusCode.ServiceUnavailable))
}
