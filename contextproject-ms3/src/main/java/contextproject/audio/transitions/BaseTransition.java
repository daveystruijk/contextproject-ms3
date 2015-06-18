package contextproject.audio.transitions;

import contextproject.audio.TrackProcessor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class BaseTransition implements Runnable {
  
  private static Logger log = LogManager.getLogger(BaseTransition.class.getName());
  
  private TrackProcessor from;
  private TrackProcessor to;
  private TransitionDoneCallback callback;
  
  public interface TransitionDoneCallback {
    public void onFinished();
  }
  
  /**
   * Base transition.
   * @param from from track prosessor
   * @param to to track processor
   * @param callback transisition callback
   */
  public BaseTransition(TrackProcessor from, TrackProcessor to, TransitionDoneCallback callback) {
    log.info("Setup transition");
    this.from = from;
    this.to = to;
    this.callback = callback;
  }
  
  @Override
  public void run() {
    log.info("Started transition");
    this.begin(from, to);
    log.info("Finished transition");
    this.callback.onFinished();
  }
  
  public abstract void begin(TrackProcessor from, TrackProcessor to);
  
  public static void syncTempo(TrackProcessor master, TrackProcessor slave) {
    double percentage = master.getTrack().getBpm() / slave.getTrack().getBpm();
    slave.setTempo(percentage);
    log.info("Set tempo from " + slave.getTrack().getBpm() + " to "
        + master.getTrack().getBpm() + " (" + percentage * 100 + "%)");
  }
}
