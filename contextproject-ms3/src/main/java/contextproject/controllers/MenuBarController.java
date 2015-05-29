package contextproject.controllers;

import contextproject.App;
import contextproject.helpers.FileName;
import contextproject.loaders.FolderLoader;
import contextproject.models.Playlist;
import contextproject.sorters.MaximumFlowPlaylistSorter;
import contextproject.sorters.PlaylistSorter;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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
  private WindowController wincontroller;
  @FXML
  private LibraryController libcontroller;
  
  
  @FXML protected void importPlaylistButtonAction(ActionEvent event) {
    this.wincontroller = App.getController();
    String directory = "";
    DirectoryChooser directoryChooser = new DirectoryChooser();
    File selectedDirectory = directoryChooser.showDialog(null);
    if (selectedDirectory == null) {
      System.out.println("No directory selected.");
      System.exit(-1);
    } else {
      directory = selectedDirectory.getAbsolutePath();
    }

    FolderLoader folderLoader = new FolderLoader(directory);
    String playlistname = FileName.getName(directory);
    Playlist playlist = folderLoader.load();
    PlaylistSorter sorter = new MaximumFlowPlaylistSorter();
    Playlist mixablePlaylist = sorter.sort(playlist);
    wincontroller.setEverything(mixablePlaylist,playlistname);
  }
}
