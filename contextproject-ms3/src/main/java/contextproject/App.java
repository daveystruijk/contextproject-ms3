package contextproject;

import controller.Clicontroller;

/**
 * Hello world.
 *
 */
public class App {
  /**
   * Should start our application. No GUI for now just CLI.
   */
  public static void main(String[] args) {
    Clicontroller control = new Clicontroller();
    control.run(); 
  }
}
