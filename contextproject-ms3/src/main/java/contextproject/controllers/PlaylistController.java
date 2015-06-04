package contextproject.controllers;

import contextproject.audio.PlayerService;
import contextproject.helpers.FileName;
import contextproject.models.Key;
import contextproject.models.Playlist;
import contextproject.models.Track;
import contextproject.models.TrackProperty;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class PlaylistController {
  @FXML
  private TableView<TrackProperty> tableView;
  @FXML
  private TableColumn<TrackProperty, String> titleColumn;
  @FXML
  private TableColumn<TrackProperty, String> artistColumn;
  @FXML
  private TableColumn<TrackProperty, Double> bpmColumn;
  @FXML
  private TableColumn<TrackProperty, Object> keyColumn;

  private Playlist playlist;
  private PlayerControlsController playerControlsController;

  /**
   * Setup events on the tableView items.
   */
  public void begin(final PlayerControlsController playerControlsController, Scene scene) {
    this.playerControlsController = playerControlsController;
    PlayerService.getInstance().setCurrentTrack(playlist.get(0));
    PlayerService.getInstance().play();
    Track curtitle = playlist.get(0);
    String nxtitle;
    if (1 > playlist.size() - 1) { 
      nxtitle = "none";
    } else {
      nxtitle = playlist.get(1).getTitle();
    }
    playerControlsController.update(curtitle,nxtitle);

    tableView.setOnMousePressed(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
          PlayerService.getInstance().setNextTrack(
              tableView.getSelectionModel().getSelectedItem().getTrack());
          Track curtrack = tableView.getSelectionModel().getSelectedItem().getTrack();
          String nxtitle;
          if ((playlist.indexOf(curtrack) + 1) > (playlist.size() - 1)) {
            nxtitle = "none";
          } else {
            nxtitle = playlist.get(playlist.indexOf(curtrack) + 1).getTitle();
          }
          PlayerService.getInstance().transition();
          playerControlsController.update(curtrack,nxtitle);
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
      TrackProperty prop = setProp(tr);
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
   * 
   * @param track
   *          the track to converted into a property
   * @return the property
   */
  public TrackProperty setProp(Track track) {
    String title = track.getTitle();
    if (title == null) {
      title = FileName.getName(track.getPath());
      track.setTitle(title);
    }
    String artist = track.getArtist();
    if (artist == null) {
      artist = "unkown";
    }
    double bpm = track.getBpm();
    Key key = track.getKey();
    TrackProperty prop = new TrackProperty(title, artist, bpm, key, track);
    return prop;
  }
}