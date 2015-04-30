package contextproject.export;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import contextproject.models.Playlist;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

public class M3UExportTest {

  private Playlist pl;
  private String song;
  private M3UBuilder builder;

  /**
   * Before every test this method is done first to initialize the M3UExport.
   */
  @Before
  public void initialize() {
    song = System.getProperty("user.dir").toString() + File.separator + "src" + File.separator
        + "test" + File.separator + "resources" + File.separator + "beep.mp3";
    ArrayList<String> list = new ArrayList<String>();
    list.add(song);
    pl = new Playlist(list);
    builder = new M3UBuilder(pl);
  }

  @Test
  public void buildTest() {
    assertEquals(builder.build(), song + "\n");
  }
  
  @Test
  public void buildFailTest() {
    assertFalse(builder.build().equals("Fail"));
  }
}
