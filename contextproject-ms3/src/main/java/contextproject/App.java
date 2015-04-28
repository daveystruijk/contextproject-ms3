package contextproject;

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

/**
 * Hello world.
 *
 */
public class App {
  static Logger log = LogManager.getLogger(App.class.getName());

  public static void main(String[] args) throws EncoderException, LineUnavailableException {
    String source = "/Users/daveystruijk/Documents/FEESJE/House (Chill)" +
          "/Laura Veirs - July Flame (Gamper & Dadoni Remix).mp3";
    String source2 = "/Users/daveystruijk/Documents/FEESJE/House (Chill)" +
        "/Dolly Parton - Jolene (GAMPER & DADONI Remix).mp3";

    Attributes attributes = DefaultAttributes.WAV_PCM_S16LE_MONO_44KHZ.getAttributes();
    attributes.setSamplingRate(44100);

    // Stream 1
    AudioInputStream stream1 = Streamer.stream(source, attributes);
    TarsosDSPAudioInputStream audioStream1 = new JVMAudioInputStream(stream1);
    
    // Stream 2
    AudioInputStream stream2 = Streamer.stream(source2, attributes);
    TarsosDSPAudioInputStream audioStream2 = new JVMAudioInputStream(stream2);
    
    AudioFormat audioFormat = Streamer.streamAudioFormat(attributes);
    AudioPlayer player = new AudioPlayer(audioFormat);
    AudioPlayer player2 = new AudioPlayer(audioFormat);
    
    AudioDispatcher dispatcher = new AudioDispatcher(audioStream1, 2048, 0);
    dispatcher.addAudioProcessor(player);
    Thread t1 = new Thread(dispatcher);
    t1.start();
    
    AudioDispatcher dispatcher2 = new AudioDispatcher(audioStream2, 2048, 0);
    dispatcher2.addAudioProcessor(player2);
    Thread t2 = new Thread(dispatcher2);
    t2.start();

  }
}
