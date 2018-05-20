package model.data_structure

import com.mpatric.mp3agic.ID3v1

case class V1TagData(
                      dbID: Long,

                      album: String,
                      artist: String,
                      comment: String,
                      genreDescription: String,
                      genre: Int,
                      title: String,
                      track: String,
                      version: String,
                      year: String
                    )

object V1TagData {
  def apply(dbID: Long, iD3v1: ID3v1): V1TagData = {

    new V1TagData(

      dbID,
      iD3v1.getAlbum,
      iD3v1.getArtist,
      iD3v1.getComment,
      iD3v1.getGenreDescription,
      iD3v1.getGenre,
      iD3v1.getTitle,
      iD3v1.getTrack,
      iD3v1.getVersion,
      iD3v1.getYear
    )
  }

}
