package contextproject.controllers;

import static org.junit.Assert.assertNotEquals;
import contextproject.App;
import contextproject.models.Library;

import org.junit.Test;

public class LibraryControllerTest {

  @Test
  public void constructTest() {
    LibraryController newLibrary = new LibraryController();
    assertNotEquals(newLibrary, null);
  }
}
