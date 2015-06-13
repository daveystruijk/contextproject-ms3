package contextproject.audio;

import be.tarsos.transcoder.Attributes;

import contextproject.models.MusicalKey;
import contextproject.models.Track;

import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class AirhornProcessorTest {

  private Track track;
  private Attributes attr;

  @Before
  public void setUp() {
    track = new Track();
    track.setBpm(120);
    track.setPath("a");
    track.setKey(new MusicalKey("11A"));
    attr = Mockito.mock(Attributes.class);
  }

  @Test
  public void test() {
    AirhornProcessor airProcessor = new AirhornProcessor(attr, track);
    assertNotEquals(airProcessor, null);
  }
}
