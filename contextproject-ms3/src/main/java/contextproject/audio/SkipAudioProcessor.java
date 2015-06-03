package contextproject.audio;

import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;

import contextproject.models.Track;

public class SkipAudioProcessor implements AudioProcessor {
  
  private double secondsToSkip;
  private SkipAudioProcessorCallback callback;
  private boolean hasFinished;
  private boolean stopped = false;

  public interface SkipAudioProcessorCallback {
    public void onFinished();
  }
  
  /**
   * Because of the way MP3 streaming works, we can't use dispatcher.skip().
   * Instead, this processor silently goes through the stream and cancels
   * the rest of the dispatcher chain until the desired point is reached.
   * 
   */
  public SkipAudioProcessor(Track track, SkipAudioProcessorCallback callback) {
    // TODO: get starting point based on track beatgrid
    this.callback = callback;
    this.secondsToSkip = (60.0 / track.getBpm()) * 64;
  }
  
  @Override
  public boolean process(AudioEvent audioEvent) {
    if (stopped) {
      return true;
    }
    
    if (audioEvent.getTimeStamp() > secondsToSkip) {
      // Stop the stream from continuing any further
      audioEvent.setBytesProcessed(0);
      // Notify the calling class if we haven't yet
      if (!hasFinished) {
        callback.onFinished();
        hasFinished = true;
      }
      return false;
    } else {
      return false;
    }
  }
  
  public void stop() {
    this.stopped = true;
  }

  @Override
  public void processingFinished() {
    
  }
}
