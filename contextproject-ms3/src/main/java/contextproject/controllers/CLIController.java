package contextproject.controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import contextproject.formats.M3UBuilder;
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

    System.out.println("Construct optimal playlist! Enter directory:");

    String fileName = sc.nextLine();
    sc.close();
    FolderLoader folderLoader = new FolderLoader(fileName);
    Playlist playlist = new Playlist(folderLoader.load());
    ArrayList<Track> addedTracks = new ArrayList<Track>();
    Playlist mixablePlaylist = new Playlist();
    
    Track currentTrack = playlist.get(0);
    mixablePlaylist.add(currentTrack);
    while (currentTrack != null) {
      addedTracks.add(currentTrack);
      Track bestMatch = null;
      double bestScore = 0.0f;
      for (int j = 0; j < playlist.size(); j++) {
        if (currentTrack != playlist.get(j)) {
          double score = TrackCompatibility.getScore(currentTrack, playlist.get(j));
          if (score > bestScore && !addedTracks.contains(playlist.get(j))) {
            bestScore = score;
            bestMatch = playlist.get(j);
          }
        }
      }
      if (bestMatch == null) {
        break;
      }
      
      System.out.println();
      printTrack(currentTrack);
      printTrack(bestMatch);
      System.out.println(bestScore);
      System.out.println();
      
      mixablePlaylist.add(bestMatch);
      currentTrack = bestMatch;
    }
    
    System.out.println("Done! Calculated mixable playlist through "
        + addedTracks.size() + " tracks.");
    
    M3UBuilder builder = new M3UBuilder(mixablePlaylist);
    String name = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss'.m3u'").format(new Date());
    String dirPath = System.getProperty("user.dir").toString() + File.separator 
        + "playlists";
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
  
  public void printTrack(Track track) {
    System.out.println(track.getArtist() + " - " + track.getTitle() + " | "
        + track.getKey().getNormalizedKeyString() + ", " + track.getBpm());
  }
}