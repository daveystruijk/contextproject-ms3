package contextproject.audio;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.io.jvm.JVMAudioInputStream;
import be.tarsos.dsp.onsets.ComplexOnsetDetector;
import be.tarsos.dsp.onsets.OnsetHandler;
import be.tarsos.transcoder.Attributes;
import be.tarsos.transcoder.Streamer;
import be.tarsos.transcoder.ffmpeg.EncoderException;

import contextproject.models.Track;

import java.lang.reflect.Field;

import javax.sound.sampled.AudioInputStream;

/**
 * Detect the onset.
 *
 */
public class OnsetProcessor implements OnsetHandler {

  private ComplexOnsetDetector onsetDetector;
  private AudioDispatcher dispatcher;
  private AudioInputStream stream = null;
  private Attributes attributes;

  private double threshold = 0.4;
  private int bufferSize = 512;
  private int overlap = 0;

  public OnsetProcessor(Attributes attributes) {
    this.attributes = attributes;
  }

  public void dingen(Track track) {

    if (dispatcher != null) {
      dispatcher.stop();
    }

    try {
      stream = Streamer.stream(track.getPath(), attributes);
    } catch (EncoderException e) {
      e.printStackTrace();
    }
    final JVMAudioInputStream audioStream = new JVMAudioInputStream(stream);
    dispatcher = new AudioDispatcher(audioStream, bufferSize, overlap);
    onsetDetector = new ComplexOnsetDetector(bufferSize, threshold, 0.07, -60);
    onsetDetector.setHandler(this);
    dispatcher.run();
    
    try {
      Field field = ComplexOnsetDetector.class.getDeclaredField("lastOnset");
      field.setAccessible(true);
      Object value = field.get(onsetDetector);
      System.out.println(value.toString());
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }

  @Override
  public void handleOnset(double time, double salience) {
    System.out.println("blavla");

  }

}
