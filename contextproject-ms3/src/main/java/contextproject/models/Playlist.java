package contextproject.models;

import java.util.ArrayList;

public class Playlist extends ArrayList<Track> {

  /**
   * serialVersionUID.
   */
  private static final long serialVersionUID = 1L;
  
  private ArrayList<Track> pl;

  
  /**
   * Generate an ArrayList with tracks.
   * @param songs ArrayList with absolute path
   */
  public Playlist(ArrayList<String> songs) {
    pl = new ArrayList<Track>();
    for (String song : songs) {
      Track tr = new Track(song);
      pl.add(tr);
    }
  }
  
}
