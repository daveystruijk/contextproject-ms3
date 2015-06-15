package contextproject.helpers;

import be.tarsos.transcoder.Attributes;

import contextproject.App;
import contextproject.audio.TrackProcessor;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.testfx.framework.junit.ApplicationTest;

import javafx.stage.Stage;

public class AudioProgressTest extends ApplicationTest {

  private Attributes attr;
  private TrackProcessor process;
  private AudioProgress audio;

  @Before
  public void setUp() {
    attr = Mockito.mock(Attributes.class);
    process = new TrackProcessor(attr);
  }

  @Override
  public void start(Stage stage) throws Exception {
    // TODO Auto-generated method stub
    App ap = new App();
    ap.setUp(stage);
  }

  @Test
  public void constructTest() {
    audio = new AudioProgress(process);
  }

}
