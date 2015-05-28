package contextproject.controllers;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class CLIControllerTest {

  @Test
  public void test() {
    CLIController controller = new CLIController();
    assertNotEquals(controller, null);
  }
}
