package contextproject.audio;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.io.TarsosDSPAudioInputStream;
import be.tarsos.dsp.io.jvm.AudioPlayer;
import be.tarsos.dsp.io.jvm.JVMAudioInputStream;
import be.tarsos.transcoder.Attributes;
import be.tarsos.transcoder.Streamer;
import be.tarsos.transcoder.ffmpeg.EncoderException;

import contextproject.App;
import contextproject.helpers.StackTrace;
import contextproject.models.Track;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineUnavailableException;

public class AirhornProcessor {
  static Logger log = LogManager.getLogger(App.class.getName());

  private Track track;

  // Data
  private Attributes attributes;
  private AudioFormat format;

  // Audio Processing
  private AudioInputStream inputStream;
  private TarsosDSPAudioInputStream tarsosStream;
  private AudioPlayer audioPlayer;
  private AudioDispatcher dispatcher;

  /**
   * This class plays an airhorn sample.
   * 
   * @throws EncoderException
   *           encode error.
   * @throws LineUnavailableException
   *           line error.
   */
  public AirhornProcessor(Attributes attributes, Track track) {
    this.track = track;

    try {
      this.attributes = attributes;
      this.format = Streamer.streamAudioFormat(attributes);
    } catch (EncoderException e) {
      log.error("Airhorn processor encoder exception");
      log.trace(StackTrace.stackTrace(e));
    }
  }

  /**
   * Play airhorn.
   * 
   * @throws EncoderException
   *           encode error.
   * @throws LineUnavailableException
   *           line error.
   */
  public void play() throws EncoderException, LineUnavailableException {
    // Initialize the correct stream objects from file
    inputStream = Streamer.stream(track.getPath(), attributes);
    tarsosStream = new JVMAudioInputStream(inputStream);

    // Initialize audio processors
    audioPlayer = new AudioPlayer(format);
    dispatcher = new AudioDispatcher(tarsosStream, 2048, 0);
    dispatcher.addAudioProcessor(audioPlayer);
    Thread thread = new Thread(dispatcher);
    thread.start();
  }
}
