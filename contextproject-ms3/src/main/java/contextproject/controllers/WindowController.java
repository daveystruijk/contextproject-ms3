package contextproject.controllers;

import contextproject.models.Library;
import contextproject.models.Playlist;

import javafx.fxml.FXML;
import javafx.scene.Scene;
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
  @FXML
  private MenuBarController menubarController;

  /**
   * This function passes on the playlist from the model to the view.
   * 
   * @param playlist
   *          : The playlist that comes from our algorithm
   */
  public void setEverything(Playlist playlist, String name, Scene scene) {
    playlistController.setPlaylist(playlist);
    libraryController.setLibrary(playlist, name);
    libraryController.begin(playlistController,scene);
    playerControlsController.initialize(scene);
    playlistController.begin(playerControlsController,scene);
    
  }
  /**
   * this function passes on the library from the library.xml to the view
   * 
   * @param library
   *          the loaded library
   */
  public void setLibrary(Library library, Scene scene) {
    Playlist pl = library.get(0);
    playlistController.setPlaylist(pl);
    libraryController.setLibrary(library);
    libraryController.begin(playlistController,scene);
    playerControlsController.initialize(scene);
    playlistController.begin(playerControlsController,scene);
  }
  
  public PlaylistController getPlaylistController() {
    return playlistController;
  }
  
  public LibraryController getLibraryController() {
    return libraryController;
  }
}
