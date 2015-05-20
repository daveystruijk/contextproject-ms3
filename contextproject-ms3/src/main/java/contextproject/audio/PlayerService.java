package contextproject.audio;

import javax.sound.sampled.LineUnavailableException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import be.tarsos.transcoder.Attributes;
import be.tarsos.transcoder.DefaultAttributes;
import be.tarsos.transcoder.ffmpeg.EncoderException;

import contextproject.loaders.LibraryLoader;
import contextproject.models.Track;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;




import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineUnavailableException;


public class PlayerService {
  private static PlayerService instance = null;
  private static Logger log = LogManager.getLogger(LibraryLoader.class.getName());

  private TrackProcessor currentProcessor;
  private TrackProcessor nextProcessor;

  private Attributes attributes;

  public Track currentTrack;
  public Track nextTrack;

  final int SAMPLE_RATE = 44100;

  protected PlayerService() throws EncoderException, LineUnavailableException {
    // Initialize standard attributes for audio playback
    attributes = DefaultAttributes.WAV_PCM_S16LE_MONO_44KHZ.getAttributes();
    attributes.setSamplingRate(SAMPLE_RATE);
  }

  /**
   * Temporary method for playing tracks.
   */
  public void play() {
    currentProcessor = new TrackProcessor(attributes);
    currentProcessor.load(currentTrack);
    try {
      currentProcessor.play(1.0);
    } catch (EncoderException | LineUnavailableException e) {
      e.printStackTrace();
    }
  }

  public void transition() {
    nextProcessor = new TrackProcessor(attributes);
    nextProcessor.load(nextTrack);
    try {
      nextProcessor.play(0.0);
    } catch (EncoderException | LineUnavailableException e) {
      e.printStackTrace();
    }

    // TODO: Refactor into transition class
    for(int i = 0; i < 30; i++) {
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      currentProcessor.setGain(1.0 - (i / 30.0));
      nextProcessor.setGain((double) i / 30.0);
    }
    currentProcessor.unload();
    currentProcessor = nextProcessor;
  }
  /**
   * Stops the music.
   */
  public void exit() {
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
   *
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
