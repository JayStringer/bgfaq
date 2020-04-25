package bgfaq

import org.mongodb.scala._
import bgfaq.models.Models._

import org.bson.codecs.configuration.CodecRegistries._
import org.mongodb.scala.MongoClient.DEFAULT_CODEC_REGISTRY
import org.mongodb.scala.bson.codecs.Macros._


object Database {

  private val customCodecs = fromProviders(
    classOf[Game],
    classOf[FAQ]
  )

  private val codecRegistry = fromRegistries(customCodecs, DEFAULT_CODEC_REGISTRY)

  val mongoClient: MongoClient = MongoClient()
  val database: MongoDatabase = mongoClient
    .getDatabase("game-data-store")
    .withCodecRegistry(codecRegistry)

  val gameCollection: MongoCollection[Game] = database.getCollection("games")

}

