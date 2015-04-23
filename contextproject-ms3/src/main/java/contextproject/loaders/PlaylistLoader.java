package contextproject.loaders;

import java.io.File;
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

  /**
   * Method to add files to the list.
   * 
   * @param file
   *          directory where the files are located.
   */
  public void addToList(File file);

  /**
   * Method to get put all the music into the list.
   * 
   * @return list with all music files.
   * 
   * @throws Exception
   *           exception if no such directory exist.
   */
  public ArrayList<String> getAllMusic();
}
