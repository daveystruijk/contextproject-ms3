package contextproject.controllers;

import contextproject.formats.M3UBuilder;
import contextproject.loaders.FolderLoader;
import contextproject.models.Playlist;
import contextproject.sorters.MaximumFlowPlaylistSorter;
import contextproject.sorters.PlaylistSorter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Controls CLI for time being.
 */
public class CliController {

  /**
   * This will start a simple Command Line Interface for our first working version. Optionally to be
   * included in later versions.
   */
  public void run() {
    Scanner sc = new Scanner(System.in);

    System.out.println("Construct optimal playlist! Enter directory:");

    String fileName = sc.nextLine();
    sc.close();

    FolderLoader folderLoader = new FolderLoader(fileName);
    Playlist playlist = folderLoader.load();
    PlaylistSorter sorter = new MaximumFlowPlaylistSorter();
    Playlist result = sorter.sort(playlist);

    System.out.println("Done! Calculated mixable playlist through " + result.size() + " tracks.");

    M3UBuilder builder = new M3UBuilder(result);
    String name = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss'.m3u'").format(new Date());
    String dirPath = System.getProperty("user.dir").toString() + File.separator + "playlists";
    new File(dirPath).mkdirs();
    String fullPath = dirPath + File.separator + name;
    try {
      FileWriter writer = new FileWriter(fullPath);
      writer.write(builder.build());
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println("Exported to " + fullPath);
  }
}
