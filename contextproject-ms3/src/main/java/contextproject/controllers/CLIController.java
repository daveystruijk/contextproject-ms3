package contextproject.controllers;

import java.util.Scanner;

import contextproject.helpers.TrackCompatibility;
import contextproject.loaders.FolderLoader;
import contextproject.models.Playlist;
import contextproject.models.Track;

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

      String fileName = sc.nextLine();
      FolderLoader folderLoader = new FolderLoader(fileName);
      Playlist playlist = new Playlist(folderLoader.load());
      
      for (Track i : playlist) {
        Track bestMatch = null;
        float bestScore = 0.0f;
        for (int j = 0; j < playlist.size(); j++) {
          if (i != playlist.get(j)) {
            float score = TrackCompatibility.getScore(i, playlist.get(j));
            if (score > bestScore) {
              bestScore = score;
              bestMatch = playlist.get(j);
            }
          }
        }
        System.out.println();
        printTrack(i);
        printTrack(bestMatch);
        System.out.println(bestScore);
        System.out.println();
      }
    }
  }
  
  public void printTrack(Track track) {
    System.out.println(track.getArtist() + " - " + track.getTitle() + " | "
        + track.getKey().getNormalizedKeyString() + ", " + track.getBpm());
  }
}