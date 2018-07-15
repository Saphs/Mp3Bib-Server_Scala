import com.mpatric.mp3agic.Mp3File
import input_output.BasicDAO
import model.Mp3Converter
import org.mongodb.scala.Completed
import org.mongodb.scala.bson.collection.immutable.Document

import scala.concurrent.Future
import scala.util.Random


object Mp3ManipulationService extends BasicDAO {

  def writeOneDataSet(mp3File: Mp3File): Future[Completed] ={
    val randomId = Random.nextInt
    val jsonString = Mp3Converter.toJsonString(randomId, mp3File)
    collection.insertOne(Document(jsonString)).toFuture()
  }

  def findAll(): Future[Seq[Document]] = {
    collection.find().toFuture()
  }

}


