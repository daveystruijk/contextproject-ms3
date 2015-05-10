package contextproject.controllers;

import contextproject.models.Playlist;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class WindowController {
  @FXML
  private Text actiontarget;
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
  public void setLibrary(Playlist playlist) {
    libraryController.setLibrary(playlist);
    libraryController.begin();
    playerControlsController.togglePlayPause();
  }
}
