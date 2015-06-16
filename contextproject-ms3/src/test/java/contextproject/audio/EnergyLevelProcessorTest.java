package contextproject.audio;

import contextproject.models.Track;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.io.TarsosDSPAudioFormat;
import be.tarsos.transcoder.Attributes;
import be.tarsos.transcoder.ffmpeg.EncoderException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.sound.sampled.LineUnavailableException;

public class EnergyLevelProcessorTest {

  private Attributes mockAttributes;
  private EnergyLevelProcessor energyProcessor;
  private AudioEvent event;
  private float[] floatBuffer;

  /**
   * This test the processor for the Energy Level. For now relies on Mockito to mock certain Tarsos
   * elements.
   */
  @Before
  public void setUp() {
    mockAttributes = Mockito.mock(Attributes.class);
    energyProcessor = new EnergyLevelProcessor(mockAttributes);
    assertNotEquals(energyProcessor, null);
    event = new AudioEvent(new TarsosDSPAudioFormat(100, 200, 110, true, false));
    floatBuffer = new float[10];
    for (int i = 0; i < 10; i++) {
      floatBuffer[i] = 7.0f + i / 10;
    }
    event.setFloatBuffer(floatBuffer);
  }

  @Test
  public void constTest() {
    assertNotEquals(new EnergyLevelProcessor(mockAttributes), energyProcessor);
  }

  @Test
  public void getAverageTest() {
    assertEquals(energyProcessor.process(event), false);
    assertEquals(energyProcessor.getAverageEnergy(), 7.0f, 0.0011f);
    energyProcessor.processingFinished();
  }
}
