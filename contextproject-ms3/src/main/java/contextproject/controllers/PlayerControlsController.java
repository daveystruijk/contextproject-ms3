package contextproject.controllers;

import contextproject.audio.PlayerService;
import contextproject.models.Track;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
  private Button playButton;
  @FXML
  private ProgressBar musicBar;
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
  private Track curtrack;
  private double buttonwidth;
  private double textwidth;
  private double progresswidth;
  
  /**
   * . initialize the controller
   */
  public void initialize(Scene scene) {
    int sceneWidth = (int) scene.getWidth();
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
//    buttonbox1.setPrefWidth(buttonwidth);
//    buttonbox1.prefWidthProperty().set(buttonwidth);
//    buttonbox2.setPrefWidth(buttonwidth);
//    //buttonbox1.prefWidthProperty().setValue(v);
//    buttonbox1.prefWidthProperty().bind(scene.widthProperty());
//    buttonbox1.prefWidthProperty().addListener(new ChangeListener<Number>() {
//      
//      @Override
//      public void changed(ObservableValue<? extends Number> ov, Number oldValue, 
//    Number newValue) {
//        buttonwidth = newValue.doubleValue() * 0.2;
//        buttonbox1.setPrefWidth(buttonwidth);
//        buttonbox1.prefWidthProperty().set(buttonwidth);
//        buttonbox1.setMinWidth(buttonwidth);
//        buttonbox2.setPrefWidth(buttonwidth);
//        buttonbox2.prefWidthProperty().set(buttonwidth);
//      }
//    });
    
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
            PlayerService.getInstance().exit();;
        } else {
          PlayerService.getInstance().play();
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
