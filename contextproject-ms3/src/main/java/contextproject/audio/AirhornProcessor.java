package contextproject.audio;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.GainProcessor;
import be.tarsos.dsp.io.TarsosDSPAudioInputStream;
import be.tarsos.dsp.io.jvm.AudioPlayer;
import be.tarsos.dsp.io.jvm.JVMAudioInputStream;
import be.tarsos.transcoder.Attributes;
import be.tarsos.transcoder.DefaultAttributes;
import be.tarsos.transcoder.Streamer;
import be.tarsos.transcoder.ffmpeg.EncoderException;

import contextproject.helpers.StackTrace;
import contextproject.models.Track;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineUnavailableException;

public class AirhornProcessor {
  static Logger log = LogManager.getLogger(AirhornProcessor.class.getName());

  private Track track;

  // Data
  private Attributes attributes;
  private AudioFormat format;

  // Audio Processing
  private AudioInputStream inputStream;
  private TarsosDSPAudioInputStream tarsosStream;
  private AudioPlayer audioPlayer;
  private AudioDispatcher dispatcher;

  private static Track airhornTrack = null;
  
  /**
   * get the airhorn for transitions.
   * @return the airhorn as track.
   */
  public static Track getAirhornTrack() {
    if (airhornTrack == null) {
      airhornTrack = new Track(AirhornProcessor.class.getClass()
          .getResource("/samples/airhorn.mp3").getPath());
    }
    return airhornTrack;
  }
  
  /**
   * This class plays an airhorn sample.
   * 
   * @throws EncoderException
   *           encode error.
   * @throws LineUnavailableException
   *           line error.
   */
  public AirhornProcessor() {
    attributes = DefaultAttributes.WAV_PCM_S16LE_MONO_44KHZ.getAttributes();
    attributes.setSamplingRate(44100);
    this.track = getAirhornTrack();
    try {
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
    GainProcessor gainProcessor = new GainProcessor(0.8f);
    dispatcher = new AudioDispatcher(tarsosStream, 2048, 0);
    dispatcher.addAudioProcessor(audioPlayer);
    dispatcher.addAudioProcessor(gainProcessor);
    Thread thread = new Thread(dispatcher);
    thread.start();
  }
}
