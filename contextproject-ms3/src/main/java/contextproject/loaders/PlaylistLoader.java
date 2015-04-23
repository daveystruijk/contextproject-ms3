package contextproject.loaders;

import java.util.ArrayList;

/**
 * Interface for the loaders to ensure it has the right functionality.
 *
 */
public interface PlaylistLoader {

  /**
   * Main method to load all the names of the mp3 files.
   * 
   * @return list of names of the mp3 files
   */
  public ArrayList<String> load();

}
