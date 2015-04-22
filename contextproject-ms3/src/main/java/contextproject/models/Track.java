package contextproject.models;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.IOException;

public class Track {
  private Mp3File song;
  private String title;
  private String artist;
  private String album;
  private String absolutepath;
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
      System.out.println("There was a Unsupported tag exception with file:" + abPath);
      e.printStackTrace();
    } catch (InvalidDataException e) {
      System.out.println("There was a Invalid data exception with file:" + abPath);
      e.printStackTrace();
    } catch (IOException e) {
      System.out.println("There was a IO exception with file:" + abPath);
      e.printStackTrace();
    }
    absolutepath = abPath;
    getInformation();
    
  }

  /**
   * get information from Id3Tag.
   */
  private void getInformation() {
    if (song.hasId3v2Tag()) {
      title = song.getId3v2Tag().getTitle();
      artist = song.getId3v2Tag().getArtist();
      album = song.getId3v2Tag().getAlbum();
      length = song.getLengthInMilliseconds();
    } else {
      title = song.getId3v1Tag().getTitle();
      artist = song.getId3v1Tag().getArtist();
      album = song.getId3v1Tag().getAlbum();
      length = song.getLengthInMilliseconds();
    }
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
    return absolutepath;
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
