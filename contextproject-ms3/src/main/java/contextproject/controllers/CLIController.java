package contextproject.controllers;

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
    System.out.println("Welcome to our application!");
    while (true) {
      System.out.println("Construct playlist! Enter foldername");

      try {
        String fileName = sc.next();
        FolderLoader folderLoader = new FolderLoader(fileName);
        Playlist result = new Playlist(folderLoader.load());
        //TODO!
        System.out.println("\\TODO!");
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
      sc.close();

    }
  }
}