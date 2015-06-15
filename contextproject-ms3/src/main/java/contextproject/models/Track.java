package contextproject.models;

import contextproject.helpers.KeyBpmFinder;
import contextproject.helpers.StackTrace;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Track implements Serializable {

  private static final long serialVersionUID = -4652897251022204080L;

  private static Logger log = LogManager.getLogger(Track.class.getName());

  private String title;
  private String artist;
  private String album;
  private String absolutePath;
  private double duration;
  private long length;
  private double bpm;
  private MusicalKey key;
  private BeatGrid beatGrid;
  private ArrayList<Float> energyLevels;
  private MP3File song;
  private AbstractID3v2Tag tag;

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
      song = new MP3File(absolutePath);
      tag = song.getID3v2Tag();

    } catch (IOException e) {
      log.error("There was an IO exception with file: " + absolutePath);
      log.trace(StackTrace.stackTrace(e));
    } catch (TagException e) {
      log.error("There was a Tag exception with file: " + absolutePath);
      log.trace(StackTrace.stackTrace(e));
    } catch (ReadOnlyFileException e) {
      log.error("There was a Read-Only file exception with file: " + absolutePath);
      log.trace(StackTrace.stackTrace(e));
    } catch (InvalidAudioFrameException e) {
      log.error("There was an invalid audio frame exception with file: " + absolutePath);
      log.trace(StackTrace.stackTrace(e));
    }
  }

  /**
   * Create beatgrid.
   * 
   * @param startBeatIntro
   *          start beat of intro.
   * @param introBeatLength
   *          length of intro.
   * @param startBeatOutro
   *          start beat of outro.
   * @param outroBeatLength
   *          length of outro.
   * @param firstBeat
   *          first beat time.
   */
  public void createBeatGrid(int startBeatIntro, int introBeatLength, int startBeatOutro,
      int outroBeatLength, long firstBeat) {
    if (startBeatIntro > 0 && introBeatLength >= 0
        && startBeatIntro + introBeatLength < startBeatOutro && outroBeatLength >= 0) {
      this.beatGrid = new BeatGrid(this.length, this.bpm, firstBeat, startBeatIntro,
          introBeatLength, startBeatOutro, outroBeatLength);
    } else {
      log.warn("The beatgrid information is corrupt in: " + absolutePath);
    }
  }

  /**
   * Analyze a track for key and bpm.
   */
  public void analyzeTrack() {
    KeyBpmFinder keyBpm = new KeyBpmFinder();
    keyBpm.findKeyBpm(absolutePath);
    createSong();
    log.info("KEY: " + tag.getFirst(FieldKey.KEY) + "   BPM: " + tag.getFirst(FieldKey.BPM)
        + absolutePath);
  }

  /**
   * get information from Id3Tag.
   */
  private void getMetadata() {
    if (title == null) {
      title = tag.getFirst(FieldKey.TITLE);
    }
    if (artist == null) {
      artist = tag.getFirst(FieldKey.ARTIST);
    }
    if (album == null) {
      album = tag.getFirst(FieldKey.ALBUM);
    }
    if (duration < 1) {
      try {
        AudioFile audioFile = AudioFileIO.read(new File(absolutePath));
        duration = ((MP3AudioHeader) audioFile.getAudioHeader()).getPreciseTrackLength();
      } catch (Exception e) {
        log.error("Input was not correct: " + absolutePath);
        log.trace(StackTrace.stackTrace(e));
      }
    }
    if (bpm == 0) {
      try {
        bpm = Double.parseDouble(tag.getFirst(FieldKey.BPM));
      } catch (NumberFormatException e) {
        analyzeTrack();
        try {
          bpm = Double.parseDouble(tag.getFirst(FieldKey.BPM));
        } catch (NumberFormatException f) {
          log.error("There was no bpm information in file: " + absolutePath);
          log.trace(StackTrace.stackTrace(f));
        }
      }
    }
    try {
      key = new MusicalKey(tag.getFirst(FieldKey.KEY));
    } catch (IllegalArgumentException e) {
      analyzeTrack();
      try {
        key = new MusicalKey(tag.getFirst(FieldKey.KEY));
      } catch (IllegalArgumentException f) {
        log.error("There was no key information in file: " + absolutePath);
        log.trace(StackTrace.stackTrace(f));
      }
    }
  }

  /**
   * Set title.
   * 
   * @param songTitle
   *          title of the song.
   */
  public void setTitle(String songTitle) {
    title = songTitle;
  }

  /**
   * Set Artist.
   * 
   * @param songArtist
   *          artist of the song.
   */
  public void setArtist(String songArtist) {
    artist = songArtist;
  }

  /**
   * setAlbum.
   * 
   * @param songAlbum
   *          album of the song.
   */
  public void setAlbum(String songAlbum) {
    album = songAlbum;
  }

  /**
   * setPath.
   * 
   * @param path
   *          path of the song.
   */
  public void setPath(String path) {
    absolutePath = path;
  }

  /**
   * setDuration.
   * 
   * @param duration
   *          duration of song.
   */
  public void setDuration(double duration) {
    this.duration = duration;
  }

  /**
   * Set length of the song.
   * 
   * @param songLength
   *          length of the song
   */
  public void setLength(long songLength) {
    length = songLength;
  }

  /**
   * Set the beats per minute of the track.
   * 
   * @param songBpm
   *          bpm of the song
   */
  public void setBpm(double songBpm) {
    bpm = songBpm;
  }

  /**
   * Set the key of the song.
   * 
   * @param songKey
   *          key of the song.
   */
  public void setKey(MusicalKey songKey) {
    key = songKey;

  }

  /**
   * set the BeatGrid of the track.
   * 
   * @param songBeatGrid
   *          BeatGrid of the song
   */
  public void setBeatGrid(BeatGrid songBeatGrid) {
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

  public double getDuration() {
    return duration;
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
  public MusicalKey getKey() {
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

  public ArrayList<Float> getEnergyLevels() {
    return energyLevels;
  }

  public void setEnergyLevels(ArrayList<Float> el) {
    energyLevels = el;
  }

  public String toString() {
    return title;
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
