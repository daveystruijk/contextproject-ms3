package contextproject.models;

import static org.junit.Assert.assertEquals;

import contextproject.models.Property;

import org.junit.Test;

public class PropertyTest {

  @Test
  public void test() {
    Property property = new Property("Test Property!");
    property.setName("More testing properties!");
    assertEquals(property.nameProperty().get(), property.getName());
  }

}
