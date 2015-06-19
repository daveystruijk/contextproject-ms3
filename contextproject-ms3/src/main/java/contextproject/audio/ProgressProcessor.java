package contextproject.audio;

import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;

import contextproject.AppConfig;
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
    if (AppConfig.useAirhornTransition) {
      this.skip = 0;
    } else {
      this.skip = skip;
    }
    this.progressPcs = pcc.getPcs();
  }

  @Override
  public boolean process(AudioEvent audioEvent) {
    if (progress < 0.99) {
      double currentTime = audioEvent.getTimeStamp();
      double oldValue = progress;
      double newValue = (currentTime - skip) / (duration - skip);
      progress = newValue;
      if (newValue < 0) {
        progressPcs.firePropertyChange("progress", oldValue, 0.0);
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
