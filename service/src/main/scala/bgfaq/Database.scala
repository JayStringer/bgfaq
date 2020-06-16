package bgfaq

import org.mongodb.scala._
import bgfaq.models.Models._
import org.bson.codecs.configuration.CodecRegistries._
import org.mongodb.scala.MongoClient.DEFAULT_CODEC_REGISTRY
import org.mongodb.scala.bson.codecs.Macros._
import org.mongodb.scala.model.IndexOptions
import org.mongodb.scala.model.Indexes._
import pureconfig._
import pureconfig.generic.auto._

object Database {

  val config = ConfigSource.default.at(Config.namespace).loadOrThrow[Config]

  private val customCodecs = fromProviders(
    classOf[Game],
    classOf[FAQ]
  )

  private val codecRegistry = fromRegistries(customCodecs, DEFAULT_CODEC_REGISTRY)

  val mongoClient: MongoClient = MongoClient(config.mongo.addressString)
  val database: MongoDatabase = mongoClient
    .getDatabase(config.mongo.database)
    .withCodecRegistry(codecRegistry)

  val gameCollection: MongoCollection[Game] = database.getCollection(config.mongo.collection)
  gameCollection.createIndex(text("title"), IndexOptions().unique(true)).toFuture()

}

