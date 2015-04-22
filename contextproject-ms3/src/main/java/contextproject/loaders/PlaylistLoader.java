package contextproject.loaders;

import java.util.ArrayList;

/**
 * Interface for the loaders to ensure it has the right functionality.
 *
 */
public interface PlaylistLoader {

  /**
   * Method to get a list of all the files that are in the folder.
   * 
   * @return a list.
   */
  public ArrayList<String> getList();
}
