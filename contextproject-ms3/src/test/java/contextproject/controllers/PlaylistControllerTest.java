package contextproject.controllers;

import static org.junit.Assert.assertEquals;

import contextproject.controllers.LibraryController;
import org.junit.Test;

public class PlaylistControllerTest {

  @Test
  public void beginTest() {
    PlaylistController playController = new PlaylistController();
    assertEquals(playController.getPlaylist(), null);
    
  }

}
