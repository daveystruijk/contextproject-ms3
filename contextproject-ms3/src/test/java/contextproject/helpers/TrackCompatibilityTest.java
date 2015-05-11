package contextproject.helpers;

import static org.junit.Assert.assertEquals;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import contextproject.models.Key;
import contextproject.models.Track;

import org.junit.Test;

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

}