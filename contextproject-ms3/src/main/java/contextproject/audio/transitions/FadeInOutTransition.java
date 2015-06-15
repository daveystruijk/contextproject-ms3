package contextproject.audio.transitions;

import contextproject.audio.TrackProcessor;
import contextproject.helpers.StackTrace;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FadeInOutTransition extends BaseTransition {
  private static Logger log = LogManager.getLogger(TrackProcessor.class.getName());

  
  /**
   * In out fader transition.
   * 
   * @param from
   *          from track processor.
   * @param to
   *          to track processor.
   * @param callback
   *          transition callback
   */
  public FadeInOutTransition(TrackProcessor from, TrackProcessor to, 
      TransitionDoneCallback callback) {
    super(from, to, callback);
  }

  @Override
  public void begin(TrackProcessor from, TrackProcessor to) {
    to.setGain(0.0);
    to.play();
    for (int i = 0; i < 16; i++) {
      from.setGain(1.0 - (i / 16.0));
      to.setGain((double) i / 16.0);
      try {
        double timePerBeat = 60.0 / from.getTrack().getBpm();
        Thread.sleep(Math.round(timePerBeat * 1000));
      } catch (InterruptedException e) {
        log.error("Error occured in FadeInOutProcessor while calling begin");
        log.trace(StackTrace.stackTrace(e));
      }
    }
  }
}
