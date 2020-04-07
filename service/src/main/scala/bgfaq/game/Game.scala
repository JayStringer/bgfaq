package bgfaq.game

import bgfaq.models.Game._

import scala.concurrent.Future

object Game {

  def addGameLogic(game: GameModel): Future[Either[Unit, AddSuccess]] = {
    Future.successful(Right(AddSuccess(s"${game.title} added to nothing... db not implemented yet...")))
  }

}
