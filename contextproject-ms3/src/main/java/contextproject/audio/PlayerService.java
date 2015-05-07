package contextproject.audio;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineUnavailableException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.io.TarsosDSPAudioInputStream;
import be.tarsos.dsp.io.jvm.AudioPlayer;
import be.tarsos.dsp.io.jvm.JVMAudioInputStream;
import be.tarsos.transcoder.Attributes;
import be.tarsos.transcoder.DefaultAttributes;
import be.tarsos.transcoder.Streamer;
import be.tarsos.transcoder.ffmpeg.EncoderException;
import contextproject.loaders.LibraryLoader;
import contextproject.models.Track;

public class PlayerService {
  private static PlayerService instance = null;
  private static Logger log = LogManager.getLogger(LibraryLoader.class.getName());
  
  private Attributes attributes;
  private AudioFormat audioFormat;
  private AudioInputStream currentStream;
  private AudioInputStream nextStream;
  private TarsosDSPAudioInputStream currentTarsosStream;
  private TarsosDSPAudioInputStream nextTarsosStream;
  private AudioPlayer currentPlayer;
  private AudioPlayer nextPlayer;
  private AudioDispatcher currentDispatcher;
  private AudioDispatcher nextDispatcher;
  private Thread currentThread;
  private Thread nextThread;
  
  public Track currentTrack;
  public Track nextTrack;

  protected PlayerService() throws EncoderException, LineUnavailableException {
    // Initialize standard attributes for audio playback
    attributes = DefaultAttributes.WAV_PCM_S16LE_MONO_44KHZ.getAttributes();
    attributes.setSamplingRate(44100);
    audioFormat = Streamer.streamAudioFormat(attributes);
    
    /*
    // Current track stream setup
    currentStream = Streamer.stream(currentTrack.getPath(), attributes);
    currentTarsosStream = new JVMAudioInputStream(currentStream);
    currentPlayer = new AudioPlayer(audioFormat);
    currentDispatcher = new AudioDispatcher(currentTarsosStream, 2048, 0);
    currentDispatcher.addAudioProcessor(currentPlayer);
    currentThread = new Thread(currentDispatcher);

    // Next track stream setup
    nextStream = Streamer.stream(nextTrack.getPath(), attributes);
    nextTarsosStream = new JVMAudioInputStream(nextStream);
    nextPlayer = new AudioPlayer(audioFormat);
    nextDispatcher = new AudioDispatcher(nextTarsosStream, 2048, 0);
    nextDispatcher.addAudioProcessor(nextPlayer);
    nextThread = new Thread(nextDispatcher);

    currentThread.start();
    nextThread.start();
    */
  }
  
  /**
   * Temporary method for playing tracks.
   */
  public void play() {
    try {
      currentStream = Streamer.stream(currentTrack.getPath(), attributes);
      currentTarsosStream = new JVMAudioInputStream(currentStream);
      currentPlayer = new AudioPlayer(audioFormat);
      currentDispatcher = new AudioDispatcher(currentTarsosStream, 2048, 0);
      currentDispatcher.addAudioProcessor(currentPlayer);
      currentThread = new Thread(currentDispatcher);
      currentThread.start();
    } catch (EncoderException e) {
      e.printStackTrace();
    } catch (LineUnavailableException e) {
      e.printStackTrace();
    }
  }

  public Track getCurrentTrack() {
    return currentTrack;
  }

  public void setCurrentTrack(Track currentTrack) {
    this.currentTrack = currentTrack;
  }

  public Track getNextTrack() {
    return nextTrack;
  }

  public void setNextTrack(Track nextTrack) {
    this.nextTrack = nextTrack;
  }

  /**
   * Returns the current PlayerService instance.
   * @return PlayerService
   */
  public static PlayerService getInstance() {
    if (instance == null) {
      try {
        instance = new PlayerService();
      } catch (EncoderException | LineUnavailableException e) {
        log.warn(e.getMessage());
      }
    }
    return instance;
  }
}