package contextproject;

import be.tarsos.transcoder.Attributes;
import be.tarsos.transcoder.DefaultAttributes;
import be.tarsos.transcoder.ffmpeg.EncoderException;

import contextproject.audio.EnergyLevelProcessor;
import contextproject.audio.PlayerService;
import contextproject.controllers.CliController;
import contextproject.controllers.WindowController;
import contextproject.formats.XmlExport;
import contextproject.helpers.FileName;
import contextproject.loaders.FolderLoader;
import contextproject.loaders.LibraryLoader;
import contextproject.models.Library;
import contextproject.models.Playlist;
import contextproject.models.Track;
import contextproject.sorters.MaximumFlowPlaylistSorter;
import contextproject.sorters.PlaylistSorter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;

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

  @FXML
  private static WindowController controller;

  /**
   * This will start our app with a graphical user interface.
   */
  public static void main(String[] args) {
    Attributes attributes = DefaultAttributes.WAV_PCM_S16LE_MONO_44KHZ.getAttributes();
    attributes.setSamplingRate(44100);
    boolean gui = true;
    if (gui == true) {
      launch(args);
    } else {
      CliController control = new CliController();
      control.run();
    }
  }

  @Override
  public void start(Stage stage) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/window.fxml"));

    Parent root = (Parent) loader.load();
    final WindowController controller = (WindowController) loader.getController();
    App.controller = controller;
    
    Rectangle2D screen = Screen.getPrimary().getVisualBounds();
    screenWidth = (int) screen.getWidth();
    screenHeight = (int) screen.getHeight();
    Scene scene = new Scene(root, screenWidth, screenHeight);
    stage.setTitle("dj shrubberyrobber");
    stage.setScene(scene);
    stage.show();
    App.scene = scene;

    try {
      LibraryLoader libraryLoader = new LibraryLoader("library.xml");
      library = libraryLoader.load();
      if (library.size() < 1) {
        empty = true;
      }
    } catch (IOException | NullPointerException e) {
      library = new Library();
      empty = true;
    }
    scene.getWindow().setOnHidden(new EventHandler<WindowEvent>() {
      @Override
      public void handle(WindowEvent arg0) {
        PlayerService.getInstance().exit();
        if (library != null) {
          XmlExport exporter = new XmlExport("library.xml", library);
          exporter.export();
          System.out.println(library.size());
        }
        System.exit(0);
      }
    });
    if (empty) {
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
      controller.setEverything(mixablePlaylist, playlistname,scene);
    } else {
      controller.setLibrary(library,scene);
    }
  }

  public static WindowController getController() {
    return controller;
  }
  
  public static Scene getScene(){
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
}
