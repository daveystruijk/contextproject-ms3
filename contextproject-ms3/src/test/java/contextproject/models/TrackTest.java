package contextproject.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;



public class TrackTest {

  private static final double DELTA = 1e-15;

  @Test
  public void trackConstructorTest() {
    URL resourceUrl = getClass().getResource("/beep.mp3");
    Path resourcePath;
    try {
      resourcePath = Paths.get(resourceUrl.toURI());
      Track track = new Track(resourcePath.toString(), new Hashtable<String, String>());
      assertEquals(track.getAlbum(), "Beeps of the year");
      assertEquals(track.getTitle(), "Beep");
      assertEquals(track.getArtist(), "Flix");
      assertEquals(track.getLength(), new Long(575));
    } catch (URISyntaxException e) {
      fail("file wans't read correctly");
      e.printStackTrace();
    }

  }

  @Test
  public void trackConstructorWithAdditionalInfoTest() {

    Hashtable<String, String> info = new Hashtable<String, String>();
    info.put("bpm", "129.1");
    info.put("artist", "notID3");
    info.put("length", "100");

    URL resourceUrl = getClass().getResource("/beep.mp3");
    Path resourcePath;

    try {
      resourcePath = Paths.get(resourceUrl.toURI());
      Track track = new Track(resourcePath.toString(), info);
      assertEquals(track.getAlbum(), "Beeps of the year");
      assertEquals(track.getTitle(), "Beep");
      assertEquals(track.getArtist(), "notID3");
      assertEquals(track.getLength(), new Long(100));
      assertEquals(track.getBpm(), 129.1, DELTA);
    } catch (URISyntaxException e) {
      fail("file wans't read correctly");
      e.printStackTrace();
    }
  }

  @Test
  public void trackConstructorWithAdditionalInfoTest2() {

    Hashtable<String, String> info = new Hashtable<String, String>();
    info.put("startBeatIntro", "12");
    info.put("introBeatLength", "18");
    info.put("startBeatOutro", "100");
    info.put("outroBeatLength", "150");
    info.put("key", "11B");
    info.put("firstBeat", "12");

    URL resourceUrl = getClass().getResource("/beep.mp3");
    Path resourcePath;

    try {
      resourcePath = Paths.get(resourceUrl.toURI());
      Track track = new Track(resourcePath.toString(), info);
      assertEquals(track.getAlbum(), "Beeps of the year");
      assertEquals(track.getTitle(), "Beep");
      assertEquals(track.getArtist(), "Flix");
      assertEquals(track.getLength(), new Long(575));
      assertEquals(track.getBeatGrid().getIntro(), new BeatRange(12, 18));
      assertEquals(track.getBeatGrid().getOutro(), new BeatRange(100, 150));
      assertEquals(track.getKey().getNormalizedKeyString(), "11B");
    } catch (URISyntaxException e) {
      fail("file wans't read correctly");
      e.printStackTrace();
    }
  }

}
