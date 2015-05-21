package contextproject.controllers;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class PlaylistControllerTest {

  @Test
  public void beginTest() {
    PlaylistController playController = new PlaylistController();
    PlaylistController dummyController = new PlaylistController();
    assertNotEquals(playController, dummyController);

  }

}
