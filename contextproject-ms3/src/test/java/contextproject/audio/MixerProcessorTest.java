package contextproject.audio;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MixerProcessorTest {

  @Test
  public void manipulateTest() {
    MixerProcessor mixProcessor = new MixerProcessor();
    assertEquals(mixProcessor.manipulateSignal(10), 10.001f, 0.0001f);
    mixProcessor.processingFinished();
  }

}
