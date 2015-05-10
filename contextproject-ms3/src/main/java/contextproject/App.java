package contextproject;

import contextproject.audio.PlayerService;
import contextproject.controllers.CLIController;
import contextproject.controllers.WindowController;
import contextproject.formats.XmlExport;
import contextproject.loaders.FolderLoader;
import contextproject.models.Playlist;
import contextproject.sorters.GreedyPlaylistSorter;
import contextproject.sorters.PlaylistSorter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Hello world.
 *
 */
public class App extends Application {
  static Logger log = LogManager.getLogger(App.class.getName());

  private Playlist playlist;

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
    Parent root = loader.load();
    final WindowController controller = (WindowController) loader.getController();

    Scene scene = new Scene(root, 1200, 800);
    stage.setTitle("Cool demo!");
    stage.setScene(scene);
    stage.show();
    scene.getWindow().setOnHidden(new EventHandler<WindowEvent>() {
      @Override
      public void handle(WindowEvent arg0) {
        PlayerService.getInstance().exit();
        if (playlist != null) {
          XmlExport exporter = new XmlExport("library.xml", playlist);
          exporter.export();
        }
        System.exit(0);
      }
    });

    String directory = "";
    DirectoryChooser directoryChooser = new DirectoryChooser();
    File selectedDirectory = directoryChooser.showDialog(stage);
    if (selectedDirectory == null) {
      System.out.println("No directory selected.");
      System.exit(-1);
    } else {
      directory = selectedDirectory.getAbsolutePath();
    }

    FolderLoader folderLoader = new FolderLoader(directory);
    playlist = folderLoader.load();
    PlaylistSorter sorter = new GreedyPlaylistSorter();
    Playlist mixablePlaylist = sorter.sort(playlist);
    controller.setLibrary(mixablePlaylist);
  }
}
