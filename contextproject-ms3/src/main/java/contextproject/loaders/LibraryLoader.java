package contextproject.loaders;

import contextproject.helpers.StackTrace;
import contextproject.models.Playlist;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.XMLDecoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LibraryLoader implements PlaylistLoader {

  private static Logger log = LogManager.getLogger(LibraryLoader.class.getName());
  private File file;

  public LibraryLoader(String folder) {
    file = new File(folder);
  }

  @Override
  public Playlist load() {

    Playlist playlist = null;
    try {
      FileInputStream fis = new FileInputStream(file);
      XMLDecoder in = new XMLDecoder(fis);
      playlist = (Playlist) in.readObject();
      in.close();
      fis.close();

    } catch (FileNotFoundException e) {
      log.error("Exception");
      log.trace(StackTrace.stackTrace(e));
    } catch (IOException e) {
      log.error("Exception");
      log.trace(StackTrace.stackTrace(e));
    }

    return playlist;
  }

}
