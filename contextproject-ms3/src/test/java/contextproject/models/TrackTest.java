package contextproject.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
  public void equalsTest() {
    URL resourceUrl = getClass().getResource("/beep.mp3");
    Path resourcePath;
    try {
      resourcePath = Paths.get(resourceUrl.toURI());
      Track track = new Track(resourcePath.toString());
      Track track2 = new Track(resourcePath.toString());
      assertTrue(track.equals(track2));
    } catch (URISyntaxException e) {
      fail("file wans't read correctly");
      e.printStackTrace();
    }
  }

  @Test
  public void notEqualsTest() {
    URL urlOne = getClass().getResource("/beep.mp3");
    URL urlTwo = getClass().getResource("/buz.mp3");
    Path resourceOne;
    Path resourceTwo;
    try {
      resourceOne = Paths.get(urlOne.toURI());
      resourceTwo = Paths.get(urlTwo.toURI());

      Track track = new Track(resourceOne.toString());
      Track track2 = new Track(resourceTwo.toString());

      assertFalse((track.equals(track2)));
    } catch (Exception e) {
      fail("");
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

  @Test
  public void setTest() {

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
      track.setAlbum("album");
      track.setArtist("artist");
      track.setBpm(60);
      track.setLength(100);
      track.setPath("path");
      track.setTitle("Title");

      BeatGrid bg = new BeatGrid((long) 100, 60, 5, 10, 10, 90, 10);
      Key newKey = new Key("10B");

      track.setBeatGrid(bg);
      track.setKey(newKey);
      assertTrue(track.equals(track));
      assertEquals(track.getAlbum(), "album");
      assertEquals(track.getTitle(), "Title");
      assertEquals(track.getArtist(), "artist");
      assertEquals(track.getLength(), new Long(100));
      assertEquals(track.getBeatGrid().getIntro(), new BeatRange(10, 10));
      assertEquals(track.getBeatGrid().getOutro(), new BeatRange(90, 10));
      assertEquals(track.getKey().getNormalizedKeyString(), "10B");
    } catch (URISyntaxException e) {
      fail("file wans't read correctly");
      e.printStackTrace();
    }
  }

}
