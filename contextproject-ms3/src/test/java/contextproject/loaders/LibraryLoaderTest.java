package contextproject.loaders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import contextproject.formats.XmlExport;
import contextproject.models.Library;
import contextproject.models.Playlist;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


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

  @Test(expected = IOException.class)
  public void noFileTest() throws IOException {
    LibraryLoader lib = new LibraryLoader("noDirectory");
    lib.load();
  }

  @Test
  public void equalsPlaylistTest() {
    FolderLoader loader = new FolderLoader(directory);
    Playlist pl = loader.load();
    Library library = new Library();
    library.add(pl);
    XmlExport export = new XmlExport(directory + fileName, library);
    export.export();
    LibraryLoader lib = new LibraryLoader(directory + fileName);
    try {
      assertEquals(library, lib.load());
    } catch (IOException e) {
      assertFalse(library.equals(null));
    }
  }

}
