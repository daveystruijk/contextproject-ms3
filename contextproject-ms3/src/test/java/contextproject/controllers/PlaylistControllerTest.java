package contextproject.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import contextproject.models.Track;
import contextproject.models.TrackProperty;

import org.junit.Before;
import org.junit.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;

public class PlaylistControllerTest {

  private PlaylistController playController;
  private Track track;

  /**
   * This test will test the PlayListController. We
   */
  @Before
  public void beginTest() {
    playController = new PlaylistController();
    URL resourceUrl = getClass().getResource("/beep.mp3");
    Path resourcePath;
    try {
      resourcePath = Paths.get(resourceUrl.toURI());
      track = new Track(resourcePath.toString());
    } catch (URISyntaxException e) {
      fail("file wans't read correctly");
      e.printStackTrace();
    }
  }

  @Test
  public void checkLibraryTest() {
    assertEquals(playController.getPlaylist(), null);
  }

  @Test
  public void propTest() {
    assertNotEquals(playController.setProp(track),
        new TrackProperty(track.getTitle(), track.getArtist(), track.getBpm(), track.getKey(),
            track));
  }

  @Test
  public void propTestNullTitle() {
    track.setTitle(null);
    assertNotEquals(playController.setProp(track),
        new TrackProperty(track.getTitle(), track.getArtist(), track.getBpm(), track.getKey(),
            track));
  }

  @Test
  public void propTestNullAlbum() {
    track.setArtist(null);
    assertNotEquals(playController.setProp(track),
        new TrackProperty(track.getTitle(), track.getArtist(), track.getBpm(), track.getKey(),
            track));
  }
}
