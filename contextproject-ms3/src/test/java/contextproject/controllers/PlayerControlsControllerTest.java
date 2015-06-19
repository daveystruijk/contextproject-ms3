package contextproject.controllers;

import be.tarsos.transcoder.Attributes;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import contextproject.App;
import contextproject.audio.TrackProcessor;
import contextproject.models.Track;

import org.junit.Test;
import org.mockito.Mockito;
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
    } catch (URISyntaxException e) {
      fail("file wans't read correctly");
      e.printStackTrace();
    }

    PlayerControlsController controller = new PlayerControlsController();
    controller.currentTrack = new TextField();
    controller.nextTrack = new TextField();
    controller.update(track.getTitle(), track.getTitle());
    controller.buttonbox1 = new HBox();
    controller.buttonbox2 = new HBox();
    controller.curbox = new HBox();
    controller.nextbox = new HBox();
    controller.musicBar = new ProgressBar();
    controller.playButton = new Button();
    controller.initialize(App.getScene().getWidth());
    controller.setProgress(0.45);
    controller.playButton.setId("playButton");
    controller.toggleButton();
    controller.toggleButton();
    assertEquals(controller.playButton.getId(), "pauseButton");
    controller.playButton.setId("playButton");
    controller.togglePlayPause();
  }

  @Test
  public void tpTest() {
    PlayerControlsController controller = new PlayerControlsController();
    Attributes attr = Mockito.mock(Attributes.class);
    TrackProcessor track = new TrackProcessor(attr);
    controller.setPcs(track);
    controller.getPcs();
    
  }

}
