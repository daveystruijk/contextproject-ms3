package contextproject.audio;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.GainProcessor;
import be.tarsos.dsp.WaveformSimilarityBasedOverlapAdd;
import be.tarsos.dsp.io.TarsosDSPAudioInputStream;
import be.tarsos.dsp.io.jvm.AudioPlayer;
import be.tarsos.dsp.io.jvm.JVMAudioInputStream;
import be.tarsos.transcoder.Attributes;
import be.tarsos.transcoder.Streamer;
import be.tarsos.transcoder.ffmpeg.EncoderException;

import contextproject.models.Track;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineUnavailableException;

public class EnergyLevelProcessor implements AudioProcessor {
  // Data
  private Attributes attributes;
  private AudioFormat format;

  // Audio Processing
  private AudioInputStream inputStream;
  private TarsosDSPAudioInputStream tarsosStream;
  private AudioDispatcher dispatcher;
  
  public EnergyLevelProcessor(Attributes attributes) {
    try {
      this.attributes = attributes;
      this.format = Streamer.streamAudioFormat(attributes);
    } catch (EncoderException e) {
      e.printStackTrace();
    }
  }
  
  public void detect(Track track) throws EncoderException, LineUnavailableException {
    inputStream = Streamer.stream(track.getPath(), attributes);
    tarsosStream = new JVMAudioInputStream(inputStream);
    
    float msPerBeat = (float) (60.0f / track.getBpm());
    float fourBarsInSeconds = msPerBeat * 16;
    System.out.println(fourBarsInSeconds);
    
    dispatcher = new AudioDispatcher(tarsosStream, Math.round(44100 * fourBarsInSeconds), 0);
  
    dispatcher.addAudioProcessor(this);
    dispatcher.run();
  }

  @Override
  public boolean process(AudioEvent audioEvent) {
    System.out.println(audioEvent.getRMS());
    return false;
  }

  @Override
  public void processingFinished() {
    System.out.println("Finished!");
  }
}
