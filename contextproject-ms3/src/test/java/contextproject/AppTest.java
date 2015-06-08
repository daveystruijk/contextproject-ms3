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

  @Test
  public void appTest() throws Exception {

    App ap = new App();
    assertNotEquals(ap, null);
    assertNotEquals(App.getController(), null);

  }

  @Override
  public void start(Stage stage) throws Exception {
    // TODO Auto-generated method stub
    App ap = new App();
    ap.start(stage);
  }

  @Test
  public void typeTest() {
    clickOn("#playButton");
  }
}
