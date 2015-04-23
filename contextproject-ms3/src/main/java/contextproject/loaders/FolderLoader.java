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
  public void addToList(File directory) {
    try {
      File[] allfiles = directory.listFiles();

      for (int i = 0; i < allfiles.length; i++) {
        if (allfiles[i].length() >= 4 && allfiles[i].toString().endsWith(".mp3")) {
          list.add(allfiles[i].toString());
        } else if (allfiles[i].isDirectory()) {
          FolderLoader loader = new FolderLoader(allfiles[i].toString());
          File submap = new File(allfiles[i].toString());
          loader.addToList(submap);
          list.addAll(loader.getList());
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
  public ArrayList<String> getAllMusic() {
    System.out.println(folder.toString());
    try {
      this.addToList(folder);
    } catch (NullPointerException e) {
      System.out.println("Exception");
    }
    return this.getList();
  }

}
