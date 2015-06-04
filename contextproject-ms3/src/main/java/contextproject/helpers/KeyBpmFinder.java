package contextproject.helpers;

import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;

import java.io.IOException;

public class KeyBpmFinder {

  /**
   * Find Key and BPM
   * @param absolutePath mp3 absolutePath.
   */
  public void findKeyBpm(String absolutePath) {
    String absolutePath2 = "\"" + absolutePath + "\"";
    try {
      Process proc = Runtime.getRuntime().exec(
          "java -jar TrackAnalyzer.jar " + absolutePath2 + " -w -o testouput.txt");
      while(proc.isAlive()){
        
      }
      proc.destroy();
      MP3File mp3 = new MP3File(absolutePath);
      AbstractID3v2Tag tag = mp3.getID3v2Tag();
      tag.setField(FieldKey.KEY, tag.getFirst("TXXX"));
      mp3.setTag(tag);
      mp3.commit();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (TagException e) {
      e.printStackTrace();
    } catch (ReadOnlyFileException e) {
      e.printStackTrace();
    } catch (InvalidAudioFrameException e) {
      e.printStackTrace();
    } catch (CannotWriteException e) {
      e.printStackTrace();
    }

  }

}
