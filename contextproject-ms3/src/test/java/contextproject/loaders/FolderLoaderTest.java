package contextproject.loaders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

public class FolderLoaderTest {

  ArrayList<String> array;
  private static String directory;

  /**
   * Before running the test this method is done first to initialize the loader.
   */
  @Before
  public void initialize() {
    directory = System.getProperty("user.dir").toString()
        + File.separator + "src" + File.separator + "test" + File.separator 
        + "resources" + File.separator + "loadTest";
    array = new ArrayList<String>();
  }

  @Test
  public void getListTest() {
    FolderLoader loader = new FolderLoader(directory);
    assertEquals(loader.getList(), array);
  }

  @Test
  public void addNothingTest() {
    FolderLoader loader = new FolderLoader(directory + File.separator + "EmptyDirectory");
    assertTrue(loader.load().isEmpty());
  }

  @Test
  public void addTextTest() {
    FolderLoader loader = new FolderLoader(directory
        + File.separator + "SubDirectory" + File.separator + "Beeps" + File.separator + "TextFile");
    assertTrue(loader.load().isEmpty());
  }

  @Test
  public void addSubDirectoryTest() {
    array
        .add(directory
            + File.separator + "SubDirectory" + File.separator + "Beeps" + File.separator
            + "HighBeep" + File.separator + "HighBeep.mp3");
    array.add(directory + File.separator + "SubDirectory" + File.separator + "shortBeep.mp3");
    FolderLoader loader = new FolderLoader(directory + File.separator + "SubDirectory");
    assertEquals(loader.load().toString(), array.toString());
  }

  @Test
  public void noDirectoryTest2() {
    FolderLoader loader = new FolderLoader("noDirectory");
    assertTrue(loader.load().isEmpty());
  }

  @Test
  public void loadTest() {
    array.add(directory + File.separator + "buz.mp3");
    array.add(directory + File.separator + "buz2.mp3");
    array
        .add(directory
            + File.separator + "SubDirectory" + File.separator + "Beeps" + File.separator 
            + "HighBeep" + File.separator + "HighBeep.mp3");
    array.add(directory + File.separator + "SubDirectory" + File.separator + "shortBeep.mp3");
    FolderLoader loader = new FolderLoader(directory);
    assertEquals(loader.load().toString(), array.toString());
  }

}
