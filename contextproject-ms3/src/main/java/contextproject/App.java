package contextproject;

import contextproject.controllers.CLIController;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;

import javafx.stage.Stage;

/**
 * Hello world.
 *
 */
public class App extends Application {
  /**
   * Should start our application. No GUI for now just CLI.
   */

  static Logger log = LogManager.getLogger(App.class.getName());

  @Override
  public void start(Stage initStage) throws Exception {
    // TODO Auto-generated method stub
    Button btn = new Button();
    btn.setText("Do somehthing cool");
    btn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        System.out.println("Get funky!");
      }
    });

    StackPane root = new StackPane();
    root.getChildren().add(btn);

    Scene scene = new Scene(root, 300, 250);

    initStage.setTitle("Hello World!");
    initStage.setScene(scene);
    initStage.show();

  }

  public static void main(String[] args) {
    launch(args);
    CLIController control = new CLIController();
    control.run();
  }

}
