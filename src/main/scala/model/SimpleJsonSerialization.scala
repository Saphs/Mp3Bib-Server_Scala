package model
import net.liftweb.json._

trait SimpleJsonSerialization {
  def convertToJson = {
    compactRender(convertToJsonStructure)
  }
  def convertToJsonStructure: JObject
}
