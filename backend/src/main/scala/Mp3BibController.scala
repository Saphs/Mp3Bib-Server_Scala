
import com.mpatric.mp3agic.Mp3File
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import model.requests.InsertCall
import net.liftweb.json._
import org.mongodb.scala.bson.collection.immutable.Document

import scala.concurrent.ExecutionContext

class Mp3BibController extends Controller {

  val service: Mp3ManipulationService.type = Mp3ManipulationService

  implicit val ec: ExecutionContext = ExecutionContext.global
  implicit val formats: DefaultFormats.type = service.formats

  get("/collection") { request: Request =>
    info("Recieved request on /collection")
    val futureResult = service.findAll()

    futureResult.map { sequence: Seq[Document] =>
      val jsonSequence = sequence.map(_.toJson())
      val responseJson = jsonSequence.mkString("[", ",", "]")
      response.ok.contentTypeJson().body(responseJson)
    }
  }
  // This needs to handle exceptions more gracefully, TODO: Refine malformed request handling
  put("/insert") { request: Request =>
    info(s"Recieved request on /insert with body: ${request.contentString}")
    val insertCall = parseOpt(request.contentString).map(_.extract[InsertCall])
    info(insertCall)
    val files: Seq[Option[Mp3File]] = insertCall.get match {
      case InsertCall(src, 1) => Seq(service.readMp3(src))
      case InsertCall(src, 2) => service.findMp3Locations(src, recursive = false).map(service.readMp3)
      case InsertCall(src, 3) => service.findMp3Locations(src, recursive = true).map(service.readMp3)
    }
    val cleanFiles = files.flatten
    cleanFiles.foreach(file => service.writeOneDataSet(file) )
      response.ok(s"Wrote ${cleanFiles.size} files to database:\n${cleanFiles.map(_.getFilename.split("\\\\").last)}")
  }
}
