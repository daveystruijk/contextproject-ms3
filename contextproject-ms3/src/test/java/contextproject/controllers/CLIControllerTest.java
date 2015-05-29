package contextproject.controllers;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class CliControllerTest {

  @Test
  public void test() {
    CliController controller = new CliController();
    assertNotEquals(controller, null);
  }
}
