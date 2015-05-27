package contextproject;

import static org.junit.Assert.assertEquals;

import contextproject.App;
import contextproject.models.Library;
import contextproject.models.Playlist;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Unit test for simple App.
 */
public class AppTest {

  @Test
  public void appSetup() {
    App ap = new App();
    assertEquals(App.getController(), null);
  }
}
