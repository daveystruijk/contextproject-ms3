package contextproject.loaders;

import contextproject.models.Playlist;
import contextproject.models.Track;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ArrayList;

/**
 * Class to read all the mp3 files from a given folder.
 *
 */
public class FolderLoader implements PlaylistLoader {
  private static Logger log = LogManager.getLogger(FolderLoader.class.getName());
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
  private void addToList(File directory) throws NullPointerException {
    log.info("Scanning " + directory);
    for (File file : directory.listFiles()) {
      if (file.length() >= 4 && file.toString().endsWith(".mp3")) {
        list.add(file.toString());
      } else if (file.isDirectory()) {
        this.addToList(file);
      }
    }
  }
  /**
   * Method to get the list of mp3 files and returns the list.
   */
  public ArrayList<String> getList() {
    return list;
  }

  @Override
  public Playlist load() throws NullPointerException {
    this.addToList(folder);
    ArrayList<String> array = this.getList();
    Playlist pl = new Playlist();
    for (String s : array) {
      // pl.add(new Track(s), new Hashtable());
      pl.add(new Track(s));
    }
    return pl;
  }

}
