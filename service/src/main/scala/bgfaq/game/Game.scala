package bgfaq.game


import bgfaq.Database._
import bgfaq.models.Models._
import org.mongodb.scala._
import org.mongodb.scala.model.Filters._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.control.NonFatal


object Game {

  def addGame(game: Game): Future[Either[ErrorMessage, AddSuccess]] = {
    gameCollection.insertOne(game).toFuture()
      .map(_ => Right(AddSuccess(s"Added '${game.title}' to the database", game._id)))
      .recover {case NonFatal(t) => Left(ErrorMessage(t.getMessage))}
  }

  val getAllGames: Unit => Future[Either[ErrorMessage, GameList]] = _ => {
    gameCollection.find[Game]().toFuture()
      .map((g: Seq[Game]) => Right(GameList(g.toList)))
      .recover {case NonFatal(t) => Left(ErrorMessage(t.getMessage))}
  }

  def queryGames(query: GameQuery): Future[Either[ErrorMessage, QueryResult]] = {
    gameCollection.find(regex("title", query.title.getOrElse(""))).toFuture()
      .map((g: Seq[Game]) => Right(QueryResult(g.toList)))
      .recover {case NonFatal(t) => Left(ErrorMessage(t.getMessage))}
  }

  def getGame(input: String): Future[Either[ErrorResponse, Game]] = {
    gameCollection.find(equal("_id", input)).first().toFuture()
      .map {
        case null => Left(ResourceNotFound(s"Unable to find game with id '${input}'"))
        case g: Game => Right(g)
      }
      .recover {case NonFatal(t) => Left(ErrorMessage(t.getMessage))}
  }

  def getQuestion(input: FAQSearch): Future[Either[ErrorResponse, FAQ]] = {
    gameCollection.find(equal("_id", input.gameId)).first().toFuture()
      .map {
        case null => Left(ResourceNotFound(s"Unable to find game with id '${input.gameId}'"))
        case g: Game =>
          g.entries.getOrElse(List.empty).find(_.id == input.faqId) match {
            case Some(f: FAQ) => Right(f)
            case None => Left(ResourceNotFound(s"Question with id ${input.faqId} not found for ${g.title}"))
          }
      }
      .recover {case NonFatal(t) => Left(ErrorMessage(t.getMessage))}
  }
}
