package contextproject.controllers;

import contextproject.App;
import contextproject.helpers.FileName;
import contextproject.loaders.FolderLoader;
import contextproject.models.LibraryProperty;
import contextproject.models.Playlist;
import contextproject.sorters.MaximumFlowPlaylistSorter;
import contextproject.sorters.PlaylistSorter;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;

public class MenuBarController {
  @FXML
  private Menu menu;
  @FXML
  private MenuBar menuBar;
  @FXML
  private MenuItem menuItemImport;
  @FXML
  private MenuItem menuItemExport;
  @FXML
  private MenuItem menuItemDelete;
  @FXML
  private WindowController wincontroller;
  @FXML
  private LibraryController libcontroller;
  @FXML
  private PlaylistController playcontroller;
  @FXML
  private TableView<LibraryProperty> tableView;
  private Scene scene;

  @FXML
  protected void importPlaylistButtonAction(ActionEvent event) {
    this.wincontroller = App.getController();
    this.scene = App.getScene();
    String directory = "";
    DirectoryChooser directoryChooser = new DirectoryChooser();
    File selectedDirectory = directoryChooser.showDialog(null);
    if (selectedDirectory == null) {
      System.out.println("No directory selected.");
    } else {
      directory = selectedDirectory.getAbsolutePath();
    }

    FolderLoader folderLoader = new FolderLoader(directory);
    String playlistname = FileName.getName(directory);
    Playlist playlist = folderLoader.load();
    PlaylistSorter sorter = new MaximumFlowPlaylistSorter();
    Playlist mixablePlaylist = sorter.sort(playlist);
    wincontroller.setEverything(mixablePlaylist, playlistname, scene);
  }

  @FXML
  protected void deletePlaylistButtonAction(ActionEvent event) {
    System.out.println("delete that bitch");
    this.libcontroller = wincontroller.getLibraryController();
    tableView = libcontroller.getTable();
    String name = tableView.getSelectionModel().getSelectedItem().getName();
    libcontroller.deleteplaylist(name);

  }
}
