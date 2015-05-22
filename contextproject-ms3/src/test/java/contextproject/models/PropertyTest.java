package contextproject.models;

import static org.junit.Assert.assertEquals;

import contextproject.models.LibraryProperty;

import org.junit.Test;

public class PropertyTest {

  @Test
  public void test() {
    LibraryProperty property = new LibraryProperty("Test Property!");
    property.setName("More testing properties!");
    assertEquals(property.nameProperty().get(), property.getName());
  }

}
