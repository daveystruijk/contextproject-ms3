package contextproject.controllers;

import contextproject.audio.PlayerService;
import contextproject.audio.transitions.BaseTransition.TransitionDoneCallback;
import contextproject.helpers.FileName;
import contextproject.models.MusicalKey;
import contextproject.models.Playlist;
import contextproject.models.Track;
import contextproject.models.TrackProperty;

import javafx.application.Platform;
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
  private TableColumn<TrackProperty, String> playingColumn;
  @FXML
  private TableColumn<TrackProperty, String> titleColumn;
  @FXML
  private TableColumn<TrackProperty, String> artistColumn;
  @FXML
  private TableColumn<TrackProperty, String> bpmColumn;
  @FXML
  private TableColumn<TrackProperty, Object> keyColumn;
  @FXML
  private TableColumn<TrackProperty, Double> energyColumn;

  private Track playingtrack;
  private Playlist playlist;
  private PlayerControlsController playerControlsController;

  /**
   * Setup events on the tableView items.
   */
  public void begin(final PlayerControlsController playerControlsController, Scene scene) {
    this.playerControlsController = playerControlsController;
    Track firstTrack = playlist.get(0);
    PlayerService.getInstance().setCurrentTrack(firstTrack);
    PlayerService.getInstance().setUpCurrentTrack();
    playingtrack = firstTrack;
    Track nextTrack = getNextTrack(firstTrack);
    prepareNextTrackTransition(nextTrack);
    updateTracks(firstTrack, nextTrack);

    // Setup click handler on track
    tableView.setOnMousePressed(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
          handleTrackClick();
        }
      }
    });
  }

  /**
   * Gets called whenever a track is clicked on within the GUI.
   */
  private void handleTrackClick() {
    Track selectedTrack = getSelectedTrack();
    Track nextTrack = getNextTrack(selectedTrack);

    prepareNextTrackTransition(nextTrack);
    playTrack(selectedTrack);
    updateTracks(selectedTrack, nextTrack);
  }

  /**
   * Plays a track from the playlist and sets up events for next transitions. This should happen
   * only the first time and whenever a track is clicked, since it stops audio and doesn't do
   * transitions.
   * 
   * @param track
   *          The track object to play.
   */
  private void playTrack(Track track) {
    PlayerService.getInstance().setCurrentTrack(track);
    PlayerService.getInstance().playCurrentTrack();
    playerControlsController.toggleButton();
    playingtrack = track;
  }

  /**
   * Sets up events for the track to transition into the next track.
   * 
   * @param track
   *          The track to prepare.
   */
  private void prepareNextTrackTransition(Track track) {
    PlayerService.getInstance().prepareNextTrack(track);
    PlayerService.getInstance().setupTransition(new TransitionDoneCallback() {
      @Override
      public void onFinished() {
        // When transition is done, setup events for the next one
        Track nextTrack = getNextTrack(track);
        prepareNextTrackTransition(nextTrack);
        Platform.runLater(new Runnable() {
          @Override
          public void run() {
            updateTracks(track, nextTrack);
          }
        });

      }
    });
  }

  /**
   * Gets the selected track object from the current playlist.
   * 
   * @return Track
   */
  private Track getSelectedTrack() {
    return tableView.getSelectionModel().getSelectedItem().getTrack();
  }

  /**
   * Determines the next track (current track's index + 1) in the playlist.
   * 
   * @param track
   *          The current track that is playing.
   * @return Track
   */
  private Track getNextTrack(Track track) {
    if (playlist.indexOf(track) + 1 == playlist.size()) {
      return playlist.get(0);
    } else {
      return playlist.get(playlist.indexOf(track) + 1);
    }
  }

  /**
   * Updates the GUI to show what track is playing and what will be next.
   */
  private void updateTracks(Track track, Track nextTrack) {
    playerControlsController.update(track, nextTrack);
    setPlayingIndicator(track, ">");

  }

  /**
   * Sets/removes the playing indicator in front of the specified track.
   * 
   * @param track
   *          The track to indicate.
   * @param playing
   *          The string to put into the playing column.
   */
  private void setPlayingIndicator(Track track, String playing) {
    for (TrackProperty trackProp : tableView.getItems()) {
      trackProp.setPlaying("");
    }
    TrackProperty trackProperty = tableView.getItems().get(playlist.indexOf(track));
    trackProperty.setPlaying(playing);
  }

  /**
   * Update the table view.
   */
  public void update() {
    tableView.getItems().clear();
    ObservableList<Track> items = FXCollections.observableArrayList(playlist);
    if (items.isEmpty()) {
      tableView.setDisable(true);
      tableView.setOpacity(1);
      tableView.getItems().add(new TrackProperty(null, null, null, null, null, playingtrack, null));
    } else {
      tableView.setDisable(false);
      for (Track tr : items) {
        TrackProperty prop = setProp(tr);
        if (!tableView.getItems().contains(prop)) {
          tableView.getItems().add(prop);
        }
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
    if (title == null || title.length() < 1) {
      title = FileName.getName(track.getPath());
      track.setTitle(title);
    }
    String artist = track.getArtist();
    if (artist == null || artist.length() < 1) {
      artist = "unkown";
      track.setArtist("unknown");
    }
    String bpm = "" + track.getBpm();
    MusicalKey key = track.getKey();
    String playing;
    if (track.equals(playingtrack)) {
      playing = ">";
    } else {
      playing = "";
    }
    String energy = "" + track.getAverageEnergy();
    TrackProperty prop = new TrackProperty(title, artist, bpm, key, energy, track, playing);
    return prop;
  }
}
