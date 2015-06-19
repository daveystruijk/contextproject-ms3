package contextproject.audio.transitions;

import be.tarsos.transcoder.Attributes;

import contextproject.AppConfig;
import contextproject.audio.TrackProcessor;
import contextproject.audio.transitions.TransitionFactory;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class TransitionFactoryTest {

  @Test
  public void test() {
    AppConfig app = new AppConfig();
    assertNotEquals(app, null);
    AppConfig.useAirhornTransition = true;
    TransitionFactory fac = new TransitionFactory();
    Attributes attr = new Attributes();
    attr.setChannels(2);
    attr.setSamplingRate(10);
    attr.setFormat("wav");
    TrackProcessor from = new TrackProcessor(attr);
    TrackProcessor to = new TrackProcessor(attr);
    fac.createTransition(from, to, null);

  }

}
