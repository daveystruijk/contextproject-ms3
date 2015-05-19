package contextproject.controllers;

import contextproject.audio.PlayerService;
import contextproject.helpers.FileName;
import contextproject.models.Key;
import contextproject.models.Playlist;
import contextproject.models.PlaylistProperty;
import contextproject.models.Track;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class PlaylistController {
  @FXML
  private TableView<PlaylistProperty> tableView;
  @FXML
  private TableColumn<PlaylistProperty, String> titleColumn;
  @FXML
  private TableColumn<PlaylistProperty, String> artistColumn;
  @FXML
  private TableColumn<PlaylistProperty, Double> bpmColumn;
  @FXML
  private TableColumn<PlaylistProperty, Object> keyColumn;

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
              tableView.getSelectionModel().getSelectedItem().getTrack());
          PlayerService.getInstance().transition();
        }
      }
    });
  }

  /**
   * Update the table view.
   */
  public void update() {
    tableView.getItems().clear();
    ObservableList<Track> items = FXCollections.observableArrayList(playlist);
    for (Track tr : items) {
      PlaylistProperty prop = setProp(tr);
      if (!tableView.getItems().contains(prop)) {
        tableView.getItems().add(prop);
      }
    }
  }

  public Playlist getPlaylist() {
    return this.playlist;
  }

  public void setPlaylist(Playlist playlist) {
    this.playlist = playlist;
    this.update();
  }
  
  /**
   * set the property to be added to the view.
   * @param track the track to converted into a property
   * @return the property
   */
  public PlaylistProperty setProp(Track track) {
    String title = track.getTitle();
    if (title == null) {
      title = FileName.getName(track.getPath());
    }
    String artist = track.getArtist();
    if (artist == null) {
      artist = "unkown";
    }
    double bpm = track.getBpm();
    Key key = track.getKey();
    PlaylistProperty prop = new PlaylistProperty(title, artist, bpm, key, track);
    return prop;
  }
}
