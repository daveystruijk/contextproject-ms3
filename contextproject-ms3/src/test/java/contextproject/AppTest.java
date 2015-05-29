package contextproject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import contextproject.App;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

  @Test
  public void appSetup() {
    App ap = new App();
    assertEquals(App.getController(), null);
    assertNotEquals(ap, null);
  }
}
