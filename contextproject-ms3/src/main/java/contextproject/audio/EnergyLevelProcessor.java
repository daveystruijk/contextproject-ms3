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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineUnavailableException;

public class EnergyLevelProcessor implements AudioProcessor {
  static Logger log = LogManager.getLogger(EnergyLevelProcessor.class.getName());

  // Data
  private Attributes attributes;
  private ArrayList<Double> energyLevels;
  private float oneBarInSeconds;
  public double onset;

  // Audio Processing
  private AudioInputStream inputStream;
  private TarsosDSPAudioInputStream tarsosStream;
  private AudioDispatcher dispatcher;
  private SkipAudioProcessor skipProcessor;

  /**
   * Constructor of the processor.
   * 
   * @param attributes
   *          of the song.
   */
  public EnergyLevelProcessor(Attributes attributes) {
    this.attributes = attributes;
    energyLevels = new ArrayList<Double>();
  }

  /**
   * Detector of the energy.
   * 
   * @param track
   *          track to get energy from.
   * @throws EncoderException
   *           encoder exception.
   * @throws LineUnavailableException
   *           Line unavailable exception.
   */
  public void detect(Track track, double start) throws EncoderException, LineUnavailableException {
    inputStream = Streamer.stream(track.getPath(), attributes);
    tarsosStream = new JVMAudioInputStream(inputStream);

    float msPerBeat = (float) (60.0f / track.getBpm());
    oneBarInSeconds = msPerBeat * 4;

    dispatcher = new AudioDispatcher(tarsosStream, Math.round(44100 * oneBarInSeconds), 0);
    skipProcessor = new SkipAudioProcessor(start - oneBarInSeconds, false,
        new SkipAudioProcessorCallback() {

          @Override
          public void onFinished() {
            synchronized (skipProcessor) {
              skipProcessor.notify();
            }

          }
        });
    dispatcher.addAudioProcessor(skipProcessor);

    dispatcher.addAudioProcessor(this);
    dispatcher.run();

  }

  @Override
  public boolean process(AudioEvent audioEvent) {
    energyLevels.add(audioEvent.getRMS());
    return false;
  }

  @Override
  public void processingFinished() {
  }

  /**
   * Get the average energy of a song.
   * 
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

  /**
   * get Energy Levels.
   * 
   * @return ArrayList(Double) energyLevels
   */
  public ArrayList<Double> getEnergyLevels() {
    return energyLevels;
  }
}
