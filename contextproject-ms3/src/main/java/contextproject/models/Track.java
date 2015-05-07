package contextproject.models;

import java.io.IOException;
import java.io.Serializable;
import java.util.Hashtable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import contextproject.helpers.StackTrace;

public class Track implements Serializable {
  private static Logger log = LogManager.getLogger(Track.class.getName());

  private Mp3File song;
  private String title;
  private String artist;
  private String album;
  private String absolutePath;
  private long length;
  private double bpm;
  private Key key;
  private BeatGrid beatGrid;

  /**
   * Constructor of a track with extended information from library . 
   * The hashtable could contain the following keys 
   * (all values should be strings that will be parsed later):
   * 
   * <p>
   * String title, String artist, String album, long length, double bpm, String key, 
   * long firstBeat, int beatIntroStart, int beatsIntroLength, 
   * int beatOutroStart, int beatsOutroLength
   * 
   * @param abPath
   *          Path of the mp3 file
   * @param info
   *          Hashtable with extended information
   */
  public Track(String abPath, Hashtable<String, String> info) {

    this.absolutePath = abPath;
    createSong();
    extractInfo(info);
    getMetadata();
  }
  
  /**
   * Constructor without arguments.
   */
  public Track() {

  }

  /**
   * constructor of a track with only id3 information.
   * 
   * @param abPath
   *          Path of the mp3 file
   */
  public Track(String abPath) {
    this.absolutePath = abPath;
    createSong();
    getMetadata();
  }

  private void createSong() {
    try {
      song = new Mp3File(absolutePath);
    } catch (UnsupportedTagException e) {
      log.error("There was a Unsupported tag exception with file:" + absolutePath);
      log.trace(StackTrace.stackTrace(e));
    } catch (InvalidDataException e) {
      log.error("There was a Invalid data exception with file:" + absolutePath);
      log.trace(StackTrace.stackTrace(e));
    } catch (IOException e) {
      log.error("There was a IO exception with file:" + absolutePath);
      log.trace(StackTrace.stackTrace(e));
    }
  }

  private void extractInfo(Hashtable<String, String> info) {

    this.title = info.get("title");
    this.artist = info.get("artist");
    this.album = info.get("album");

    try {
      this.length = Long.parseLong(info.get("length"));
    } catch (NumberFormatException e) {
      this.length = song.getLengthInMilliseconds();
    }

    try {
      this.bpm = Double.parseDouble((info.get("bpm")));
    } catch (NullPointerException | NumberFormatException f) {
      this.bpm = 0.0;
    }

    if (info.get("key") != null) {
      this.key = new Key(info.get("key"));
    }

    getMetadata();

    int startBeatIntro;
    int introBeatLength;
    int startBeatOurto;
    int outroBeatLength;
    long firstBeat;

    try {
      startBeatIntro = Integer.parseInt(info.get("startBeatIntro"));
      introBeatLength = Integer.parseInt(info.get("introBeatLength"));
    } catch (NumberFormatException e) {
      startBeatIntro = 0;
      introBeatLength = 0;
    }

    try {
      startBeatOurto = Integer.parseInt(info.get("startBeatOutro"));
      outroBeatLength = Integer.parseInt(info.get("outroBeatLength"));
    } catch (NumberFormatException e) {
      startBeatOurto = 0;
      outroBeatLength = 0;
    }

    try {
      firstBeat = Long.parseLong(info.get("firstBeat"));
    } catch (NumberFormatException e) {
      firstBeat = 0;
    }

    this.beatGrid = new BeatGrid(this.length, this.bpm, firstBeat, startBeatIntro, introBeatLength,
        startBeatOurto, outroBeatLength);

  }

  /**
   * get information from Id3Tag.
   */
  private void getMetadata() {
    if (song.hasId3v2Tag()) {
      if (title == null) {
        title = song.getId3v2Tag().getTitle();
      }
      if (artist == null) {
        artist = song.getId3v2Tag().getArtist();
      }
      if (album == null) {
        album = song.getId3v2Tag().getAlbum();
      }
      if (bpm == 0) {
        bpm = song.getId3v2Tag().getBPM();
      }
      try {
        key = new Key(song.getId3v2Tag().getKey());
      } catch (IllegalArgumentException e) {
        log.warn("Could not find key information in: " + song.getFilename());
      }
    } else {
      log.warn("Could not find Id3v2 information in: " + song.getFilename());
    }
  }

  /**
   * Set title.
   * 
   * @param songTitle title of the song.
   */
  public void setTitle(String songTitle) {
    title = songTitle;
  }


  /**
   * Set Artist.
   * 
   * @param songArtist artist of the song.
   */
  public void setArtist(String songArtist) {
    artist = songArtist;
  }

  /**
   * setAlbum.
   * 
   * @param songAlbum album of the song.
   */
  public void setAlbum(String songAlbum) {
    album = songAlbum;
  }

  /**
   * setPath.
   * 
   * @param path path of the song.
   */
  public void setPath(String path) {
    absolutePath = path;
  }

  /**
   * Set length of the song.
   * 
   * @param songLength length of the song
   */
  public void setLength(long songLength) {
    length = songLength;
  }

  /**
   * Set the beats per minute of the track.
   * 
   * @param songBpm bpm of the song
   */
  public void setBpm(double songBpm) {
    bpm = songBpm;
  }

  /**
   *  Set the key of the song.
   * 
   * @param songKey key of the song.
   */
  public void getKey(Key songKey) {
    key = songKey;

  }

  /**
   * set the BeatGrid of the track.
   * 
   * @param songBeatGrid BeatGrid of the song
   */
  public void getBeatGrid(BeatGrid songBeatGrid) {
    beatGrid = songBeatGrid;
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
   * String with absolute path.
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

  /**
   * Beats per minute of the track.
   * 
   * @return int
   */
  public double getBpm() {
    return bpm;
  }

  /**
   * Track key object.
   * 
   * @return Key
   */
  public Key getKey() {
    return key;

  }

  /**
   * BeatGrid object.
   * 
   * @return BeatGrida
   */
  public BeatGrid getBeatGrid() {
    return beatGrid;
  }

  
  
  
  
  

  /**
   * Equals method to check if an object is the same as the Track object.
   * 
   * @param other
   *          object
   * @return true if equals, else false.
   */
  public boolean equals(Object other) {
    if (other instanceof Track) {
      if (this.getPath() != null && ((Track) other).getPath() != null) {
        return (this.getPath().equals(((Track) other).getPath()));
      }
      return false;
    }
    return false;
  }
}
