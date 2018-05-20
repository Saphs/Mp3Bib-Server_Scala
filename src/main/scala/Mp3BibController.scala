

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import org.mongodb.scala.bson.collection.immutable.Document

import scala.concurrent.ExecutionContext

class Mp3BibController extends Controller {

  implicit val ec: ExecutionContext = ExecutionContext.global

  val service: Mp3ManipulationService.type = Mp3ManipulationService

  get("/collection") { request: Request =>
    info("Recieved request on /collection")
    val futureResult = service.findAll()

    futureResult.map { sequence: Seq[Document] =>
      val jsonSequence = sequence.map(_.toJson())
      val responseJson = jsonSequence.mkString("[", ",", "]")
      response.ok.contentTypeJson().body(responseJson)
    }
  }

  get("/testInsert") { request: Request => //this should use a put
    info("Recieved request on /collection")
    val mp3 = service.readMp3("src/test/resources/example.mp3")

    mp3 match {
      case Some(file) =>
        val futureResult = service.writeOneDataSet(file)
        futureResult.map(response.ok)
      case None =>
        response.conflict
    }
  }
}
