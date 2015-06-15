package contextproject.audio;

import static org.junit.Assert.assertEquals;

import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.io.TarsosDSPAudioFormat;
import be.tarsos.dsp.onsets.PrintOnsetHandler;

import org.junit.Before;
import org.junit.Test;

public class ComplexOnsetDetectorTest {

  private AudioEvent event;
  private float[] floatBuffer;

  /**
   * Set up variables.
   */
  @Before
  public void setUp() {
    event = new AudioEvent(new TarsosDSPAudioFormat(100, 200, 110, true, false));
    floatBuffer = new float[10];
    for (int i = 0; i < 10; i++) {
      floatBuffer[i] = 7.0f + i / 10;
    }
  }

  @Test
  public void singleArgumentTest() {
    ComplexOnsetDetector complex = new ComplexOnsetDetector(20);
    complex.setThreshold(10);
    assertEquals(complex.getFirstOnset(), 0.0, 0.0001d);
    complex.setHandler(new PrintOnsetHandler());
    complex.processingFinished();
  }

  @Test
  public void processTest() {
    ComplexOnsetDetector complex = new ComplexOnsetDetector(10);
    event.setFloatBuffer(floatBuffer);
    assertEquals(complex.process(event), true);
  }
}
