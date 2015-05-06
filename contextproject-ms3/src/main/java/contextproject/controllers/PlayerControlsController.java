package contextproject.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PlayerControlsController {
  @FXML
  private Button playButton;

  /**
   * Toggles the button.
   */
  public void togglePlayPause() {
    playButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent arg0) {
        if (playButton.getId().equals("playButton")) {
          playButton.setId("pauseButton");
          //TODO Music player control: ON
        } else {
          //TODO Music player contro: OFF
          playButton.setId("playButton");
        }
      }
    });
  }

}
