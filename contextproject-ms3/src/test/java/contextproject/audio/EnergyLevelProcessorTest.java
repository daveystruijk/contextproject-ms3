package contextproject.audio;

import contextproject.models.Key;
import contextproject.models.Track;

import be.tarsos.transcoder.Attributes;
import be.tarsos.transcoder.DefaultAttributes;
import be.tarsos.transcoder.ffmpeg.EncoderException;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import javax.sound.sampled.LineUnavailableException;

public class EnergyLevelProcessorTest {
  
  private EnergyLevelProcessor processor;
  private Track track;
  private static String directory;
  
  @Before
  public void initialize() {
    Attributes attributes = DefaultAttributes.WAV_PCM_S16LE_MONO_44KHZ.getAttributes();
    attributes.setSamplingRate(44100);
    processor = new EnergyLevelProcessor(attributes);
    directory = System.getProperty("user.dir").toString() + File.separator + "src" + File.separator
        + "test" + File.separator + "resources";
    track = new Track(directory + File.separator + "beep.mp3");
  }

//  @Test
//  public void getAvarageEnergyTest() throws EncoderException, LineUnavailableException {
//    processor.detect(track);
//    System.out.println(track.getEnergyLevels() + "jaja");
//  }

}
