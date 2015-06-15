package contextproject.audio;

import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.io.TarsosDSPAudioFormat;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SkipAudioProcessorTest {

  private AudioEvent event;
  private float[] floatBuffer;

  @Before
  public void setUp() {
    event = new AudioEvent(new TarsosDSPAudioFormat(100, 200, 110, true, false));
    floatBuffer = new float[10];
    for (int i = 0; i < 10; i++) {
      floatBuffer[i] = 7.0f + i / 10;
    }
    event.setFloatBuffer(floatBuffer);
  }

  @Test
  public void processTest() {
    SkipAudioProcessor skip = new SkipAudioProcessor(10, false, null);
    assertEquals(skip.process(event), false);
    skip.processingFinished();
  }
}
