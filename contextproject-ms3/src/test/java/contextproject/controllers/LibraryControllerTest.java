package contextproject.controllers;

import static org.junit.Assert.assertEquals;

import contextproject.controllers.LibraryController;
import org.junit.Test;

public class LibraryControllerTest {

  @Test
  public void beginTest() {
    LibraryController libController = new LibraryController();
    assertEquals(libController.getLibrary(), null);
    
  }

}
