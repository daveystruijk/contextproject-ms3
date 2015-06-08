package contextproject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/window.fxml"));

    Parent root = (Parent) loader.load();

    Scene scene = new Scene(root, 1200, 800);
    stage.setTitle("Window Test!");
    stage.setScene(scene);
    stage.show();

  }

  @Before
  public void setUp() {
    
  }
  
  @Test
  public void typeTest() {
    clickOn("#playButton");
  }
}
