package model.data_structure

import com.mpatric.mp3agic.ID3v2

case class V2TagData (
                       dbID: Long,

                       album: String,
                       artist: String,
                       comment: String,
                       genreDescription: String,
                       genre: Int,
                       title: String,
                       track: String,
                       version: String,
                       year: String,

                       albumArtist: String,

                       composer: String,
                       date: String, encoder: String,
                       grouping: String,
                       itunesComment: String,
                       key: String,
                       lyrics: String,
                       originalArtist: String,
                       partOfSet: String,
                       publisher: String,

                       bpm: Int
                    )


object V2TagData {
  def apply(dbID: Long, iD3v2: ID3v2): V2TagData = new V2TagData(
    dbID,
    iD3v2.getAlbum,
    iD3v2.getArtist,
    iD3v2.getComment,
    iD3v2.getGenreDescription,
    iD3v2.getGenre,
    iD3v2.getTitle,
    iD3v2.getTrack,
    iD3v2.getVersion,
    iD3v2.getYear,
    iD3v2.getAlbumArtist,
    iD3v2.getComposer,
    iD3v2.getDate,
    iD3v2.getEncoder,
    iD3v2.getGrouping,
    iD3v2.getItunesComment,
    iD3v2.getKey,
    iD3v2.getLyrics,
    iD3v2.getOriginalArtist,
    iD3v2.getPartOfSet,
    iD3v2.getPublisher,
    iD3v2.getBPM
  )
}
