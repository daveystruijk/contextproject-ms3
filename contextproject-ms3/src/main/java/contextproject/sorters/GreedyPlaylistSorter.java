package contextproject.sorters;

import contextproject.helpers.TrackCompatibility;
import contextproject.models.Playlist;
import contextproject.models.Track;

import java.util.ArrayList;

public class GreedyPlaylistSorter implements PlaylistSorter {

  @Override
  public Playlist sort(Playlist playlist) {
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
    
    return mixablePlaylist;
  }

  
  public void printTrack(Track track) {
    System.out.println(track.getArtist() + " - " + track.getTitle() + " | "
        + track.getKey().getNormalizedKeyString() + ", " + track.getBpm());
  }
}
