package contextproject;


import contextproject.controllers.CLIController;
import contextproject.controllers.WindowController;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hello world.
 *
 */
public class App extends Application {
  static Logger log = LogManager.getLogger(App.class.getName());
/**
 * This will start our app with a graphical user interface.
 */
  public static void main(String[] args) {
    boolean gui = true;
    if (gui == true) {
      launch(args);
    } else {
      CLIController control = new CLIController();
      control.run();
    }
  }

  @Override
  public void start(Stage stage) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/window.fxml"));
    Parent root = loader.load();
    WindowController controller = (WindowController) loader.getController();
    controller.doSomething();
    Scene scene = new Scene(root, 1200, 800);
    stage.setTitle("Window");
    stage.setScene(scene);
    stage.show();
    
  }

}
