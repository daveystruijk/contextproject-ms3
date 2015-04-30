package contextproject.controllers;

import contextproject.formats.M3UBuilder;
import contextproject.loaders.FolderLoader;
import contextproject.models.Playlist;

import java.util.Scanner;

/**
 * Controls CLI for time being.
 * 
 * @author Felix
 */
public class CLIController {

  /**
   * This will start a simple Command Line Interface for our first working version. Optionally to be
   * included in later versions.
   */
  public void run() {
    Scanner sc = new Scanner(System.in);
    while (true) {
      System.out.println("Construct optimal playlist! Enter directory:");

      try {
        String fileName = sc.nextLine();
        FolderLoader folderLoader = new FolderLoader(fileName);
        Playlist playlist = new Playlist(folderLoader.load());
        M3UBuilder m3uBuilder = new M3UBuilder(playlist);
        System.out.println(m3uBuilder.build());
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }
}