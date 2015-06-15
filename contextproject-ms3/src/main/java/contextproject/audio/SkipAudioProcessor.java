package contextproject.audio;

import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;

import contextproject.helpers.StackTrace;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SkipAudioProcessor implements AudioProcessor {
  private static Logger log = LogManager.getLogger(SkipAudioProcessor.class.getName());

  private double secondsToSkip;
  private SkipAudioProcessorCallback callback;
  private boolean stopped = false;
  private boolean waitForNotify;

  public interface SkipAudioProcessorCallback {
    public void onFinished();
  }

  /**
   * Because of the way MP3 streaming works, we can't use dispatcher.skip(). Instead, this processor
   * silently goes through the stream and cancels the rest of the dispatcher chain until the desired
   * point is reached.
   * 
   */
  public SkipAudioProcessor(double secondsToSkip, boolean waitForNotify,
      SkipAudioProcessorCallback callback) {
    this.waitForNotify = waitForNotify;
    this.callback = callback;
    this.secondsToSkip = secondsToSkip;
  }

  @Override
  public boolean process(AudioEvent audioEvent) {
    if (stopped) {
      return true;
    }

    // If the desired point is reached,
    // stop the dispatcher thread from continuing any further.
    if (audioEvent.getTimeStamp() > secondsToSkip) {
      callback.onFinished();
      stopped = true;
      if (waitForNotify) {
        blockThread();
      }
    }

    return false;
  }

  /**
   * Block thread.
   */
  public void blockThread() {
    try {
      synchronized (this) {
        wait();
      }
    } catch (InterruptedException e) {
      log.error("Error occured in SkipAudioProcessor while calling blockThread");
      log.trace(StackTrace.stackTrace(e));
    }
  }

  @Override
  public void processingFinished() {

  }
}
