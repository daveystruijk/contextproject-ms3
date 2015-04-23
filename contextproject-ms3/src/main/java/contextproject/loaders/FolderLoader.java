package contextproject.loaders;

import java.io.File;
import java.util.ArrayList;

/**
 * Class to read all the mp3 files from a given folder.
 *
 */
public class FolderLoader implements PlaylistLoader {

  private File folder;
  private ArrayList<String> list;

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
   * Method to add all mp3 files from the folder and folders within this folder to the list.
   * 
   * @param directory
   *          location of the folder.
   */
  void addToList(File directory) {
    try {
      for (File file : directory.listFiles()) {
        if (file.length() >= 4 && file.toString().endsWith(".mp3")) {
          list.add(file.toString());
        } else if (file.isDirectory()) {
          this.addToList(file);
        }
      }
    } catch (NullPointerException e) {
      System.out.println("No such directory");
    }
  }
  /**
   * Method to get the list of mp3 files and returns the list.
   */
  public ArrayList<String> getList() {
    return list;
  }

  /**
   * Method to get all the music in the folder and the folders within this folder.
   * 
   * @return list with all mp3 music
   */
  public ArrayList<String> load() {
    try {
      this.addToList(folder);
    } catch (NullPointerException e) {
      System.out.println("Exception");
    }
    return this.getList();
  }

}
