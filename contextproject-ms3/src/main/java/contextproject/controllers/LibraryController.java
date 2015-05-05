package contextproject.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class LibraryController {
  @FXML private ListView<String> listView;
  
  public void setItems() {
    ObservableList<String> items = FXCollections.observableArrayList (
        "One", "Two", "Three", "Nice!");
    listView.setItems(items);
  }
}
