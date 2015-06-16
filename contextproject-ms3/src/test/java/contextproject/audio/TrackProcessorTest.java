package contextproject.audio;

import contextproject.audio.TrackProcessor.PlayerState;
import contextproject.audio.transitions.FadeInOutTransition;

import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.io.TarsosDSPAudioFormat;
import be.tarsos.transcoder.Attributes;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeListenerProxy;

public class TrackProcessorTest {
  private Attributes attr;
  private TrackProcessor track;
  private AudioEvent event;
  private float[] floatBuffer;

  @Before
  public void setUp() {
    attr = Mockito.mock(Attributes.class);
    track = new TrackProcessor(attr);
    event = new AudioEvent(new TarsosDSPAudioFormat(100, 200, 110, true, false));
    floatBuffer = new float[10];
    for (int i = 0; i < 10; i++) {
      floatBuffer[i] = 7.0f + i / 10;
    }
  }

  @Test
  public void processTest() {
    assertEquals(track.process(event), true);
    track.processingFinished();
  }

  @Test
  public void processTestWithTransitionTime() {
    track.setupTransition(1, new FadeInOutTransition(track, track, null));
    assertEquals(track.process(event), true);
  }

  @Test
  public void loadTest() {
    track.unload();
    assertEquals(track.getState(), PlayerState.NO_FILE_LOADED);
  }

  @Test
  public void getTest() {
    assertEquals(track.getTrack(), null);
    track.setTempo(120);
    assertEquals(track.getTempo(), 120, 0.001f);
  }

  @Test
  public void propertyListenerTest() {
    PropertyChangeListener prop = new PropertyChangeListenerProxy("name", null);
    track.addPropertyChangeListener(prop);
    track.removePropertyChangeListener(prop);
  }
}
