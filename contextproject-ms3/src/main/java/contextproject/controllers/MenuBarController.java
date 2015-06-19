package contextproject.controllers;

import contextproject.App;
import contextproject.helpers.FileName;
import contextproject.helpers.TextFileReader;
import contextproject.loaders.FolderLoader;
import contextproject.models.Playlist;
import contextproject.sorters.GreedyPlaylistSorter;
import contextproject.sorters.MaximumFlowPlaylistSorter;
import contextproject.sorters.PlaylistSorter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuBarController {
  static Logger log = LogManager.getLogger(MenuBarController.class.getName());

  @FXML
  private Menu file;
  @FXML
  private Menu about;
  @FXML
  private MenuBar menuBar;
  @FXML
  private MenuItem menuItemImport;
  @FXML
  private MenuItem menuItemExport;
  @FXML
  private MenuItem help;
  @FXML
  private MenuItem us;
  @FXML
  private WindowController wincontroller;
  private Scene scene;

  @FXML
  protected void importPlaylistButtonAction(ActionEvent event) {
    this.wincontroller = App.getController();
    this.scene = App.getScene();
    String directory = "";
    DirectoryChooser directoryChooser = new DirectoryChooser();
    File selectedDirectory = directoryChooser.showDialog(null);
    if (selectedDirectory == null) {
      log.info("No directory selected.");
    } else {
      directory = selectedDirectory.getAbsolutePath();
    }

    FolderLoader folderLoader = new FolderLoader(directory);
    String playlistname = FileName.getName(directory);
    Playlist playlist = folderLoader.load();
    PlaylistSorter sorter;
    if (App.getMaxFlowSorter()) {
      sorter = new MaximumFlowPlaylistSorter();
    } else {
      sorter = new GreedyPlaylistSorter();
    }
    Playlist mixablePlaylist = sorter.sort(playlist);
    wincontroller.setEverything(mixablePlaylist, playlistname, scene);
  }

  @FXML
  protected void helpButtonAction(ActionEvent event) {
    final Stage dialog = new Stage();
    dialog.initModality(Modality.APPLICATION_MODAL);
    dialog.initOwner(App.getStage());
    VBox dialogVbox = new VBox(20);
    ArrayList<String> lines = TextFileReader.read("help.txt");
    String text = "";
    for (String line : lines) {
      text = text + line + "\n";
    }
    dialogVbox.getChildren().add(new Text(text));
    Scene dialogScene = new Scene(dialogVbox, 502, 70);
    dialog.setScene(dialogScene);
    dialog.show();
  }
  @FXML
  protected void aboutUsButtonAction(ActionEvent event) {
    final Stage dialog = new Stage();
    dialog.initModality(Modality.APPLICATION_MODAL);
    dialog.initOwner(App.getStage());
    VBox dialogVbox = new VBox(20);
    ArrayList<String> lines = TextFileReader.read("about.txt");
    String text = "";
    for (String line : lines) {
      text = text + line + "\n";
    }
    dialogVbox.getChildren().add(new Text(text));
    Scene dialogScene = new Scene(dialogVbox, 550, 500);
    dialog.setScene(dialogScene);
    dialog.show();
  }
}
