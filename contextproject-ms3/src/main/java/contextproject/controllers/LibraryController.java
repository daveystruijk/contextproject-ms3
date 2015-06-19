package contextproject.controllers;

import contextproject.App;
import contextproject.models.Library;
import contextproject.models.LibraryProperty;
import contextproject.models.Playlist;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class LibraryController {
  @FXML
  private TableView<LibraryProperty> tableView;
  @FXML
  private TableColumn<LibraryProperty, String> nameColumn;
  @FXML
  private PlaylistController playlistController;
  private Library lib = new Library();
  private ArrayList<String> names = new ArrayList<String>();
  private Playlist playlist;

  /**
   * for the playlists. when clicked, show the tracks of the playlist.
   */
  public void begin(PlaylistController playlistcontroller, Scene scene) {
    this.playlistController = playlistcontroller;
    tableView.setOnMousePressed(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        if (event.isPrimaryButtonDown()) {
          String name = tableView.getSelectionModel().getSelectedItem().getName();
          playlist = getPlaylist(name);
          playlistController.setPlaylist(playlist);
        }
      }
    });
  }

  /**
   * Update the library.
   */
  public void update() {
    tableView.getItems().clear();
    ObservableList<String> items = FXCollections.observableArrayList(names);
    if (items.isEmpty()) {
      tableView.setDisable(true);
      tableView.setOpacity(1);
      tableView.getItems().add(new LibraryProperty(null));
    } else {
      tableView.setDisable(false);
      for (String s : items) {
        LibraryProperty prop = new LibraryProperty(s);
        if (!tableView.getItems().contains(prop)) {
          tableView.getItems().add(prop);
        }
      }
    }

  }
  /**
   * Sets the library of playlists.
   * 
   * @param playlist
   *          the playlist itself
   * @param name
   *          the name of the playlist.
   */
  public void setLibrary(Playlist playlist, String name) {
    playlist.setName(name);
    this.names.add(name);
    this.lib.add(playlist);
    setAppLibrary(lib);
    this.update();
  }
  /**
   * sets the library of playlists.
   * 
   * @param library
   *          the playlists in library.
   */
  public void setLibrary(Library library) {
    int nr = 1;
    for (Playlist pl : library) {
      String name = "";
      if (pl.getName() == null) {
        name = "playlist: " + nr;
      } else {
        name = pl.getName();
      }
      this.names.add(name);
      this.lib.add(pl);
      this.update();
      nr++;
    }
    setAppLibrary(this.lib);
  }

  /**
   * return the playlist.
   * 
   * @param name
   *          the name of the playlist.
   * @return the playlist.
   */
  public Playlist getPlaylist(String name) {
    int index = names.indexOf(name);
    return lib.get(index);
  }

  public Playlist getPlaylist() {
    return playlist;
  }
  /**
   * delete the currently selected playlist.
   */
  public void deletePlaylist() {
    String name = playlist.getName();
    this.names.remove(name);
    this.lib.remove(playlist);
    Playlist pl = new Playlist();
    setAppLibrary(lib);
    this.update();
    playlistController.setPlaylist(pl);
  }

  public void setAppLibrary(Library library) {
    App.setLibrary(library);
  }

  public TableView<LibraryProperty> getTable() {
    return tableView;
  }
}
