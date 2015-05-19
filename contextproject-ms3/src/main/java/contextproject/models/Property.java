package contextproject.models;

import java.io.Serializable;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Property implements Serializable {

  private static final long serialVersionUID = 3236190461451926648L;
  private final StringProperty name = new SimpleStringProperty();

  public Property(String name) {
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
