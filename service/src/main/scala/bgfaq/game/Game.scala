package bgfaq.game


import bgfaq.models.Models._
import bgfaq.Database._

import org.mongodb.scala._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.control.NonFatal


object Game {

  def addGameLogic(game: Game): Future[Either[ErrorMessage, AddSuccess]] = {
    gameCollection.insertOne(game).toFuture()
      .map(_ => Right(AddSuccess(s"Added ${game.title} to the database")))
      .recover {case NonFatal(t) => Left(ErrorMessage(t.getMessage))}
  }

}
