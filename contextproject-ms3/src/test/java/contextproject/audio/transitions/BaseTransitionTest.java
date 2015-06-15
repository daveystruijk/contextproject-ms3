package contextproject.audio.transitions;

import static org.junit.Assert.assertEquals;

import contextproject.audio.TrackProcessor;
import contextproject.audio.transitions.BaseTransition.TransitionDoneCallback;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class BaseTransitionTest {

  private TrackProcessor from;
  private TrackProcessor to;
  private TransitionDoneCallback callback;

  /**
   * Set up variables.
   */
  @Before
  public void setUp() {
    from = Mockito.mock(TrackProcessor.class);
    to = Mockito.mock(TrackProcessor.class);
    callback = Mockito.mock(TransitionDoneCallback.class);

  }
  
  @Test
  public void test() {
    BaseTransition base = new FadeInOutTransition(from, to, callback);
    assertEquals(base.getClass(), FadeInOutTransition.class);
  }

}
