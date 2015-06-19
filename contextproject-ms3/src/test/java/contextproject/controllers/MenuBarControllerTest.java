package contextproject.controllers;

import contextproject.App;

import org.testfx.framework.junit.ApplicationTest;

import javafx.stage.Stage;

public class MenuBarControllerTest extends ApplicationTest {

  @Override
  public void start(Stage stage) throws Exception {
    App ap = new App();
    ap.setUp(stage);
  }
}
