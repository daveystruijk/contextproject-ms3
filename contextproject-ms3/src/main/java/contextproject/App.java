package contextproject;


import controllers.CLIController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hello world.
 *
 */
public class App {
  /**
   * Should start our application. No GUI for now just CLI.
   */

  static Logger log = LogManager.getLogger(App.class.getName());

  public static void main(String[] args) {
    CLIController control = new CLIController();
    control.run(); 
  }
}
