package contextproject.controllers;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class MenuBarControllerTest {

  @Test
  public void test() {
    MenuBarController menubar = new MenuBarController();
    assertNotEquals(menubar, null);
  }
}
