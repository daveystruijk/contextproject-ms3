package contextproject.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class PlayerControlsController {
  @FXML
  private Button playButton;
  @FXML
  private Slider slider;
  @FXML
  public TextField currentTrack;
  @FXML
  public TextField nextTrack;

  /**
   * . initialize the controller
   */
  public void initialize() {
    currentTrack.setEditable(false);
    nextTrack.setEditable(false);
    togglePlayPause();
  }

  /**
   * Toggles the button.
   */
  public void togglePlayPause() {
    playButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent arg0) {
        if (playButton.getId().equals("playButton")) {
          playButton.setId("pauseButton");
          // TODO Music player control: ON
        } else {
          // TODO Music player control: OFF
          playButton.setId("playButton");
        }
      }
    });
  }
  /**
   * updates the statusbar.
   * 
   * @param title
   *          the title of the current song.
   */
  public void update(String curtitle, String nxtitle) {
    currentTrack.setText(curtitle);
    nextTrack.setText(nxtitle);
  }
}
