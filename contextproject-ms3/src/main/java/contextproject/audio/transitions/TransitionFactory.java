package contextproject.audio.transitions;

import contextproject.AppConfig;
import contextproject.audio.TrackProcessor;
import contextproject.audio.transitions.BaseTransition.TransitionDoneCallback;

public class TransitionFactory {

  /**
   * Creates a transition based on the application's configuration.
   * @return Either a FadeInOutTransition or an EscalatieTransition.
   */
  public BaseTransition createTransition(TrackProcessor from, TrackProcessor to,
      TransitionDoneCallback callback) {
    if (AppConfig.useAirhornTransition == true) {
      return new EscalatieTransition(from, to, callback);
    } else {
      return new FadeInOutTransition(from, to, callback);
    }
  }
}
