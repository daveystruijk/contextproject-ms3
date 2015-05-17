package contextproject.controllers;

import contextproject.audio.PlayerService;
import contextproject.models.Playlist;
import contextproject.models.Track;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class PlaylistController {
  @FXML
  private TableView<Track> tableView;

  private Playlist playlist;

  /**
   * Setup events on the tableView items.
   */
  public void begin() {
    PlayerService.getInstance().setCurrentTrack(playlist.get(0));
    PlayerService.getInstance().play();

    tableView.setOnMousePressed(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
          PlayerService.getInstance().setCurrentTrack(
              tableView.getSelectionModel().getSelectedItem());
          PlayerService.getInstance().transition();
        }
      }
    });
  }

  /**
   * Update the table view.
   */
  public void update() {
    ObservableList<Track> items = FXCollections.observableArrayList(playlist);
    tableView.setItems(items);
  }

  public Playlist getPlaylist() {
    return this.playlist;
  }

  public void setPlaylist(Playlist playlist) {
    this.playlist = playlist;
    this.update();
  }
}
