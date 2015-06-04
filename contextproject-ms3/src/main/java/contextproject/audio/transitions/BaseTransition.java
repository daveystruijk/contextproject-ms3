package contextproject.audio.transitions;

import contextproject.audio.TrackProcessor;

public interface BaseTransition {
  public void begin(TrackProcessor from, TrackProcessor to);
}
