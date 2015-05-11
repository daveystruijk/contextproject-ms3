package contextproject.helpers;

import static org.junit.Assert.assertEquals;

import contextproject.models.Key;
import contextproject.models.Track;

import org.junit.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TrackCompatibilityTest {

  @Test
  public void testBpmScore100() {
    assertEquals(1.0f, TrackCompatibility.getBpmScore(128, 128), 0.0001);
  }

  @Test
  public void testBpmScore75() {
    assertEquals(0.75f, TrackCompatibility.getBpmScore(128, 123), 0.0001);
  }

  @Test
  public void testKeyScore() {
    assertEquals(1.0f, TrackCompatibility.getKeyScore(new Key("B"), new Key("B")), 0.0001);
  }

  @Test
  public void testDifferentKeyScore() {
    assertEquals(0.7f, TrackCompatibility.getKeyScore(new Key("A"), new Key("B")), 0.0001);
  }

  @Test
  public void testSameTracks() {
    URL resourceUrl = getClass().getResource("/beep.mp3");
    Path resourcePath;
    try {
      resourcePath = Paths.get(resourceUrl.toURI());
      Track track = new Track(resourcePath.toString());
      track.setBpm(180);
      track.setKey(new Key("A"));
      assertEquals(TrackCompatibility.getScore(track, track), 1.0f, 0.0001);
    } catch (URISyntaxException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @Test
  public void testDifferentTracks() {
    URL resourceUrl = getClass().getResource("/beep.mp3");
    Path resourcePath;
    try {
      resourcePath = Paths.get(resourceUrl.toURI());
      Track track = new Track(resourcePath.toString());
      Track track2 = new Track(resourcePath.toString());
      track.setBpm(180);
      track.setKey(new Key("A"));
      track2.setBpm(150);
      track2.setKey(new Key("B"));
      assertEquals(TrackCompatibility.getScore(track, track2), 0.0f, 0.0001);
    } catch (URISyntaxException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}