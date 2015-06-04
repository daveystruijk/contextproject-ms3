package contextproject.helpers;

import contextproject.models.Track;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;

import java.io.IOException;

public class KeyBpmFinder {

  private static Logger log = LogManager.getLogger(Track.class.getName());

  /**
   * Find Key and BPM
   * 
   * @param absolutePath
   *          mp3 absolutePath.
   */
  public void findKeyBpm(String absolutePath) {
    try {
      Process proc = Runtime.getRuntime().exec(
          "java -jar TrackAnalyzer.jar " + "\"" + absolutePath + "\"" + " -w -o testouput.txt");
      proc.waitFor();
      proc.destroy();
      MP3File mp3 = new MP3File(absolutePath);
      AbstractID3v2Tag tag = mp3.getID3v2Tag();
      tag.setField(FieldKey.KEY, tag.getFirst("TXXX"));
      mp3.setTag(tag);
      mp3.commit();
    } catch (IOException e) {
      log.error("There was a IO exception with file: " + absolutePath);
      log.trace(StackTrace.stackTrace(e));
    } catch (TagException e) {
      log.error("There was a Tag exception with file: " + absolutePath);
      log.trace(StackTrace.stackTrace(e));
    } catch (ReadOnlyFileException e) {
      log.error("There was a read only file exception with file: " + absolutePath);
      log.trace(StackTrace.stackTrace(e));
    } catch (InvalidAudioFrameException e) {
      log.error("There was a invallid audio frame exception with file: " + absolutePath);
      log.trace(StackTrace.stackTrace(e));
    } catch (CannotWriteException e) {
      log.error("There was a write exception with file: " + absolutePath);
      log.trace(StackTrace.stackTrace(e));
    } catch (InterruptedException e) {
      log.error("There was a interrupted process exception with file: " + absolutePath);
      log.trace(StackTrace.stackTrace(e));
    }

  }

}
