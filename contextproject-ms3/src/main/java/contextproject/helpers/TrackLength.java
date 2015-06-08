package contextproject.helpers;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public class TrackLength {
  /**
   * gets the duration of a audiofile.
   * @param abpath the path of the file.
   * @return the duration.
   * @throws UnsupportedAudioFileException when an audiofile is not supported.
   * @throws IOException when there is something wrong with the input of the file.
   */
  public static double getTrackDuration(String abpath) 
      throws UnsupportedAudioFileException, IOException {
    File file = new File(abpath);
    AudioInputStream input = AudioSystem.getAudioInputStream(file);
    AudioFormat format = input.getFormat();
    long fileLength = file.length();
    int frameSize = format.getFrameSize();
    float frameRate = format.getFrameRate();
    float duration = (fileLength / (frameSize * frameRate));
    System.out.println("track length should be: " + duration);
    return duration;
  }
}
