package input_output

import net.liftweb.json.DefaultFormats

/**
  * BasicDAO combines DBAccess and FileAccess to provide
  * a single general purpose access point for mp3 data handling.
  *
  * Constant values for the access directory will use a config
  * file in future versions
  */
trait BasicDAO extends DBAccess with FileAccess{

  implicit val formats: DefaultFormats.type = DefaultFormats

  val defaultPath = "C:\\Users\\Tiz\\Videos\\4K Video Downloader"
  lazy val diskLocations: Seq[String] = findMp3Locations(defaultPath, recursive = true)

}


