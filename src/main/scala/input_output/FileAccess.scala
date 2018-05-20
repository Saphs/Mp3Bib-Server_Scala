package input_output

import java.io.File
import java.util.UUID

import util.Logging
import com.mpatric.mp3agic.Mp3File

import scala.util.Try


/**
  * FileAccess is a general purpose mp3 file access point,
  * providing default file manipulation functions.
  */
trait FileAccess extends Logging{

  def readMp3(path: String): Option[Mp3File] = {

    try {
      Some(new Mp3File(path))
    }
    catch{
      case e: Exception =>
        logger.warn(s"An Exception occurred while reading [$path]", e)
        None
    }
  }

  def overrideMp3(path: String, file: Mp3File): Boolean = {

    val strippedPath = path.dropWhile(!_.equals("/")).reverse
    val newPath = strippedPath + UUID.randomUUID().toString

    if (!saveFile(newPath, file)) {
      logger.warn(s"Failed to save [$file] to [$path]")
      return false
    }
    else if (!deleteFile(path)){
      logger.error(s"Failed to delete old file [$path] - new file is saved under [$newPath]")
      return false
    }
    else if (!renameExistingFile(newPath, path)) {
      logger.warn(s"Failed to rename new file from [$newPath] to [$path]")
      return false
    }

    true
  }

  def renameExistingFile(oldName: String, newName: String): Boolean = {
    Try(new File(oldName).renameTo(new File(newName))).getOrElse(false)
  }

  def saveFile(path: String, file: Mp3File): Boolean = {
    try {
      file.save(path)
      true
    }
    catch{
      case e: Exception =>
        logger.warn(s"An Exception occurred while saving [$path]", e)
        false
    }
  }

  def deleteFile(path: String): Boolean = {
    new File(path).delete()
  }

  def findMp3Locations(path: String): Seq[String] = {
    listFiles(new File(path)).map( _.getAbsolutePath )
  }

  private def listFiles(base: File, recursive: Boolean = true): Seq[File] = {
    val files = base.listFiles
    val result = files.filter(_.isFile)
    result ++
      files
        .filter(_.isDirectory)
        .filter(_ => recursive)
        .flatMap(listFiles(_, recursive))
  }
}
