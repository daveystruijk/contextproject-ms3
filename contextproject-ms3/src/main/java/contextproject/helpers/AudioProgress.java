package contextproject.helpers;

import contextproject.App;
import contextproject.audio.TrackProcessor;
import contextproject.controllers.PlayerControlsController;
import contextproject.controllers.WindowController;

public class AudioProgress extends Thread {

  private double progress = 0;
  //private TrackProcessor tp;
  private PlayerControlsController pcc;
  private WindowController wc;

  /**
   * constructor for the audio progress thread.
   * 
   * @param tp
   *          the track processor.
   */
  public AudioProgress(TrackProcessor tp) {
    //this.tp = tp;
    wc = App.getController();
    pcc = wc.getPlayerControlsController();
  }

  @Override
  public void run() {
    pcc.setProgres(progress);
    while (progress < 1) {
      progress = 0;
      pcc.setProgres(progress);
    }
  }
}