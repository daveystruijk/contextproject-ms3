package contextproject.helpers;

import contextproject.App;
import contextproject.audio.PlayerService;
import contextproject.audio.TrackProcessor;
import contextproject.controllers.PlayerControlsController;
import contextproject.controllers.WindowController;

public class AudioProgress extends Thread {

  private double progress = 0;
  private TrackProcessor tp;
  private PlayerControlsController pcc;
  private WindowController wc;

  /**
   * constructor for the audio progress thread.
   * @param tp the track processor.
   */
  public AudioProgress(TrackProcessor tp) {
    this.tp = tp;
    wc = App.getController();
    pcc = wc.getPlayerControlsController();
  }

  @Override
  public void run() {
    pcc.setProgres(progress);
    while (progress < 1) {
      progress = tp.getProgress();
      pcc.setProgres(progress);
      System.out.println("progress = " + (progress * 100) + "%");
      if (progress > 0.05) {
        System.out.println("transition this shit");
        PlayerService.getInstance().transition();
      }
    }
    PlayerService.getInstance().transition();
  }
}