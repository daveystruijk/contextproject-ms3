package contextproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import contextproject.controllers.CLIController;
import contextproject.controllers.WindowController;
import contextproject.loaders.FolderLoader;
import contextproject.models.Playlist;
import contextproject.sorters.GreedyPlaylistSorter;
import contextproject.sorters.PlaylistSorter;

/**
 * Hello world.
 *
 */
public class App extends Application {
  static Logger log = LogManager.getLogger(App.class.getName());
  static final String DIRECTORY = "/Users/daveystruijk/Documents/FEESJE/House (Chill)";
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
    WindowController controller = (WindowController) loader.getController();
    
    FolderLoader folderLoader = new FolderLoader(DIRECTORY);
    Playlist playlist = folderLoader.load();
    PlaylistSorter sorter = new GreedyPlaylistSorter();
    Playlist mixablePlaylist = sorter.sort(playlist);
    controller.setLibrary(mixablePlaylist);
    
    Scene scene = new Scene(root, 1200, 800);
    stage.setTitle("Cool demo!");
    stage.setScene(scene);
    stage.show();
    

  }

}
