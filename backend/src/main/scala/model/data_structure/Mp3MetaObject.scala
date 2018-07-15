package model.data_structure

import com.mpatric.mp3agic.Mp3File
import model.SimpleJsonSerialization
import net.liftweb.json.Extraction._
import net.liftweb.json.JsonDSL._
import net.liftweb.json._

case class Mp3MetaObject(dbID: Long, fileData: FileData, v1TagData: Option[V1TagData], v2TagData: Option[V2TagData]) extends SimpleJsonSerialization{

  implicit val formats: DefaultFormats.type = DefaultFormats

  override def convertToJsonStructure = {

    ("fileData" -> decompose(fileData)) ~
      ("v1TagData" -> v1TagData.map( decompose )) ~
      ("v2TagData" -> v2TagData.map( decompose ))
  }
}

object Mp3MetaObject  {
  def apply(dbID: Long, mp3File: Mp3File): Mp3MetaObject = {

    val fileData = FileData(dbID, mp3File)

    val v1TagData: Option[V1TagData] = if (mp3File.hasId3v1Tag) {
      Some(V1TagData(dbID, mp3File.getId3v1Tag))
    } else {
      None
    }

    val v2TagData: Option[V2TagData] = if (mp3File.hasId3v2Tag){
      Some(V2TagData(dbID, mp3File.getId3v2Tag))
    } else {
      None
    }

    Mp3MetaObject(dbID, fileData, v1TagData, v2TagData)
  }
}
