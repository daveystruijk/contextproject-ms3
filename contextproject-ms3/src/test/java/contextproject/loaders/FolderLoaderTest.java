package contextproject.loaders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import contextproject.models.Playlist;
import contextproject.models.Track;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class FolderLoaderTest {

  private Playlist pl;
  private static String directory;

  /**
   * Before running the test this method is done first to initialize the loader.
   */
  @Before
  public void initialize() {
    directory = System.getProperty("user.dir").toString() + File.separator + "src" + File.separator
        + "test" + File.separator + "resources" + File.separator + "loadTest";
    pl = new Playlist();
  }

  @Test
  public void getListTest() {
    FolderLoader loader = new FolderLoader(directory);
    assertEquals(loader.getList(), pl);
  }

  @Test
  public void addNothingTest() {
    FolderLoader loader = new FolderLoader(directory + File.separator + "EmptyDirectory");
    assertTrue(loader.load().isEmpty());
  }

  @Test
  public void addTextTest() {
    FolderLoader loader = new FolderLoader(directory + File.separator + "SubDirectory"
        + File.separator + "Beeps" + File.separator + "TextFile");
    assertTrue(loader.load().isEmpty());
  }

  @Test
  public void addSubDirectoryTest() {
    pl.add(new Track(directory + File.separator + "SubDirectory" + File.separator + "Beeps"
        + File.separator + "HighBeep" + File.separator + "HighBeep.mp3"));
    pl.add(new Track(directory + File.separator + "SubDirectory" 
        + File.separator + "shortBeep.mp3"));
    FolderLoader loader = new FolderLoader(directory + File.separator + "SubDirectory");
    assertTrue(loader.load().containsAll(pl) && pl.containsAll(loader.load()));
  }

  @Test
  public void noDirectoryTest2() {
    FolderLoader loader = new FolderLoader("noDirectory");
    assertTrue(loader.load().isEmpty());
  }

  @Test
  public void loadTest() {
    pl.add(new Track(directory + File.separator + "buz.mp3"));
    pl.add(new Track(directory + File.separator + "buz2.mp3"));
    pl.add(new Track(directory + File.separator + "SubDirectory" + File.separator + "Beeps"
        + File.separator + "HighBeep" + File.separator + "HighBeep.mp3"));
    pl.add(new Track(directory + File.separator + "SubDirectory" 
        + File.separator + "shortBeep.mp3"));
    FolderLoader loader = new FolderLoader(directory);
    assertTrue(loader.load().containsAll(pl) && pl.containsAll(loader.load()));
  }

}
