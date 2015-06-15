package contextproject.controllers;

import static org.junit.Assert.assertEquals;

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
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/window.fxml"));

    Parent root = (Parent) loader.load();
    Scene scene = new Scene(root, 1200, 800);
    stage.setTitle("Test for Pressing Buttons");
    stage.setScene(scene);
    scene2 = scene;
    stage.show();
  }

  @Test
  public void updateTest() {
    PlayerControlsController controller = new PlayerControlsController();
    Track track = new Track();
    track.setTitle("Just to be sure");
    controller.currentTrack = new TextField();
    controller.nextTrack = new TextField();
    controller.update(track, track);
    controller.buttonbox1 = new HBox();
    controller.buttonbox2 = new HBox();
    controller.curbox = new HBox();
    controller.nextbox = new HBox();
    controller.musicBar = new ProgressBar();
    controller.pauseButton = new Button();
    controller.initialize(scene2.getWidth());
    controller.pauseButton.setId("pauseButton");
    clickOn("#pauseButton");

  }

}
