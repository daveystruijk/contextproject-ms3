package contextproject.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PlayerControlsControllerTest extends ApplicationTest {

  @Test
  public void test() {
    PlayerControlsController controller = new PlayerControlsController();
    assertEquals(controller, controller);

  }

  @Override
  public void start(Stage stage) throws Exception {
    // TODO Auto-generated method stub
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/player_controls.fxml"));

    Parent root = (Parent) loader.load();

    Scene scene = new Scene(root, 1200, 800);
    stage.setTitle("Test for Pressing Buttons");
    stage.setScene(scene);
    stage.show();
  }

  @Test
  public void clickButtonTest() {
    clickOn("#playButton");
    clickOn("#pauseButton");
  }

}
