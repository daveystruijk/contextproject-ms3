package contextproject.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WindowControllerTest extends ApplicationTest {

  @Test
  public void test() {
    WindowController window = new WindowController();
    assertEquals(window, window);
  }

  @Override
  public void start(Stage stage) throws Exception {
    // TODO Auto-generated method stub
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/window.fxml"));

    Parent root = (Parent) loader.load();

    Scene scene = new Scene(root, 1200, 800);
    stage.setTitle("Window Test!");
    stage.setScene(scene);
    stage.show();
  }
}
