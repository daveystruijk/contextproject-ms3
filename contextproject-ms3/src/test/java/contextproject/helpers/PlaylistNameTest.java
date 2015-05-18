package contextproject.helpers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.io.File;


public class PlaylistNameTest {

  @Test
  public void macTest() {
    String delimiter = File.separator;
    String path = delimiter + "Users" + delimiter + "Me" + delimiter + "file";
    assertEquals("file", PlaylistName.getName(path));
  }

}
