package bgfaq

import org.mongodb.scala._
import bgfaq.models.Models._

object Database {

  val mongoClient: MongoClient = MongoClient()
  val database: MongoDatabase = mongoClient.getDatabase("testdb")
  val collection: MongoCollection[Document] = database.getCollection("test-collection")


  val insertGameObserver = new Observer[Completed] {
    override def onNext(result: Completed): Unit = result.toString
    override def onError(e: Throwable): Unit = throw e
    override def onComplete(): Unit = println("Done")
  }

}

