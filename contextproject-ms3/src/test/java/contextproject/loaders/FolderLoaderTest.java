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
    directory = System.getProperty("user.dir").toString() + "\\src\\test\\resources\\loadTest";
    array = new ArrayList<String>();
  }

  @Test
  public void getListTest() {
    FolderLoader loader = new FolderLoader(directory);
    assertEquals(loader.getList(), array);
  }

  @Test
  public void addNothingTest() {
    FolderLoader loader = new FolderLoader(directory + "\\EmptyDirectory");
    assertTrue(loader.getAllMusic().isEmpty());
  }

  @Test
  public void addTextTest() {
    FolderLoader loader = new FolderLoader(directory + "\\SubDirectory\\Beeps\\TextFile");
    assertTrue(loader.getAllMusic().isEmpty());
  }

  @Test
  public void addSubDirectoryTest() {
    array.add(directory + "\\SubDirectory\\Beeps\\HighBeep\\HighBeep.mp3");
    array.add(directory + "\\SubDirectory\\shortBeep.mp3");
    FolderLoader loader = new FolderLoader(directory + "\\SubDirectory");
    assertEquals(loader.getAllMusic().toString(), array.toString());
  }

  @Test
  public void noDirectoryTest() {
    FolderLoader loader = new FolderLoader("noDirectory");
    File file = new File("NoDirectory");
    loader.addToList(file);
    assertTrue(loader.getList().isEmpty());
  }

  @Test
  public void noDirectoryTest2() {
    FolderLoader loader = new FolderLoader("noDirectory");
    assertTrue(loader.getAllMusic().isEmpty());
  }

  @Test
  public void getAllMusicTest() {
    array.add(directory + "\\buz.mp3");
    array.add(directory + "\\buz2.mp3");
    array.add(directory + "\\SubDirectory\\Beeps\\HighBeep\\HighBeep.mp3");
    array.add(directory + "\\SubDirectory\\shortBeep.mp3");
    FolderLoader loader = new FolderLoader(directory);
    assertEquals(loader.getAllMusic().toString(), array.toString());
  }

}
