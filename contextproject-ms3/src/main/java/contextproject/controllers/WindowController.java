package contextproject.controllers;

import contextproject.models.Library;
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
  public void setEverything(Playlist playlist,String name) {
    playlistController.setPlaylist(playlist);
    libraryController.setLibrary(playlist,name);
    libraryController.begin(playlistController);
    playlistController.begin();
    playerControlsController.togglePlayPause();
  }
  
  public void setLibrary(Library library){
    Playlist pl = library.get(0);
      playlistController.setPlaylist(pl);
      libraryController.setLibrary(library);
      libraryController.begin(playlistController);
      playlistController.begin();
      playerControlsController.togglePlayPause();
  }
}
