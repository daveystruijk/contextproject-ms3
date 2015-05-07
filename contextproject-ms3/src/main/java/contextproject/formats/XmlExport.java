package contextproject.formats;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import contextproject.helpers.StackTrace;
import contextproject.loaders.FolderLoader;
import contextproject.loaders.LibraryLoader;
import contextproject.models.Key;
import contextproject.models.Playlist;

/**
 * Class to export data to a XML file.
 *
 */
public class XmlExport {

  private static Logger log = LogManager.getLogger(LibraryLoader.class.getName());
  private File file;
  private Playlist playlist;

  public XmlExport(String location, Playlist pl) {
    file = new File(location);
    playlist = pl;
  }

  /**
   * Write.
   */
  public void write() {
    XMLEncoder encoder = null;
    try {
      encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file)));
    } catch (FileNotFoundException e) {
      log.error("Exception");
      log.trace(StackTrace.stackTrace(e));
    }
    encoder.writeObject(playlist);
    encoder.close();
  }
  
  /**
   * Main.
   */
  public static void main(String [] args) {
    FolderLoader folderLoader 
    = new FolderLoader("C:/Users/Emiel/git/contextproject-ms3/contextproject-ms3/src/test/resources");

    Playlist pl = folderLoader.load();
    Key key = new Key("Bb");
    pl.get(0).setKey(key);
    String loc = "C:/Users/Emiel/Documents/test.xml";
    XmlExport example = new XmlExport(loc, pl);
    example.write();
    
    LibraryLoader ll = new LibraryLoader(loc);
    
    Playlist pl2 = ll.load();
    System.out.println(pl2.get(0).getKey().getNormalizedKeyString());
    System.out.println(pl.get(0).getKey().getMusicalKeyString());
    System.out.println(pl2.get(0).getKey().getMusicalKeyString());
    System.out.println(pl2.get(0).getAlbum());
    System.out.println(pl.get(0).getAlbum());

    System.out.println(pl.containsAll(pl2));
  }

}
