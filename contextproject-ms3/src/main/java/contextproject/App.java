package contextproject;

import java.io.File;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import contextproject.audio.PlayerService;
import contextproject.controllers.CLIController;
import contextproject.controllers.WindowController;
import contextproject.formats.XmlExport;
import contextproject.helpers.PlaylistName;
import contextproject.loaders.FolderLoader;
import contextproject.loaders.LibraryLoader;
import contextproject.models.Library;
import contextproject.models.Playlist;
import contextproject.sorters.GreedyPlaylistSorter;
import contextproject.sorters.PlaylistSorter;

/**
 * Hello world.
 *
 */
public class App extends Application {
  static Logger log = LogManager.getLogger(App.class.getName());

  private Playlist playlist;

  private Library library;

  @FXML
  private static WindowController controller;


  /**
   * This will start our app with a graphical user interface.
   */
  public static void main(String[] args) {
    boolean gui = true;
    if (gui == true) {
      launch(args);
    } else {
      CLIController control = new CLIController();
      control.run();
    }
  }

  @Override
  public void start(Stage stage) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/window.fxml"));

    Parent root = (Parent) loader.load();
    final WindowController controller = (WindowController) loader.getController();
    this.controller = controller;

    Scene scene = new Scene(root, 1200, 800);
    stage.setTitle("Cool demo!");
    stage.setScene(scene);
    stage.show();
    
    LibraryLoader libraryLoader = new LibraryLoader("library.xml");
    try {
      library = libraryLoader.load();
    }
    catch(FileNotFoundException e) {
      library = new Library();
    }
    scene.getWindow().setOnHidden(new EventHandler<WindowEvent>() {
      @Override
      public void handle(WindowEvent arg0) {
        PlayerService.getInstance().exit();
        if (playlist != null) {
          XmlExport exporter = new XmlExport("library.xml", library);
          exporter.export();
        }
        System.exit(0);
      }
    });

    controller.setLibrary(mixablePlaylist,playlistname);
  }
  
  public static WindowController getController() {
    return controller;
  }
}
