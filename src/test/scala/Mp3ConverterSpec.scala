import model.Mp3Converter
import org.scalatest.{MustMatchers, WordSpec}
import util.test_implementations.FileAccessTestImpl


class Mp3ConverterSpec extends WordSpec with MustMatchers{

  val resourcePath = "src/test/resources/"
  val fileAccessor = new FileAccessTestImpl

  "Mp3Converter" should {
    "Serialize a given Mp3File to a json-String" in {

      val expectedJsonString ="""{"fileData":{"dbID":884959943,"fileName":"src\\test\\resources\\example.mp3","channelMode":"Joint stereo","modeExtension":"M/S stereo","layer":"III","emphasis":"None","version":"1.0","bitrate":192,"frameCount":9569,"length":6029627,"sampleRate":44100,"hasId3v1Tag":true,"hasId3v2Tag":true},"v1TagData":{"dbID":884959943,"album":"","artist":"","comment":"converted by convert2mp3.net","genreDescription":"Unknown","genre":-1,"title":"","track":"","version":"1","year":""},"v2TagData":{"dbID":884959943,"album":null,"artist":null,"comment":"converted by convert2mp3.net","genreDescription":null,"genre":-1,"title":null,"track":null,"version":"3.0","year":null,"albumArtist":null,"composer":null,"date":null,"encoder":null,"grouping":null,"itunesComment":null,"key":null,"lyrics":null,"originalArtist":null,"partOfSet":null,"publisher":null,"bpm":-1}}"""
      val mp3 = fileAccessor.readMp3(s"${resourcePath}example.mp3")
      val randomInt = 884959943
      val mp3Data: Option[String] = mp3.map( Mp3Converter.toJsonString(randomInt, _) )

      mp3Data.isDefined must be (true)
      mp3Data.getOrElse("Empty String") must be (expectedJsonString)
    }
  }
}
