package contextproject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import contextproject.App;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * Unit test for simple App.
 */
public class AppTest extends ApplicationTest {

  @Test
  public void appSetup() {
    App ap = new App();
    assertNotEquals(App.getController(), null);
    assertNotEquals(ap, null);
  }

  @Override
  public void start(Stage stage) throws Exception {
    // TODO Auto-generated method stub
    App ap = new App();
    ap.start(stage);

  }
  
}
