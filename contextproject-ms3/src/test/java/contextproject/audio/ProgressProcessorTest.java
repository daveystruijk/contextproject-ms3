package contextproject.audio;

import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.io.TarsosDSPAudioFormat;

import contextproject.controllers.PlayerControlsController;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ProgressProcessorTest {

  private AudioEvent event;
  private float[] floatBuffer;
  
  @Before
  public void setUp(){
    event = new AudioEvent(new TarsosDSPAudioFormat(100, 200, 110, true, false));
    floatBuffer = new float[10];
    for (int i = 0; i < 10; i++) {
      floatBuffer[i] = 7.0f + i / 10;
    }
    event.setFloatBuffer(floatBuffer);
  }
  
  @Test
  public void test() {
    PlayerControlsController player = new PlayerControlsController();
    ProgressProcessor prog = new ProgressProcessor(10, 20, player);
    prog.processingFinished();
  }

}
