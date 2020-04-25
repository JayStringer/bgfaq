package bgfaq.models

object Models {

  case class Game(title: String, entries: Option[List[FAQ]])

  case class FAQ(question: String, answer: String, tags: Option[List[String]])


  case class AddSuccess(message: String)
  case class ErrorMessage(error: String)

}
