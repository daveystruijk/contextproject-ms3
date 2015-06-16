package contextproject.audio;

import be.tarsos.transcoder.Attributes;
import be.tarsos.transcoder.DefaultAttributes;
import be.tarsos.transcoder.ffmpeg.EncoderException;

import contextproject.App;
import contextproject.audio.TrackProcessor.PlayerState;
import contextproject.audio.transitions.BaseTransition.TransitionDoneCallback;
import contextproject.audio.transitions.FadeInOutTransition;
import contextproject.controllers.PlayerControlsController;
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
  private PlayerControlsController pcc = App.getController().getPlayerControlsController();
  private Attributes attributes;

  private Track currentTrack;
  private Track nextTrack;

  private static final int SAMPLE_RATE = 44100;

  protected PlayerService() throws EncoderException, LineUnavailableException {
    // Initialize standard attributes for audio playback
    attributes = DefaultAttributes.WAV_PCM_S16LE_MONO_44KHZ.getAttributes();
    attributes.setSamplingRate(SAMPLE_RATE);
  }

  /**
   * Loads the current track and plays it. This method can be used when no track is playing
   * currently, and we want to start the mix with an initial track.
   */
  public void playCurrentTrack() {
    if (currentProcessor != null) {
      currentProcessor.unload();
    }
    currentProcessor = new TrackProcessor(attributes);
    pcc.setPcs(currentProcessor);
    //currentAudioProgress = new Thread(new AudioProgress(currentProcessor));

    try {
      currentProcessor.load(currentTrack, 1.0, 1.0);
    } catch (EncoderException | LineUnavailableException e) {
      log.error("Thread interrupted");
      log.trace(StackTrace.stackTrace(e));
    }

    // Wait for track processor to be ready
    while (currentProcessor.getState() != PlayerState.READY) {
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        log.error("Thread interrupted");
        log.trace(StackTrace.stackTrace(e));
      }
    }

    currentProcessor.play();
    //currentAudioProgress.start();
  }

  /**
   * Prepares the next track for playback. Preparation means: Skipping the track to the point where
   * a transition should happen.
   */
  public void prepareNextTrack(Track newTrack) {
    this.nextTrack = newTrack;
    nextProcessor = new TrackProcessor(attributes);
   // nextAudioProgress = new Thread(new AudioProgress(nextProcessor));
    try {
      nextProcessor.load(nextTrack, 1.0, 1.0);
    } catch (EncoderException | LineUnavailableException e) {
      log.error("Thread interrupted");
      log.trace(StackTrace.stackTrace(e));
    }
  }

  /**
   * Starts playing the next track if it's ready, and applies the specified transition. If the track
   * is not prepared for transition yet, this method will throw an exception.
   */
  public void setupTransition(TransitionDoneCallback callback) {
    double transitionTime = (60.0 / nextTrack.getBpm()) * 128;
    currentProcessor.setupTransition(transitionTime, new FadeInOutTransition(currentProcessor,
        nextProcessor, new TransitionDoneCallback() {

          @Override
          public void onFinished() {
            currentProcessor.unload();
            currentProcessor = nextProcessor;
//            currentAudioProgress.stop();
//            currentAudioProgress = nextAudioProgress;
//            currentAudioProgress.start();
            callback.onFinished();
          }
        }));
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
        log.error("Error occured in playerservice while calling getting Instance");
        log.trace(StackTrace.stackTrace(e));
      }
    }
    return instance;
  }
}
