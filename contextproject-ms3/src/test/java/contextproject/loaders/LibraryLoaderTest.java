package contextproject.loaders;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class LibraryLoaderTest {

  private String directory;

  /**
   * Before running the test this method is done first to initialize the loader.
   */
  @Before
  public void initialize() {
    directory = System.getProperty("user.dir").toString() + File.separator + "src" + File.separator
        + "test" + File.separator + "resources" + File.separator + "loadTest" + File.separator
        + "test.xml";
  }

  @Test
  public void test() {
    LibraryLoader lib = new LibraryLoader("noDirectory");
    assertEquals(lib.load(), null);
  }

}
