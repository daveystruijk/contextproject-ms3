package contextproject.helpers;

import static org.junit.Assert.*;

import org.junit.Test;

public class TrackCompatibilityTest {

  @Test
  public void testBpmScore() {
    assertEquals(1.0f, TrackCompatibility.getBpmScore(128, 128), 0.0001);
  }
}