package contextproject.loaders;

import java.beans.XMLDecoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import contextproject.helpers.StackTrace;
import contextproject.models.Library;

public class LibraryLoader {

  private static Logger log = LogManager.getLogger(LibraryLoader.class.getName());
  private File file;

  public LibraryLoader(String folder) {
    file = new File(folder);
  }

  /**
   * Load the library from the xml.
   * @return library.
   */
  public Library load() {

    Library library = null;
    try {
      FileInputStream fis = new FileInputStream(file);
      XMLDecoder in = new XMLDecoder(fis);
      library = (Library) in.readObject();
      in.close();
      fis.close();

    } catch (FileNotFoundException e) {
      log.error("Exception");
      log.trace(StackTrace.stackTrace(e));
    } catch (IOException e) {
      log.error("Exception");
      log.trace(StackTrace.stackTrace(e));
    }

    return library;
  }

}
