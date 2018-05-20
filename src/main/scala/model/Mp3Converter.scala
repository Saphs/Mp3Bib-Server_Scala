package model

import com.mpatric.mp3agic.Mp3File
import model.data_structure.Mp3MetaObject
import net.liftweb.json._

object Mp3Converter {

  def toJsonString (dbID: Long, mp3file: Mp3File): String = {
    val dataObject: Mp3MetaObject = Mp3MetaObject(dbID, mp3file)

    val jsonStruct = dataObject.convertToJsonStructure
    compactRender(jsonStruct)
  }

  def fromJsonString (json: String): (Long, Mp3File) = ???
}









