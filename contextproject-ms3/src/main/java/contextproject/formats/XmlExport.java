package contextproject.formats;

import contextproject.helpers.StackTrace;
import contextproject.loaders.LibraryLoader;
import contextproject.models.Playlist;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

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
  public void export() {
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

}
