package bgfaq.game


import java.util.concurrent.TimeUnit

import bgfaq.models.Models._
import bgfaq.Database._
import org.mongodb.scala._

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

object Game {

  def addGameLogic(game: Game): Future[Either[Unit, AddSuccess]] = {

    val doc: Document = Document(
      "title" -> game.title
    )

    val insertGameObservable = collection.insertOne(doc)
    val result = Await.result(insertGameObservable.toFuture(), Duration(3, TimeUnit.SECONDS))

    insertGameObservable.subscribe(insertGameObserver)

    val response = println(result)

    Future.successful(Right(AddSuccess(s"Added ${game.title} to the database")))
  }

}
