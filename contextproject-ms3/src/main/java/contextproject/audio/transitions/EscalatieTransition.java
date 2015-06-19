package contextproject.audio.transitions;

import be.tarsos.transcoder.ffmpeg.EncoderException;

import contextproject.audio.AirhornProcessor;
import contextproject.audio.TrackProcessor;
import contextproject.helpers.StackTrace;
import contextproject.models.Track;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;

public class EscalatieTransition extends BaseTransition {
  private static Logger log = LogManager.getLogger(EscalatieTransition.class.getName());

  
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
  public EscalatieTransition(TrackProcessor from, TrackProcessor to, 
      TransitionDoneCallback callback) {
    super(from, to, callback);
  }

  @Override
  public void begin(TrackProcessor from, TrackProcessor to) {
    try {
      new AirhornProcessor().play();
    } catch (EncoderException | LineUnavailableException e1) {
      e1.printStackTrace();
    }
    to.setGain(0.0);
    to.play();
    for (int i = 0; i < 8; i++) {
      from.setGain(1.0 - (i / 8.0));
      to.setGain((double) i / 8.0);
      try {
        double timePerBeat = 60.0 / from.getTrack().getBpm();
        Thread.sleep(Math.round(timePerBeat * 100));
      } catch (InterruptedException e) {
        log.error("Thread interrupted");
        log.trace(StackTrace.stackTrace(e));
      }
    }
  }
  
  @Override
  public void determineOutTime(Track track) {
    this.determineInTime(track);
    ArrayList<Double> outTransitionTimes = new ArrayList<Double>();
    for (int i = 0; i < track.getInTransitionTimes().size(); i++) {
      double time = track.getInTransitionTimes().get(i) 
          + (60.0 / track.getBpm()) * 32;
      outTransitionTimes.add(time);
    }
    track.setOutTransitionTimes(outTransitionTimes);
  }
  
  @Override
  public void determineInTime(Track track) {
    ArrayList<Double> inTransitionTimes = new ArrayList<Double>();
    double secondsPerFourBars = 60.0f / track.getBpm() * 16;
    int highest = 0;
    for (int i = 0; i < track.getDifferences().size(); i++) {
      if (track.getDifferences().get(i) > track.getDifferences().get(highest)) {
        highest = i;
      }
    }
    inTransitionTimes.add((highest + 2) * secondsPerFourBars);
    track.setInTransitionTimes(inTransitionTimes);
  }
}
