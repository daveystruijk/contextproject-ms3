package contextproject.controllers;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class CliTest {

  @Test
  public void test() {
    CliController cli = new CliController();
    assertNotEquals(cli, null);
  }

}
