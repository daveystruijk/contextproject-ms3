package contextproject.models;

import java.io.Serializable;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LibraryProperty implements Serializable{

  /**
   * serial.
   */
  private static final long serialVersionUID = 1L;
  
  private final StringProperty name = new SimpleStringProperty();
  
  public LibraryProperty(String name) {
    this.name.set(name);
  }

  public final StringProperty nameProperty() {
    return this.name;
  }

  public final String getName() {
    return this.nameProperty().get();
  }

  public final void setName(String name) {
    this.nameProperty().set(name);
  }
}
