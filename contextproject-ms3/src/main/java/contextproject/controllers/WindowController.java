package contextproject.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class WindowController {
  @FXML private Text actiontarget;
  @FXML private LibraryController libraryController;
  @FXML private PlayerControlsController playerControlsController;
  
  public void doSomething() {
    libraryController.setItems();
    playerControlsController.togglePlayPause();
  }
}
