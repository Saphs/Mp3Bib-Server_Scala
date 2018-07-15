package model.data_structure

import com.mpatric.mp3agic.Mp3File

case class FileData(

                     dbID: Long,
                     fileName: String,
                     channelMode: String,
                     modeExtension: String,
                     layer: String,
                     emphasis: String,
                     version: String,
                     bitrate: Int,
                     frameCount: Int,
                     length: Long,
                     sampleRate: Int,

                     hasId3v1Tag: Boolean,
                     hasId3v2Tag: Boolean
                   )

object FileData {
  def apply(dbID: Long, mp3File: Mp3File): FileData = {

    new FileData(
      dbID,
      mp3File.getFilename,
      mp3File.getChannelMode,
      mp3File.getModeExtension,
      mp3File.getLayer,
      mp3File.getEmphasis,
      mp3File.getVersion,
      mp3File.getBitrate,
      mp3File.getFrameCount,
      mp3File.getLength,
      mp3File.getSampleRate,
      mp3File.hasId3v1Tag,
      mp3File.hasId3v2Tag
    )
  }
}