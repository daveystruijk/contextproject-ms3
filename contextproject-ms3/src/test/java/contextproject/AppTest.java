package contextproject;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.stage.Stage;

/**
 * Unit test for simple App.
 */
public class AppTest extends ApplicationTest {

  @Override
  public void start(Stage stage) throws Exception {
    App ap = new App();
    ap.setUp(stage);
  }

  @Test
  public void appTest() {
    App ap = new App();
    AppConfig apConfig = new AppConfig();
    assertNotEquals(ap, null);
    assertNotEquals(App.getController(), null);
    assertNotEquals(App.getScene(), null);
    assertNotEquals(apConfig, null);
  }

  @Test
  public void stageTest() {
    Stage stage = App.getStage();
    assertEquals(stage, null);
  }
}
