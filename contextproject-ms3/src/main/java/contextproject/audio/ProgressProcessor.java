package contextproject.audio;

import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;

import contextproject.controllers.PlayerControlsController;

import java.beans.PropertyChangeSupport;

public class ProgressProcessor implements AudioProcessor {

  private PropertyChangeSupport progressPcs;
  private double progress = 0;
  private double duration;
  private double skip;
  /**
   * setup for the progress bar processor.
   * 
   * @param duration
   *          time until transition.
   * @param skip
   *          amount of seconds skipped at start of track
   * @param pcc
   *          the player controls controller.
   */
  public ProgressProcessor(double duration, double skip, PlayerControlsController pcc) {
    this.duration = duration;
    this.skip = skip;
    this.progressPcs = pcc.getPcs();
  }

  @Override
  public boolean process(AudioEvent audioEvent) {
    if (progress < 1) {
      double currentTime = audioEvent.getTimeStamp();
      double oldValue = progress;
      double offset = (skip / currentTime);
      double newValue = (currentTime / duration) - offset;
      progress = newValue;
      if (newValue < 0) {
        progressPcs.firePropertyChange("progress", oldValue, 0);
        return true;
      } else {
        progressPcs.firePropertyChange("progress", oldValue, newValue);
        return true;
      }
    }
    return true;
  }

  @Override
  public void processingFinished() {
  }

}
