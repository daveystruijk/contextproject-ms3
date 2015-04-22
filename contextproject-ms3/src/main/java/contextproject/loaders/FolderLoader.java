package contextproject.loaders;

import java.io.File;
import java.util.ArrayList;

/**
 * Class to read all the mp3 files from a given folder.
 *
 */
public class FolderLoader implements PlaylistLoader {

  private File folder;
  private static ArrayList<String> list;

  /**
   * Constructor.
   * 
   * @param location
   *          location of the folder.
   */
  public FolderLoader(String location) {
    folder = new File(location);
    list = new ArrayList<String>();
  }

  /**
   * Method to get the list of mp3 files and returns the list.
   */
  public ArrayList<String> getList() {
    File[] allfiles = folder.listFiles();

    for (int i = 0; i < allfiles.length; i++) {
      if (allfiles[i].length() > 4 && allfiles[i].toString().endsWith(".mp3")) {
        list.add(i, allfiles[i].toString());
      }
    }

    return list;
  }

}
