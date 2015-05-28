package contextproject;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javafx.stage.Stage;

/**
 * Unit test for simple App.
 */
public class AppTest {

  private Stage stage = Mockito.mock(Stage.class);
  private App dummyApp = Mockito.mock(App.class);

  @Before
  public void setUp() throws Exception {

    dummyApp.start(stage);
  }

  @Test
  public void appTest() throws Exception {

    Mockito.verify(dummyApp).start(stage);

  }

}
