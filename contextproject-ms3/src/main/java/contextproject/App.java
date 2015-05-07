package contextproject;

import contextproject.controllers.CLIController;
import contextproject.controllers.PlayerControlsController;
import contextproject.controllers.WindowController;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.io.TarsosDSPAudioInputStream;
import be.tarsos.dsp.io.jvm.AudioPlayer;
import be.tarsos.dsp.io.jvm.JVMAudioInputStream;
import be.tarsos.transcoder.Attributes;
import be.tarsos.transcoder.DefaultAttributes;
import be.tarsos.transcoder.Streamer;
import be.tarsos.transcoder.ffmpeg.EncoderException;

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
    stage.setTitle("Cool demo!");
    stage.setScene(scene);
    stage.show();

  }

}
