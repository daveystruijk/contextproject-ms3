package contextproject.controllers;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class LibraryControllerTest {

  @Test
  public void test() {
    LibraryController newLibrary = new LibraryController();
    assertNotEquals(newLibrary, null);
  }

}
