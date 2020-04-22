package bgfaq.game


import java.util.concurrent.TimeUnit

import bgfaq.models.Models._
import bgfaq.Database._
import org.mongodb.scala._

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success, Try}

object Game {

  def addGameLogic(game: Game): Future[Either[ErrorMessage, AddSuccess]] = {

    val doc: Document = Document(
      "_id" -> 0,
      "title" -> game.title
    )

    Try(Await.result(collection.insertOne(doc).toFuture(), Duration(3, TimeUnit.SECONDS))) match {
      case Success(_) => Future.successful(Right(AddSuccess(s"Added ${game.title} to the database")))
      case Failure(e) => Future.successful(Left(ErrorMessage(s"Unable to add game to database: $e")))
    }
  }

}
