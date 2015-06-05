package contextproject.audio;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.io.TarsosDSPAudioInputStream;
import be.tarsos.dsp.io.jvm.JVMAudioInputStream;
import be.tarsos.transcoder.Attributes;
import be.tarsos.transcoder.Streamer;
import be.tarsos.transcoder.ffmpeg.EncoderException;

import contextproject.audio.SkipAudioProcessor.SkipAudioProcessorCallback;
import contextproject.models.Track;

import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineUnavailableException;

public class EnergyLevelProcessor implements AudioProcessor {
  // Data
  private Attributes attributes;
  private AudioFormat format;
  private ArrayList<Double> energyLevels;
  private float oneBeatInSeconds;
  public int counter = 0;

  // Audio Processing
  private AudioInputStream inputStream;
  private TarsosDSPAudioInputStream tarsosStream;
  private AudioDispatcher dispatcher;
  private SkipAudioProcessor skipProcessor;
  
  /**
   * Constructor of the processor.
   * @param attributes of the song.
   */
  public EnergyLevelProcessor(Attributes attributes) {
    try {
      this.attributes = attributes;
      this.format = Streamer.streamAudioFormat(attributes);
    } catch (EncoderException e) {
      e.printStackTrace();
    }
    energyLevels = new ArrayList<Double>();
  }
  
  /**
   * Detector of the energy.
   * @param track track to get energy from.
   * @throws EncoderException
   * @throws LineUnavailableException
   */
  public void detect(Track track, double start) throws EncoderException, LineUnavailableException {
    inputStream = Streamer.stream(track.getPath(), attributes);
    tarsosStream = new JVMAudioInputStream(inputStream);
    
    float msPerBeat = (float) (60.0f / track.getBpm());
    oneBeatInSeconds = msPerBeat;
    
    System.out.println(oneBeatInSeconds);
    
    dispatcher = new AudioDispatcher(tarsosStream, Math.round(44100 * oneBeatInSeconds), 0);
//    final EnergyLevelProcessor elp = this;
    skipProcessor = new SkipAudioProcessor(start, new SkipAudioProcessorCallback() {
      
      @Override
      public void onFinished() {
        skipProcessor.stop();
        
      }
    });
    dispatcher.addAudioProcessor(skipProcessor);
    dispatcher.addAudioProcessor(this);
    dispatcher.run();
    
  }

  @Override
  public boolean process(AudioEvent audioEvent) {
    counter ++;
    System.out.println(audioEvent.getRMS() + " at time " + oneBeatInSeconds*counter);
    energyLevels.add(audioEvent.getRMS());
    return false;
  }

  @Override
  public void processingFinished() {
    energyLevels = new ArrayList<Double>();
  }
  
  /**
   * Get the average energy of a song.
   * @return average energy
   */
  public double getAverageEnergy() {
    double sum = 0;
    double counter = 0;
    for (double energy : energyLevels) {
      sum += energy;
      counter++;
    }
    return sum / counter;
  }
  
  public void test() {
 //   Event event = new Event();
  }
}
