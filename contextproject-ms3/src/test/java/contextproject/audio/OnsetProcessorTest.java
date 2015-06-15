package contextproject.audio;

import contextproject.models.Track;

import be.tarsos.transcoder.Attributes;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;

public class OnsetProcessorTest {

  @Test
  public void test() {
    Attributes attr = Mockito.mock(Attributes.class);
    OnsetProcessor onset = new OnsetProcessor(attr);
    assertEquals(onset.getFirstOnset(), 0, 0.001f);
    onset.handleOnset(10, 40);
  }
}
