package contextproject.controllers;

import contextproject.App;
import contextproject.models.Library;
import contextproject.models.Playlist;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.stage.Stage;

public class LibraryControllerTest extends ApplicationTest {

  @Test
  public void constructorTest() {
    LibraryController newLibrary = new LibraryController();
    assertNotEquals(newLibrary, null);
  }

  @Override
  public void start(Stage stage) throws Exception {
    // TODO Auto-generated method stub
    App ap = new App();
    ap.setUp(stage);
  }

  @Test
  public void libSetTest() {
    WindowController window = App.getController();
    LibraryController libController = window.getLibraryController();
    libController.getTable();
    Playlist play = new Playlist();
    libController.setLibrary(play, "testLib");
    libController.getPlaylist("testLib");
    libController.update();
  }

  @Test
  public void libSetTestComplexArguments() {
    WindowController window = App.getController();
    LibraryController libController = window.getLibraryController();
    libController.setLibrary(new Library());
  }

}
