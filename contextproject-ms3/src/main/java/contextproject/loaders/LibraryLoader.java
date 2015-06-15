package contextproject.loaders;

import contextproject.helpers.StackTrace;
import contextproject.models.Library;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.XMLDecoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class LibraryLoader {

  private static Logger log = LogManager.getLogger(LibraryLoader.class.getName());
  private File file;

  public LibraryLoader(String folder) {
    file = new File(folder);
  }

  /**
   * Load the library from the xml.
   * 
   * @return library.
   * @throws IOException throws exception when the file is not found.
   */
  public Library load() throws IOException {

    Library library = null;
    try {
      FileInputStream fis = new FileInputStream(file);
      XMLDecoder in = new XMLDecoder(fis);
      library = (Library) in.readObject();
      in.close();
      fis.close();
    } catch (ClassCastException e) {
      log.warn("library.xml is corrupted. Starting with empty library...");
      log.trace(StackTrace.stackTrace(e));
      library = new Library();
    } 

    return library;
  }

}
