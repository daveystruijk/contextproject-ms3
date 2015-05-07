package contextproject.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import contextproject.models.Playlist;

public class WindowController {
  @FXML private Text actiontarget;
  @FXML private LibraryController libraryController;
  @FXML private PlayerControlsController playerControlsController;
  
  public void setLibrary(Playlist playlist) {
    libraryController.setLibrary(playlist);
    libraryController.initialize();
    playerControlsController.togglePlayPause();
  }
}
