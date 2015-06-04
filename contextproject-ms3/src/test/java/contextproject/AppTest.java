package contextproject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

  @Test
  public void appTest() throws Exception {

    App ap = new App();
    assertNotEquals(ap, null);
    assertEquals(App.getController(), null);

  }
}
