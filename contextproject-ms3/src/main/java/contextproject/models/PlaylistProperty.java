package contextproject.models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PlaylistProperty {

  private final StringProperty title = new SimpleStringProperty();
  private final StringProperty artist = new SimpleStringProperty();
  private final DoubleProperty bpm = new SimpleDoubleProperty();
  private final ObjectProperty<Key> key = new SimpleObjectProperty<Key>();
  private final ObjectProperty<Track> track = new SimpleObjectProperty<Track>();

  /**
   * constructor of the playlist property.
   * @param title title of the song.
   * @param artist the artist.
   * @param bpm the bpm of the track.
   * @param key key of track.
   * @param track the track itself, is for playability issues.
   */
  public PlaylistProperty(String title, String artist,double bpm,Key key, Track track) {
    this.title.set(title);
    this.artist.set(artist);
    this.bpm.set(bpm);
    this.key.set(key);
    this.track.set(track);
  }
  
  public final StringProperty titleProperty() {
    return this.title;
  }
     
  public final String getTitle() {
    return this.titleProperty().get();
  }
     
  public final void setTitle(String title) {
    this.titleProperty().set(title);
  }
  
  public final StringProperty artistProperty() {
    return this.artist;
  }
     
  public final String getArtist() {
    return this.artistProperty().get();
  }
     
  public final void setArtist(String artist) {
    this.artistProperty().set(artist);
  }
  
  public final DoubleProperty bpmProperty() {
    return this.bpm;
  }
     
  public final double getBPM() {
    return this.bpmProperty().get();
  }
     
  public final void setBPM(Double bpm) {
    this.bpmProperty().set(bpm);
  }
  
  public final ObjectProperty<Key> keyProperty() {
    return this.key;
  }
     
  public final Key getKey() {
    return this.keyProperty().get();
  }
     
  public final void setKey(Key key) {
    this.keyProperty().set(key);
  }
  
  public final ObjectProperty<Track> trackProperty() {
    return this.track;
  }
     
  public final Track getTrack() {
    return this.trackProperty().get();
  }
     
  public final void setatrack(Track track) {
    this.trackProperty().set(track);
  }
}
