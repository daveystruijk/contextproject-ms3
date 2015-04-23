package contextproject.models;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import contextproject.App;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Track {
  static Logger log = LogManager.getLogger(Track.class.getName());

  private Mp3File song;
  private String title;
  private String artist;
  private String album;
  private String absolutePath;
  private long length;

  /**
   * constructor of a track.
   * 
   * @param abPath
   *          Path of the mp3 file
   */
  public Track(String abPath) {
    try {
      song = new Mp3File(abPath);
    } catch (UnsupportedTagException e) {
      log.error("There was a Unsupported tag exception with file:" + abPath);
      e.printStackTrace();
    } catch (InvalidDataException e) {
      log.error("There was a Invalid data exception with file:" + abPath);
      e.printStackTrace();
    } catch (IOException e) {
      log.error("There was a IO exception with file:" + abPath);
      e.printStackTrace();
    }
    absolutePath = abPath;
    getMetadata();
  }

  /**
   * get information from Id3Tag.
   */
  private void getMetadata() {
    if (song.hasId3v2Tag()) {
      title = song.getId3v2Tag().getTitle();
      artist = song.getId3v2Tag().getArtist();
      album = song.getId3v2Tag().getAlbum();
    } else {
      title = song.getId3v1Tag().getTitle();
      artist = song.getId3v1Tag().getArtist();
      album = song.getId3v1Tag().getAlbum();
    }
    length = song.getLengthInMilliseconds();
  }

  /**
   * String with title.
   * 
   * @return String
   */
  public String getTitle() {
    return title;
  }

  /**
   * String with artist.
   * 
   * @return String
   */
  public String getArtist() {
    return artist;
  }

  /**
   * String with album.
   * 
   * @return String
   */
  public String getAlbum() {
    return album;
  }

  /**
   * String with  absolute path.
   * 
   * @return String
   */
  public String getPath() {
    return absolutePath;
  }

  /**
   * Long with track length.
   * 
   * @return String
   */
  public Long getLength() {
    return length;
  }

}