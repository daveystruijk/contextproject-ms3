package contextproject.audio;

import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.io.TarsosDSPAudioFormat;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MixerProcessorTest {
  private AudioEvent event;
  private float[] floatBuffer;
  private float[] floatBuffer2;

  @Before
  public void setUp() {
    event = new AudioEvent(new TarsosDSPAudioFormat(100, 200, 110, true, false));
    floatBuffer = new float[10];
    floatBuffer2 = new float[10];
    for (int i = 0; i < 10; i++) {
      floatBuffer[i] = 7.0f + i / 10;
      floatBuffer2[i] = -7.0f + i / 10;
    }
    event.setFloatBuffer(floatBuffer);
  }

  @Test
  public void manipulateTest() {
    MixerProcessor mixProcessor = new MixerProcessor();
    assertEquals(mixProcessor.manipulateSignal(10), 10.001f, 0.0001f);
    mixProcessor.processingFinished();
  }

  @Test
  public void processTest() {
    MixerProcessor mixProcessor = new MixerProcessor();
    assertEquals(mixProcessor.process(event), true);
  }

  @Test
  public void negativeProcessTest() {
    MixerProcessor mixProcessor = new MixerProcessor();
    event.setFloatBuffer(floatBuffer2);
    mixProcessor.process(event);
  }

}
