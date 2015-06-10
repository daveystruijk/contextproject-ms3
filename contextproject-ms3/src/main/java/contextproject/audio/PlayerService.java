package contextproject.audio;

import be.tarsos.transcoder.Attributes;
import be.tarsos.transcoder.DefaultAttributes;
import be.tarsos.transcoder.ffmpeg.EncoderException;

import contextproject.helpers.AudioProgress;
import contextproject.helpers.StackTrace;
import contextproject.loaders.LibraryLoader;
import contextproject.models.Track;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sound.sampled.LineUnavailableException;

public class PlayerService {
  private static PlayerService instance = null;
  private static Logger log = LogManager.getLogger(PlayerService.class.getName());

  private TrackProcessor currentProcessor;
  private TrackProcessor nextProcessor;
  private Thread currentAudioProgress;
  private Thread nextAudioProgress;

  private Attributes attributes;

  public Track currentTrack;
  public Track nextTrack;

  private static final int SAMPLE_RATE = 44100;

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
    currentAudioProgress = new Thread(new AudioProgress(currentProcessor));
    try {
      currentProcessor.play(1.0);
      currentAudioProgress.start();
    } catch (EncoderException | LineUnavailableException e) {
      log.error("Play() went wrong");
      log.trace(StackTrace.stackTrace(e));
    }
  }
  /**
   * This takes care of the Track transitions. It loads the next track or throws an exception when
   * needed
   */
  public void transition() {
    nextProcessor = new TrackProcessor(attributes);
    nextProcessor.load(nextTrack);
    nextAudioProgress = new Thread(new AudioProgress(nextProcessor));
    try {
      nextProcessor.play(0.0);
      currentAudioProgress.stop();
      currentAudioProgress = nextAudioProgress;
      currentAudioProgress.start();
    } catch (EncoderException | LineUnavailableException e) {
      log.error("transition went wrong");
      log.trace(StackTrace.stackTrace(e));
    }

    // TODO: Refactor into transition class
    for (int i = 0; i < 30; i++) {
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        log.error("Thread couldn't sleep");
        log.trace(StackTrace.stackTrace(e));
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
        log.error("Couldn't create instance");
        log.trace(StackTrace.stackTrace(e));
      }
    }
    return instance;
  }

  public void pause() {
    currentProcessor.pause();
  }

  public void resume() {
    currentProcessor.resume();
  }
}
