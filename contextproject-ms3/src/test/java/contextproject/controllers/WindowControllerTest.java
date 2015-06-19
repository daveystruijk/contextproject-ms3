package contextproject.controllers;

import contextproject.App;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.stage.Stage;

public class WindowControllerTest extends ApplicationTest {

  @Test
  public void test() {
    WindowController window = new WindowController();
    assertNotEquals(window, null);
  }

  @Override
  public void start(Stage stage) throws Exception {
    // TODO Auto-generated method stub
    App ap = new App();
    ap.setUp(stage);
    
  }

  @Test
  public void controllerTest() {
    WindowController window = App.getController();
    assertNotEquals(window.getLibraryController(), null);
  }

  @Test
  public void playlistControllerTest() {
    WindowController window = App.getController();
    assertNotEquals(window.getPlaylistController(), null);
  }
  
  @Test
  public void playerControlControllerTest() {
    WindowController window = App.getController();
    assertNotEquals(window.getPlayerControlsController(),null);
  }
}
