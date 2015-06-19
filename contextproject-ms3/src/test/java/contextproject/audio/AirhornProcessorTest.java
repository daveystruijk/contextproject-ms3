package contextproject.audio;

import static org.junit.Assert.assertNotEquals;

import contextproject.models.MusicalKey;
import contextproject.models.Track;

import org.junit.Before;
import org.junit.Test;

public class AirhornProcessorTest {

  private Track track;

  /**
   * Set up variables.
   */
  @Before
  public void setUp() {
    track = new Track();
    track.setBpm(120);
    track.setPath("a");
    track.setKey(new MusicalKey("11A"));
  }

  @Test
  public void test() {
    AirhornProcessor airProcessor = new AirhornProcessor();
    assertNotEquals(airProcessor, null);
  }
}
