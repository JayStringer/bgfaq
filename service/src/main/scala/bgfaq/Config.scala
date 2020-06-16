package bgfaq

case class Config(mongo: MongoConfig)
case class MongoConfig(addressString: String, database: String, collection: String)

object Config {
  val namespace = "bgfaq"
}