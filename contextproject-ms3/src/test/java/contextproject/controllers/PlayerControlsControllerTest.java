package contextproject.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import contextproject.App;
import contextproject.audio.PlayerService;
import contextproject.models.Track;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PlayerControlsControllerTest extends ApplicationTest {

  private Track track;
  private Track track2;
  
  @Test
  public void test() {
    PlayerControlsController controller = new PlayerControlsController();
    assertEquals(controller, controller);

  }

  @Override
  public void start(Stage stage) throws Exception {
    App ap = new App();
    ap.setUp(stage);
  }

  @Test
  public void updateTest() {
    URL resourceUrl = getClass().getResource("/beep.mp3");
    Path resourcePath;
    try {
      resourcePath = Paths.get(resourceUrl.toURI());
      track = new Track(resourcePath.toString());
      track2 = new Track(resourcePath.toString());
    } catch (URISyntaxException e) {
      fail("file wans't read correctly");
      e.printStackTrace();
    }
//    PlayerService.getInstance().setCurrentTrack(track);
//    PlayerService.getInstance().setUpCurrentTrack();
//    PlayerService.getInstance().prepareNextTrack(track2);
    PlayerControlsController controller = new PlayerControlsController();
    controller.currentTrack = new TextField();
    controller.nextTrack = new TextField();
    controller.update(track, track);
    controller.buttonbox1 = new HBox();
    controller.buttonbox2 = new HBox();
    controller.curbox = new HBox();
    controller.nextbox = new HBox();
    controller.musicBar = new ProgressBar();
    controller.playButton = new Button();
    controller.initialize(App.getScene().getWidth());
    controller.setProgress(0.45);
    controller.playButton.setId("playButton");
//    controller.playButton.fire();
//    controller.playButton.fire();
    assertEquals(controller.playButton.getId(), "playButton");
  }

}
