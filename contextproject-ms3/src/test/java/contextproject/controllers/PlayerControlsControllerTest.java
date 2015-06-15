package contextproject.controllers;

import static org.junit.Assert.assertEquals;

import contextproject.App;
import contextproject.models.Track;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PlayerControlsControllerTest extends ApplicationTest {

  private Scene scene2;

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
    PlayerControlsController controller = new PlayerControlsController();
    Track track = new Track();
    track.setTitle("Just to be sure");
    controller.currentTrack = new TextField();
    controller.nextTrack = new TextField();
    controller.update(track, "testing");
    controller.buttonbox1 = new HBox();
    controller.buttonbox2 = new HBox();
    controller.curbox = new HBox();
    controller.nextbox = new HBox();
    controller.musicBar = new ProgressBar();
    controller.pauseButton = new Button();
    controller.initialize(App.getScene().getWidth());
    controller.setProgres(0.45);
    controller.pauseButton.setId("pauseButton");
    controller.pauseButton.fire();
    controller.pauseButton.fire();
    assertEquals(controller.pauseButton.getId(), "pauseButton");
  }

}
