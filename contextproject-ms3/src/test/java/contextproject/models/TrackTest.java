package contextproject.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TrackTest {

  @Test
  public void trackConstructorTest() {
    URL resourceUrl = getClass().getResource("/beep.mp3");
    Path resourcePath;
    try {
      resourcePath = Paths.get(resourceUrl.toURI());
      Track track = new Track(resourcePath.toString());
      assertEquals(track.getAlbum(), "Beeps of the year");
      assertEquals(track.getTitle(), "Beep");
      assertEquals(track.getArtist(), "Flix");
      assertEquals(track.getLength(), new Long(575));
    } catch (URISyntaxException e) {
      fail("file wans't read correctly");
      e.printStackTrace();
    }

  }

}