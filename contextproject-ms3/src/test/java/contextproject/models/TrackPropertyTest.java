package contextproject.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;

public class TrackPropertyTest {

  private Key testKey = new Key("A");
  URL resourceUrl = getClass().getResource("/beep.mp3");
  Path resourcePath;

  private Track testTrack;
  private TrackProperty testProperty;

  /**
   * This is were the Trackproperty is initialized for this UnitTest.
   */
  @Before
  public void setUp() {
    try {
      resourcePath = Paths.get(resourceUrl.toURI());
      testTrack = new Track(resourcePath.toString(), new Hashtable<String, String>());

    } catch (URISyntaxException e) {
      fail("file wans't read correctly");
      e.printStackTrace();
    }
    testProperty = new TrackProperty("testName", "testArtist", 120, testKey, testTrack);
  }

  @Test
  public void keyTest() {
    testProperty.setKey(new Key("B"));
    assertEquals(testProperty.keyProperty().get(), testProperty.getKey());
  }

  @Test
  public void artistTest() {
    testProperty.setArtist("Felix!");
    assertEquals(testProperty.artistProperty().get(), testProperty.getArtist());
  }

  @Test
  public void titleTest() {
    testProperty.setTitle("Cool track!");
    assertEquals(testProperty.titleProperty().get(), testProperty.getTitle());
  }

  @Test
  public void bpmTest() {
    testProperty.setBpm(220.d);
    assertEquals(testProperty.bpmProperty().get(), testProperty.getBpm(), 0.00001f);
  }

  @Test
  public void trackTest() {
    testProperty.setatrack(testTrack);
    assertEquals(testProperty.trackProperty().get(), testProperty.getTrack());
  }
}
