package contextproject;

import controllers.CLIController;

/**
 * Hello world.
 *
 */
public class App {
  /**
   * Should start our application. No GUI for now just CLI.
   */
  public static void main(String[] args) {
    CLIController control = new CLIController();
    control.run(); 
  }
}
