import model.data_structure.FileData
import net.liftweb.json.DefaultFormats
import org.scalatest.{MustMatchers, WordSpec}
import util.test_implementations.FileAccessTestImpl

class FileDataSpec extends WordSpec with MustMatchers{

  implicit val formats = DefaultFormats
  val resourcePath = "src/test/resources/"
  val fileAccessor = new FileAccessTestImpl

  "FileData" should {
    "apply to extract meta data from Mp3Files" in {

      val expectedFileData = Some(FileData(1, "src\\test\\resources\\example.mp3", "Joint stereo","M/S stereo","III","None","1.0",192,9569,6029627,44100,hasId3v1Tag = true,hasId3v2Tag = true))
      val mp3 = fileAccessor.readMp3(s"${resourcePath}example.mp3")
      val extractedData = mp3.map(FileData(1, _))
      extractedData must be (expectedFileData)
    }
  }
}
