package contextproject.controllers;

import contextproject.models.Playlist;
import contextproject.models.Property;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class PlaylistsController {
  @FXML
  private TableView<Property> tableView;
  @FXML
  private TableColumn<Property, String> nameColumn;
  @FXML
  LibraryController libraryController;
  private ArrayList<Playlist> list = new ArrayList<Playlist>();
  private ArrayList<String> names = new ArrayList<String>();

  /**
   * for the playlists.
   */
  public void begin() {

    tableView.setOnMousePressed(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        if (event.isPrimaryButtonDown()) {
          String name = tableView.getSelectionModel().getSelectedItem().getName();
          Playlist lib = getLibrary(name);
          libraryController.setLibrary(lib);

        }
      }
    });
  }

  /**
   * update the library.
   */
  public void update() {
    tableView.getItems().clear();
    ObservableList<String> items = FXCollections.observableArrayList(names);
    for (String s : items) {
      Property prop = new Property(s);
      if (!tableView.getItems().contains(prop)) {
        tableView.getItems().add(prop);
      }
    }

  }
  /**
   * set the library.
   * 
   * @param playlist
   *          the playlist itself.
   */
  public void setLibrary(Playlist playlist, String name, LibraryController libraryController) {
    this.libraryController = libraryController;
    this.names.add(name);
    this.list.add(playlist);
    this.update();
  }

  public Playlist getLibrary(String name) {
    int index = names.indexOf(name);
    return list.get(index);
  }
}
