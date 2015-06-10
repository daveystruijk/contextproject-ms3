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

public class TrackTest {

  @Test
  public void trackConstructorTest() {
    URL resourceUrl = getClass().getResource("/beep.mp3");
    Path resourcePath;
    try {
      resourcePath = Paths.get(resourceUrl.toURI());
      Track track = new Track(resourcePath.toString());
      assertEquals(track.getAlbum(), "Beeps of the year");
      assertEquals(track.getTitle(), "Beep");
      assertEquals(track.getArtist(), "Flix");
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
  public void simpleNotEqualsTest() {
    Track track1 = new Track();
    Track track2 = new Track();
    assertFalse(track1.equals(track2));
  }

  @Test
  public void differentObjectEqualsTest() {
    Track track1 = new Track();
    MusicalKey key = new MusicalKey();
    assertFalse(track1.equals(key));
  }

  @Test
  public void setTest() {

    URL resourceUrl = getClass().getResource("/beep.mp3");
    Path resourcePath;

    try {
      resourcePath = Paths.get(resourceUrl.toURI());
      Track track = new Track(resourcePath.toString());
      track.setAlbum("album");
      track.setArtist("artist");
      track.setBpm(60);
      track.setLength(100);
      track.setPath("path");
      track.setTitle("Title");

      BeatGrid bg = new BeatGrid((long) 100, 60, 5, 10, 10, 90, 10);
      MusicalKey newKey = new MusicalKey("10B");

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

  @Test
  public void testBeatGridCreation() {
    URL resourceUrl = getClass().getResource("/beep.mp3");
    Path resourcePath;

    try {
      resourcePath = Paths.get(resourceUrl.toURI());
      Track track = new Track(resourcePath.toString());

      track.createBeatGrid(2, 5, 11, 13, 1);
      track.analyzeTrack();

    } catch (URISyntaxException e) {
      fail("file wans't read correctly");
      e.printStackTrace();
    }
  }

  @Test
  public void testException() {
    URL resourceUrl = getClass().getResource("/beep.mp3");
    Path resourcePath;

    try {
      resourcePath = Paths.get(resourceUrl.toURI());
      Track track = new Track(resourcePath.toString());
      track.setAlbum("album");
      track.setArtist("artist");
      track.setBpm(60);
      track.setLength(100);
      track.setPath("path");
      track.setTitle("Title");

      BeatGrid bg = new BeatGrid((long) 100, 60, 5, 10, 10, 90, 10);
      MusicalKey newKey = new MusicalKey("10B");

      track.setBeatGrid(bg);
      track.setKey(newKey);
      track.createBeatGrid(10, 1, 11, 2, 1);

    } catch (URISyntaxException e) {
      fail("file wans't read correctly");
      e.printStackTrace();
    }
  }

  @Test
  public void testEmptyString() {
    Track track = new Track("");
  }
}
