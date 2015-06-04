package contextproject;

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

  }

  @Override
  public void start(Stage arg0) throws Exception {
    // TODO Auto-generated method stub

  }

}
