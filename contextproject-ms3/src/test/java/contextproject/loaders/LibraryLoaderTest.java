package contextproject.loaders;

import static org.junit.Assert.assertEquals;

import contextproject.formats.XmlExport;
import contextproject.models.Playlist;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class LibraryLoaderTest {

  private String directory;
  private String fileName;

  /**
   * Before running the test this method is done first to initialize the loader.
   */
  @Before
  public void initialize() {
    directory = System.getProperty("user.dir").toString() + File.separator + "src" + File.separator
        + "test" + File.separator + "resources" + File.separator + "loadTest" + File.separator;
    fileName = "test.xml";
  }

  /**
   * After running tests the file that is used to test should be deleted.
   */
  @After
  public void removeFile() {
    File file = new File(directory + fileName);
    if (file.exists()) {
      file.delete();
    }
  }

  @Test
  public void noFileTest() {
    LibraryLoader lib = new LibraryLoader("noDirectory");
    assertEquals(lib.load(), null);
  }

  @Test
  public void equalsPlaylistTest() {
    FolderLoader loader = new FolderLoader(directory);
    Playlist pl = loader.load();
    XmlExport export = new XmlExport(directory + fileName, pl);
    export.export();
    LibraryLoader lib = new LibraryLoader(directory + fileName);
    assertEquals(pl, lib.load());
  }

}
