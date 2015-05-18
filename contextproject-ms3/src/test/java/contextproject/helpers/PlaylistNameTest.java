package contextproject.helpers;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class PlaylistNameTest {

  @Test
  public void MacTest() {
    String delimiter = File.separator;
    String path = delimiter + "Users" + delimiter + "Me" + delimiter + "file";
    assertEquals("file",PlaylistName.getName(path));
  }

}
