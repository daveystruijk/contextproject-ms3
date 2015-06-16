package contextproject.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import contextproject.App;
import contextproject.models.Playlist;
import contextproject.models.Track;
import contextproject.models.TrackProperty;

import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.stage.Stage;

public class PlaylistControllerTest extends ApplicationTest {

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
  @Override
  public void start(Stage stage) throws Exception {
    // TODO Auto-generated method stub
    App ap = new App();
    ap.setUp(stage);
  }

  @Test
  public void checkLibraryTest() {
    assertEquals(playController.getPlaylist(), null);
  }

  @Test
  public void propTest() {
    assertNotEquals(playController.setProp(track),
        new TrackProperty(track.getTitle(), track.getArtist(), "" + track.getBpm(), track.getKey(),
            track, ""));
  }

  @Test
  public void propTestNullTitle() {
    track.setTitle(null);
    assertNotEquals(playController.setProp(track),
        new TrackProperty(track.getTitle(), track.getArtist(), "" + track.getBpm(), track.getKey(),
            track, ""));
  }

  @Test
  public void propTestNullAlbum() {
    track.setArtist(null);
    assertNotEquals(playController.setProp(track),
        new TrackProperty(track.getTitle(), track.getArtist(), "" + track.getBpm(), track.getKey(),
            track, ""));
  }

  @Test
  public void updateTest() {
    WindowController window = App.getController();
    PlaylistController playController = window.getPlaylistController();
    Playlist playlist = new Playlist();
    playlist.add(track);
    playController.setPlaylist(playlist);
  }

  @Test
  public void startTest() {
    WindowController window = App.getController();
    PlaylistController playController = window.getPlaylistController();
    playController.begin(window.getPlayerControlsController(), App.getScene());
    doubleClickOn(App.getScene().getWidth() / 4, App.getScene().getHeight() / 12);
  }
}
