package input_output

import org.mongodb.scala.{Document, MongoClient, MongoCollection, MongoDatabase}


/**
  * DBAccess is a default Database access point
  * for statically defined MongoDB running on:
  *   localhost:27017
  *
  * See 'Robo 3T' to monitor its state.
  * Static behaviour is changing in future versions.
  */
trait DBAccess{

  lazy val mongoClient: MongoClient = MongoClient()
  lazy val database: MongoDatabase = mongoClient.getDatabase("Mp3_media")
  lazy val collection: MongoCollection[Document] = database.getCollection("Mp3_data")

}
