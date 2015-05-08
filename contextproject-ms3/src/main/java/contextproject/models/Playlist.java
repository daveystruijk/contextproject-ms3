package contextproject.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Playlist extends ArrayList<Track> implements Serializable {
  
  /**
   * serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Construct an empty playlist.
   */
  public Playlist() {
    
  }
  
  /**
   * Generate an ArrayList with tracks.
   * @param songs ArrayList with absolute path
   */
  public Playlist(ArrayList<String> songs) {
    for (String song : songs) {
      Track track = new Track(song);
      this.add(track);
    }
  }
}
