package contextproject.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import contextproject.models.Playlist;
import contextproject.models.Track;

public class LibraryController {
  @FXML
  private TableView<Track> tableView;
  
  private Playlist library;
  
  /**
   * Update the table view.
   */
  public void update() {
    ObservableList<Track> items = FXCollections.observableArrayList(library);
    tableView.setItems(items);
  }
  
  public Playlist getLibrary() {
    return this.library;
  }
  
  public void setLibrary(Playlist playlist) {
    this.library = playlist;
    this.update();
  }
}
