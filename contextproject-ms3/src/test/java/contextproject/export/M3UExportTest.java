package contextproject.export;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import contextproject.models.Playlist;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class M3UExportTest {

  private String name;
  private M3UExport export;
  private Playlist pl;
  private String song;

  /**
   * Before every test this method is done first to initialize the M3UExport.
   */
  @Before
  public void initialize() {
    song = System.getProperty("user.dir").toString() + File.separator + "src" + File.separator
        + "test" + File.separator + "resources" + File.separator + "beep.mp3";
    name = "TESTLISTtest";
    ArrayList<String> list = new ArrayList<String>();
    list.add(song);
    pl = new Playlist(list);
    export = new M3UExport(pl, name);
  }

  @Test
  public void getNameTest() {
    assertEquals(export.getName(), name);
  }

  @Test
  public void getPlaylistTest() {
    assertEquals(export.getPlaylist(), pl);
  }

  @Test
  public void getAbsolutePathTest() {
    String abPath = System.getProperty("user.dir").toString() + File.separator + "src"
        + File.separator + "main" + File.separator + "playlists" + File.separator + name + ".m3u";
    assertEquals(export.getAbsolutePath(), abPath);
  }

  @Test
  public void exportTest() {
    export.export();
    File file = new File(System.getProperty("user.dir").toString() + File.separator + "src"
        + File.separator + "main" + File.separator + "playlists" + File.separator + name + ".m3u");
    assertTrue(file.exists() && !file.isDirectory());
  }

  @Test
  public void songTest() {
    export.export();
    FileReader fr = null;
    try {
      fr = new FileReader(export.getAbsolutePath());
    } catch (FileNotFoundException e) {
      System.out.println("No such file");
    }
    BufferedReader br = new BufferedReader(fr);
    try {
      assertEquals(br.readLine(), song);
    } catch (IOException e) {
      System.out.println("No line found.");
    }
  }

  @Test
  public void fileTest() {
    export.export();
    Scanner sc = new Scanner(export.getAbsolutePath());
    while (sc.hasNextLine()) {
      assertTrue(sc.hasNextLine());
      sc.nextLine();
    }
    assertFalse(sc.hasNextLine());
    sc.close();
  }

}
