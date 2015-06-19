package contextproject;

import contextproject.controllers.CliController;
import contextproject.controllers.WindowController;
import contextproject.formats.XmlExport;
import contextproject.helpers.FileName;
import contextproject.helpers.StackTrace;
import contextproject.loaders.FolderLoader;
import contextproject.loaders.LibraryLoader;
import contextproject.models.Library;
import contextproject.models.Playlist;
import contextproject.sorters.GreedyPlaylistSorter;
import contextproject.sorters.MaximumFlowPlaylistSorter;
import contextproject.sorters.PlaylistSorter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Hello world.
 *
 */
public class App extends Application {
  static Logger log = LogManager.getLogger(App.class.getName());

  private static Library library;
  private boolean empty = false;
  private int screenWidth;
  private int screenHeight;
  private static Scene scene;
  private static boolean maxFlowSorter = true;
  private static Stage stage;

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
      CliController control = new CliController();
      control.run();
    }
  }

  @Override
  public void start(Stage stage) {
    try {
      App.stage = stage;
      setUp(stage);

      try {
        LibraryLoader libraryLoader = new LibraryLoader("library.xml");
        library = libraryLoader.load();
        if (library.size() < 1) {
          empty = true;
        }
      } catch (IOException | NullPointerException e) {
        library = new Library();
        empty = true;
        log.info("No library found");
      }
      scene.getWindow().setOnHidden(new EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent arg0) {
          if (library != null) {
            XmlExport exporter = new XmlExport("library.xml", library);
            exporter.export();
          }
          System.exit(0);
        }
      });
      if (empty) {
        log.trace("opening folderchooser");
        String directory = "";
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(null);
        if (selectedDirectory == null) {
          log.warn("No directory selected.");
          System.exit(-1);
        } else {
          directory = selectedDirectory.getAbsolutePath();
        }
        FolderLoader folderLoader = new FolderLoader(directory);
        String playlistname = FileName.getName(directory);
        Playlist playlist = folderLoader.load();
        PlaylistSorter sorter;
        if (maxFlowSorter) {
          sorter = new MaximumFlowPlaylistSorter();
        } else {
          sorter = new GreedyPlaylistSorter();
        }
        Playlist mixablePlaylist = sorter.sort(playlist);
        controller.setEverything(mixablePlaylist, playlistname, scene);
      } else {
        controller.setLibrary(library, scene);
      }
    } catch (Exception e) {
      log.fatal(e.getMessage(), e);
      System.exit(-1);
    }
  }

  public static WindowController getController() {
    return controller;
  }

  public static Scene getScene() {
    return scene;
  }

  /**
   * sets the library to be exported to library.xml.
   * 
   * @param lib
   *          the library of the app.
   */
  public static void setLibrary(Library lib) {
    library.clear();
    for (Playlist pl : lib) {
      library.add(pl);
    }
  }

  /**
   * Setup the start variables.
   * 
   * @param stage
   *          JavaFX element for gui
   */
  public void setUp(Stage stage) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/window.fxml"));

    try {
      Parent root = (Parent) loader.load();
      Rectangle2D screen = Screen.getPrimary().getVisualBounds();
      screenWidth = (int) screen.getWidth();
      screenHeight = (int) screen.getHeight();
      Scene scene = new Scene(root, screenWidth, screenHeight);
      final WindowController controller = (WindowController) loader.getController();
      App.controller = controller;
      stage.setTitle("Demo");
      stage.setScene(scene);
      stage.show();
      App.scene = scene;
      App.library = new Library();

    } catch (IOException e) {
      log.error("Error in setting up GUI");
      log.trace(StackTrace.stackTrace(e));
    }

  }
  
  public static boolean getMaxFlowSorter() {
    return maxFlowSorter;
  }
  public static Stage getStage() {
    return App.stage;
  }
}
