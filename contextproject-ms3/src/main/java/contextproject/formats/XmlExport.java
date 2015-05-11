package contextproject.formats;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import contextproject.helpers.StackTrace;
import contextproject.loaders.LibraryLoader;
import contextproject.models.Library;
import contextproject.models.Playlist;
import contextproject.models.Track;

/**
 * Class to export data to a XML file.
 *
 */
public class XmlExport {

  private static Logger log = LogManager.getLogger(LibraryLoader.class.getName());
  private File file;
  private Library library;

  public XmlExport(String location, Library lib) {
    file = new File(location);
    library = lib;
  }

  /**
   * Write.
   */
  public void export() {
    XMLEncoder encoder = null;
    try {
      encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file)));
    } catch (FileNotFoundException e) {
      log.error("Exception");
      log.trace(StackTrace.stackTrace(e));
    }
    encoder.writeObject(library);
    encoder.close();
  }
  
  /**
   * Method to add a playlist to the current xml.
   * It makes one playlist from the xml playlist and to be added playlist.
   * @param pl playlist to be added.
   */
  public void add(Playlist pl) {
    library.add(pl);
  }

}
