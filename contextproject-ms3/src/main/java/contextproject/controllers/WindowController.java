package contextproject.controllers;

import contextproject.models.Playlist;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class WindowController {
  @FXML
  private Text actiontarget;
  @FXML
  private PlaylistController playlistController;
  @FXML
  private LibraryController libraryController;
  @FXML
  private PlayerControlsController playerControlsController;

  /**
   * This function passes on the playlist from the model to the view.
   * 
   * @param playlist
   *          : The playlist that comes from our algorithm
   */
  public void setLibrary(Playlist playlist,String name) {
    playlistController.setPlaylist(playlist);
    libraryController.setLibrary(playlist,name,playlistController);
    playlistController.begin();
    libraryController.begin();
    playerControlsController.togglePlayPause();
  }
}
