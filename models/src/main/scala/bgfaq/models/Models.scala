package bgfaq.models

import io.circe._
import org.mongodb.scala.bson.ObjectId

object Models {

  case class Game(_id: String, title: String, mechanics: Option[List[String]], entries: Option[List[FAQ]])
  case class FAQ(id: String, question: String, answer: String, tags: Option[List[String]])
  case class GameList(games: List[Game])

  implicit val encodeGameAsJson: Encoder[Game] = (game: Game) => Json.obj(
      ("_id", Json.fromString(game._id)),
      ("title", Json.fromString(game.title)),
      ("mechanics", Json.arr(game.mechanics.getOrElse(List.empty).map(Json.fromString):_*)),
      ("entries", Json.arr(game.entries.getOrElse(List.empty).map(encodeFAQAsJson.apply):_*))
    )

  implicit val decodeGameFromJson: Decoder[Game] = (c: HCursor) => for {
    title <- c.downField("title").as[String]
    mechanics <- c.downField("mechanics").as[Option[List[String]]]
    entries <- c.downField("entries").as[Option[List[FAQ]]]
  } yield {
    Game(title, title, mechanics, entries)
  }

  implicit val encodeFAQAsJson: Encoder[FAQ] = (faq: FAQ) => Json.obj(
    ("id", Json.fromString(faq.id)),
    ("question", Json.fromString(faq.question)),
    ("answer", Json.fromString(faq.answer)),
    ("tags", Json.arr(faq.tags.getOrElse(List.empty).map(Json.fromString):_*))
  )

  implicit val decodeFAQFromJson: Decoder[FAQ] = (c: HCursor) => for {
    question <- c.downField("question").as[String]
    answer <- c.downField("answer").as[String]
    tags <- c.downField("tags").as[Option[List[String]]]
  } yield {
    FAQ(new ObjectId().toString, question, answer, tags)
  }

  case class AddSuccess(message: String)
  case class ErrorMessage(error: String)

}