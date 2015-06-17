package contextproject.models;

import org.apache.commons.math3.util.Precision;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TrackProperty {

  private final StringProperty playing = new SimpleStringProperty();
  private final StringProperty title = new SimpleStringProperty();
  private final StringProperty artist = new SimpleStringProperty();
  private final StringProperty bpm = new SimpleStringProperty();
  private final ObjectProperty<MusicalKey> key = new SimpleObjectProperty<MusicalKey>();
  private final ObjectProperty<Track> track = new SimpleObjectProperty<Track>();
  private final StringProperty energy = new SimpleStringProperty();

  /**
   * constructor of the playlist property.
   * 
   * @param title
   *          title of the song.
   * @param artist
   *          the artist.
   * @param bpm
   *          the bpm of the track.
   * @param key
   *          key of track.
   * 
   * @param energy
   *          average energy of track.
   * @param track
   *          the track itself, is for playability issues.
   * @param playing
   *          wether the track is playing or not.
   */

  public TrackProperty(String title, String artist, String bpm, MusicalKey key,
      String energy, Track track, String playing) {
    this.title.set(title);
    this.artist.set(artist);
    this.bpm.set(bpm);
    this.key.set(key);
    this.energy.set("" + Precision.round(Double.parseDouble(energy), 2));
    this.track.set(track);
    this.playing.set(playing);
  }
  /**
   * the title property of track property.
   * 
   * @return the property.
   */
  public final StringProperty titleProperty() {
    return this.title;
  }
  /**
   * get the title of the property.
   * 
   * @return the title
   */
  public final String getTitle() {
    return this.titleProperty().get();
  }
  /**
   * set the title of the property.
   * 
   * @param title
   *          the title to be set.
   */
  public final void setTitle(String title) {
    this.titleProperty().set(title);
  }
  /**
   * the artist property of the trackproperty.
   * 
   * @return the artist property.
   */
  public final StringProperty artistProperty() {
    return this.artist;
  }
  /**
   * get the artist of the property.
   * 
   * @return the artist.
   */
  public final String getArtist() {
    return this.artistProperty().get();
  }
  /**
   * set the artist of the property.
   * 
   * @param artist
   *          the artist to be set.
   */
  public final void setArtist(String artist) {
    this.artistProperty().set(artist);
  }
  /**
   * the bpm property of trackproperty.
   * 
   * @return the bpm property.
   */
  public final StringProperty bpmProperty() {
    return this.bpm;
  }
  /**
   * get the bpm of the property.
   * 
   * @return the bpm.
   */
  public final String getBpm() {
    return this.bpmProperty().get();
  }
  /**
   * set the bpm of the property.
   * 
   * @param bpm
   *          the bpm to be set.
   */
  public final void setBpm(String bpm) {
    this.bpmProperty().set(bpm);
  }
  /**
   * the key property of trackproperty.
   * 
   * @return the key property.
   */
  public final ObjectProperty<MusicalKey> keyProperty() {
    return this.key;
  }
  /**
   * get the key of the property.
   * 
   * @return the key.
   */
  public final MusicalKey getKey() {
    return this.keyProperty().get();
  }
  /**
   * set the key of the property.
   * 
   * @param key
   *          the key to be set.
   */
  public final void setKey(MusicalKey key) {
    this.keyProperty().set(key);
  }
  /**
   * the track property of track property(for playability issues).
   * 
   * @return the track property
   */
  
  /**
   * the energy property of track property.
   * @return the energy property
   */
  public final StringProperty energyProperty() {
    return this.energy;
  }
/**
 * get the energy of the property.
 * @return the energy
 */
  public final String getEnergy() {
    return this.energyProperty().get();
  }
/**
 * set the energy of the property
 * @param energy the energy to be set.
 */
  public final void setEnergy(String energy) {
    this.energyProperty().set(energy);
  }
  
 /**
  * the track property of track property(for playability issues).
  * @return the track property.
  */
  public final ObjectProperty<Track> trackProperty() {
    return this.track;
  }
  /**
   * get the track of the property.
   * 
   * @return the track.
   */
  public final Track getTrack() {
    return this.trackProperty().get();
  }
  /**
   * set the track of the property.
   * 
   * @param track
   *          the track to be set.
   */
  public final void setTrack(Track track) {
    this.trackProperty().set(track);
  }
  /**
   * the playing property of trackproperty.
   * 
   * @return the playing property.
   */
  public final StringProperty playingProperty() {
    return this.playing;
  }
  /**
   * get the playing string of the property.
   * 
   * @return the playing string.
   */
  public final String getPlaying() {
    return this.playingProperty().get();
  }
  /**
   * set the playing string of the property.
   * 
   * @param playing
   *          the string to be set.
   */
  public final void setPlaying(String playing) {
    this.playingProperty().set(playing);
  }

}
