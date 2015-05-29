package contextproject.controllers;

import contextproject.models.Track;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

public class PlayerControlsController {
  @FXML
  private Button playButton;
  @FXML
  private ProgressBar musicBar;
  @FXML
  public TextField currentTrack;
  @FXML
  public TextField nextTrack;
  private Track curtrack;

  /**
   * . initialize the controller
   */
  public void initialize() {
    currentTrack.setEditable(false);
    nextTrack.setEditable(false);
    togglePlayPause();
    musicBar.progressProperty().set(0.55);
    musicBar.progressProperty().addListener(new ChangeListener<Number>() {

      @Override
      public void changed(ObservableValue<? extends Number> observeValue,
          Number oldValue, Number newValue) {
        double length = (double) curtrack.getLength();
        double progress = newValue.doubleValue() / length;
        musicBar.progressProperty().set(progress);
        
      }
    });
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
  public void update(Track curtitle, String nxtitle) {
    this.curtrack = curtitle;
    currentTrack.setText(curtitle.getTitle());
    nextTrack.setText(nxtitle);
  }
}
