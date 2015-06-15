package contextproject.audio;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.io.jvm.JVMAudioInputStream;
import be.tarsos.dsp.onsets.OnsetHandler;
import be.tarsos.transcoder.Attributes;
import be.tarsos.transcoder.Streamer;
import be.tarsos.transcoder.ffmpeg.EncoderException;

import contextproject.App;
import contextproject.helpers.StackTrace;
import contextproject.models.Track;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sound.sampled.AudioInputStream;

/**
 * Detect the first onset of a song.
 *
 */
public class OnsetProcessor implements OnsetHandler {
  static Logger log = LogManager.getLogger(App.class.getName());

  private ComplexOnsetDetector onsetDetector;
  private AudioDispatcher dispatcher;
  private AudioInputStream stream = null;
  private Attributes attributes;

  private double threshold = 0.4;
  private int bufferSize = 512;
  private int overlap = 0;
  private double firstOnset = 0;

  public OnsetProcessor(Attributes attributes) {
    this.attributes = attributes;
  }

  /**
   * Detection of the onset. Uses the complex onset detector to compute.
   * 
   * @param track track.
   */
  public void detectOnset(Track track) {

    if (dispatcher != null) {
      dispatcher.stop();
    }

    try {
      stream = Streamer.stream(track.getPath(), attributes);
    } catch (EncoderException e) {
      log.error("Encoder exception in OnSetProcessor");
      log.trace(StackTrace.stackTrace(e));
    }

    final JVMAudioInputStream audioStream = new JVMAudioInputStream(stream);
    dispatcher = new AudioDispatcher(audioStream, bufferSize, overlap);
    onsetDetector = new ComplexOnsetDetector(bufferSize, threshold, 0.07, -60);
    onsetDetector.setHandler(this);
    dispatcher.addAudioProcessor(onsetDetector);
    dispatcher.run();
    firstOnset = onsetDetector.getFirstOnset();
  }

  @Override
  public void handleOnset(double time, double salience) {

  }

  /**
   * Getter of the first onset of a song.
   * 
   * @return onset of song.
   */
  public double getFirstOnset() {
    return firstOnset;
  }

}
