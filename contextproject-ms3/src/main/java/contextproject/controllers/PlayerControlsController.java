package contextproject.controllers;

import contextproject.audio.PlayerService;
import contextproject.models.Track;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PlayerControlsController {
  @FXML
  public Button pauseButton;
  @FXML
  public ProgressBar musicBar;
  @FXML
  public TextField currentTrack;
  @FXML
  public TextField nextTrack;
  @FXML
  public Text curtext;
  @FXML
  public Text nxtext;
  @FXML
  public VBox centerBox;
  @FXML
  public HBox buttonbox1;
  @FXML
  public HBox buttonbox2;
  @FXML
  public HBox curbox;
  @FXML
  public HBox nextbox;
  private double buttonwidth;
  private double textwidth;
  private double progresswidth;

  /**
   * . initialize the controller
   */
  public void initialize(double sceneWidth) {
    textwidth = sceneWidth * 0.3;
    progresswidth = sceneWidth * 0.4;
    buttonwidth = progresswidth * 0.48;
    buttonbox1.setPrefWidth(buttonwidth);
    buttonbox1.setMinWidth(10);
    buttonbox2.setPrefWidth(buttonwidth);
    curbox.setPrefWidth(textwidth);
    currentTrack.setPrefWidth(textwidth * 0.8);
    nextbox.setPrefWidth(textwidth);
    nextTrack.setPrefWidth(textwidth * 0.8);
    musicBar.setPrefWidth(progresswidth);

    currentTrack.setEditable(false);
    nextTrack.setEditable(false);
    musicBar.progressProperty().set(0);
    togglePlayPause();
  }

  /**
   * Toggles the button.
   */
  public void togglePlayPause() {
    pauseButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent arg0) {
        if (pauseButton.getId().equals("pauseButton")) {
          pauseButton.setId("playButton");
          //PlayerService.getInstance().pause();
        } else {
          //PlayerService.getInstance().resume();
          pauseButton.setId("pauseButton");
        }
      }
    });
  }

  /**
   * Updates the statusbar.
   * 
   * @param curtitle
   *          current title.
   * @param nxtitle
   *          next title.
   */
  public void update(Track curtitle, String nxtitle) {
    currentTrack.setText(curtitle.getTitle());
    nextTrack.setText(nxtitle);
  }

  public void setProgres(double prog) {
    musicBar.progressProperty().set(prog);
  }
}
