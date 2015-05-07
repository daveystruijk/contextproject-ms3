package contextproject.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import contextproject.audio.PlayerService;
import contextproject.models.Playlist;
import contextproject.models.Track;

public class LibraryController {
  @FXML
  private TableView<Track> tableView;

  private Playlist library;

  /**
   * Setup events on the tableView items.
   */
  public void initialize() {
    tableView.setOnMousePressed(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
          PlayerService.getInstance().setCurrentTrack(
              tableView.getSelectionModel().getSelectedItem());
          PlayerService.getInstance().play();
        }
      }
    });
  }

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
