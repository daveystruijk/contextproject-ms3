package contextproject.audio;

import be.tarsos.transcoder.Attributes;
import be.tarsos.transcoder.DefaultAttributes;
import be.tarsos.transcoder.ffmpeg.EncoderException;

import contextproject.audio.TrackProcessor.PlayerState;
import contextproject.loaders.LibraryLoader;
import contextproject.models.Track;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sound.sampled.LineUnavailableException;

public class PlayerService {
  private static PlayerService instance = null;
  private static Logger log = LogManager.getLogger(LibraryLoader.class.getName());

  private TrackProcessor currentProcessor;
  private TrackProcessor nextProcessor;
  private AirhornProcessor airhornProcessor;

  private Attributes attributes;

  private Track currentTrack;
  private Track nextTrack;

  private static final int SAMPLE_RATE = 44100;

  protected PlayerService() throws EncoderException, LineUnavailableException {
    // Initialize standard attributes for audio playback
    attributes = DefaultAttributes.WAV_PCM_S16LE_MONO_44KHZ.getAttributes();
    attributes.setSamplingRate(SAMPLE_RATE);
    
    airhornProcessor = new AirhornProcessor(attributes, new Track(
        "/Users/daveystruijk/Documents/FEESJE/Samples/Random - airhorn1.mp3"));
  }
  
  /**
   * Loads the current track and plays it.
   * This method can be used when no track is playing currently,
   * and we want to start the mix with an initial track.
   */
  public void playCurrentTrack() {
    currentProcessor = new TrackProcessor(attributes);
    try {
      currentProcessor.load(currentTrack, 1.0, 1.0);
    } catch (EncoderException | LineUnavailableException e) {
      e.printStackTrace();
    }
    while (currentProcessor.getState() != PlayerState.READY) {
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    currentProcessor.play();
  }
  
  /**
   * Prepares the next track for playback.
   * Preparation means: Skipping the track to the point where a transition should happen.
   */
  public void prepareNextTrack() {
    nextProcessor = new TrackProcessor(attributes);
    try {
      nextProcessor.load(nextTrack, 1.0, 1.0);
    } catch (EncoderException | LineUnavailableException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Starts playing the next track if it's ready, and applies the specified transition.
   * If the track is not prepared for transition yet, this method will throw an exception.
   */
  public void transitionIntoNextTrack() {
    nextProcessor.play();
    
    // TODO: Add support for multiple transition types
    // Basically, this block should be an interchangeable method 
    /*for (int i = 0; i < 30; i++) {
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      currentProcessor.setGain(1.0 - (i / 30.0));
      nextProcessor.setGain((double) i / 30.0);
    }*/
    
    currentProcessor.unload();
    currentProcessor = nextProcessor;

    try {
      airhornProcessor.play();
    } catch (EncoderException | LineUnavailableException e) {
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
