package contextproject.controllers;

import contextproject.App;
import contextproject.helpers.PlaylistName;
import contextproject.loaders.FolderLoader;
import contextproject.models.Playlist;
import contextproject.sorters.GreedyPlaylistSorter;
import contextproject.sorters.PlaylistSorter;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;

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
  private WindowController controller;
  
  
  @FXML protected void pushButton(ActionEvent event) {
    this.controller = App.getController();
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
    String playlistname = PlaylistName.getName(directory);
    Playlist playlist = folderLoader.load();
    PlaylistSorter sorter = new GreedyPlaylistSorter();
    Playlist mixablePlaylist = sorter.sort(playlist);
    controller.setLibrary(mixablePlaylist,playlistname);
}
}
