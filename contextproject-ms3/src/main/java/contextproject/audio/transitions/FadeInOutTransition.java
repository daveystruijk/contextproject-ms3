package contextproject.audio.transitions;

import contextproject.audio.TrackProcessor;

public class FadeInOutTransition extends BaseTransition {

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
        e.printStackTrace();
      }
    }
  }
}
