package contextproject.audio;

import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;

import contextproject.controllers.PlayerControlsController;

import java.beans.PropertyChangeSupport;

public class ProgressProcessor implements AudioProcessor{

  private PropertyChangeSupport progressPcs;
  private double progress = 0;
  private double duration;
  
  public void setUp(double duration, PlayerControlsController pcc) {
    this.duration = duration;
    this.progressPcs = pcc.getPcs();
  }
  
  @Override
  public boolean process(AudioEvent audioEvent) {
    double currentTime = audioEvent.getTimeStamp();
    double oldValue = progress;
    double newValue = currentTime / duration;
    progress = newValue;
    progressPcs.firePropertyChange("progress", oldValue, newValue);
    return true;
  }

  @Override
  public void processingFinished() {
  }

}
