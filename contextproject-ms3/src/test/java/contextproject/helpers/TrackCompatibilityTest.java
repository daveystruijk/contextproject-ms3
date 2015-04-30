package contextproject.helpers;

import static org.junit.Assert.*;

import org.junit.Test;

import contextproject.models.Key;

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
}