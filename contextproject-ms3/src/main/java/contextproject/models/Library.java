package contextproject.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Library extends ArrayList<Playlist> implements Serializable{

  private static final long serialVersionUID = 3382357001159419415L;

  /**
   * Empty constructor.
   */
  public Library() {
    
  }
  
  /**
   * Constructor of library.
   * @param playlists ArrayList of playlists.
   */
  public Library(ArrayList<Playlist> playlists) {
    for (Playlist playlist : playlists) {
      this.add(playlist);
    }
  }

}
