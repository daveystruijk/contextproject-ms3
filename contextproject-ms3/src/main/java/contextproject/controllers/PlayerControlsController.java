package contextproject.controllers;

import be.tarsos.transcoder.ffmpeg.EncoderException;

import contextproject.audio.PlayerService;
import contextproject.audio.TrackProcessor;
import contextproject.models.Track;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import javax.sound.sampled.LineUnavailableException;

public class PlayerControlsController {
  @FXML
  public Button playButton;
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
  private TrackProcessor tp;
  private static PropertyChangeSupport pcs;

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
   * set the propertychangesupport for the progresbar so it will be updated in real time.
   * 
   * @param tp
   *          the track processor .
   */
  public void setPcs(TrackProcessor tp) {
    this.tp = tp;
    pcs = new PropertyChangeSupport(tp);
    tp.setPcc(this);
    pcs.addPropertyChangeListener("progress", new PropertyChangeListener() {

      @Override
      public void propertyChange(PropertyChangeEvent evt) {
        musicBar.progressProperty().set((double) evt.getNewValue());
      }
    });
  }

  public PropertyChangeSupport getPcs() {
    return pcs;
  }

  /**
   * Toggles the button.
   */
  public void togglePlayPause() {
    playButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent arg0) {
        if (playButton.getId().equals("pauseButton")) {
          playButton.setId("playButton");
          try {
            PlayerService.getInstance().pause();
          } catch (EncoderException | LineUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        } else {
          playButton.setId("pauseButton");
          try {
            PlayerService.getInstance().resume();
          } catch (EncoderException | LineUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
      }
    });
  }
  
  /**
   * toggles the button if a track is clicked.
   */
  public void toggleButton() {
    if (playButton.getId().equals("playButton")) {
      playButton.setId("pauseButton");
    } 
  }

  /**
   * Updates the statusbar.
   * 
   * @param curtitle
   *          current title.
   * @param nxtitle
   *          next title.
   */
  public void update(Track curtitle, Track nxtitle) {
    currentTrack.setText(curtitle.getTitle());
    nextTrack.setText(nxtitle.getTitle());
  }

  public void setProgress(double prog) {
    musicBar.progressProperty().set(prog);
  }
}
