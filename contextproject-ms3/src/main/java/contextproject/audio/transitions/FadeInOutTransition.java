package contextproject.audio.transitions;

import contextproject.audio.TrackProcessor;
import contextproject.helpers.StackTrace;
import contextproject.models.Track;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class FadeInOutTransition extends BaseTransition {
  private static Logger log = LogManager.getLogger(FadeInOutTransition.class.getName());

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
        log.error("Thread interrupted");
        log.trace(StackTrace.stackTrace(e));
      }
    }
  }

  @Override
  public void determineOutTime(Track track) {
    ArrayList<Double> outTransitionTimes = new ArrayList<Double>();
    double min = -(track.getAverageEnergy() * 0.35);
    double secondsPerFourBars = 60.0f / track.getBpm() * 16;
    int index = 0;
    for (index = 0; index < track.getDifferences().size(); index++) {
      if (track.getDifferences().get(index) < min
          && ((index + 2) * secondsPerFourBars) > (0.2 * track.getDuration())
          && ((index + 2) * secondsPerFourBars) < (0.8 * track.getDuration())) {
        outTransitionTimes.add((index + 2) * secondsPerFourBars);
      }
    }
    if (outTransitionTimes.isEmpty() && index > 5) {
      outTransitionTimes.add((index - 5) * secondsPerFourBars);
    } else if (outTransitionTimes.isEmpty()) {
      outTransitionTimes.add(index * secondsPerFourBars);
    }
    track.setOutTransitionTimes(outTransitionTimes);
  }

  @Override
  public void determineInTime(Track track) {
    ArrayList<Double> inTransitionTimes = new ArrayList<Double>();
    double min = -(track.getAverageEnergy() * 0.35);
    double secondsPerFourBars = 60.0f / track.getBpm() * 16;
    for (int i = 0; i < track.getDifferences().size(); i++) {
      if (track.getDifferences().get(i) < min
          && (i + 2) * secondsPerFourBars < (0.5 * track.getDuration())) {
        inTransitionTimes.add((i + 2) * secondsPerFourBars);
      }
    }
    track.setInTransitionTimes(inTransitionTimes);
  }
}
