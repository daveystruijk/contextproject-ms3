package contextproject.controllers;

import contextproject.App;

import static org.junit.Assert.*;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class MenuBarControllerTest extends ApplicationTest {

  @Override
  public void start(Stage stage) throws Exception {
    App ap = new App();
    ap.setUp(stage);
  }

  @Test
  public void clickTest() {
    clickOn("#menu");
    clickOn("#menuItemImport");
    write("");
    press(KeyCode.ENTER);
  }
}
