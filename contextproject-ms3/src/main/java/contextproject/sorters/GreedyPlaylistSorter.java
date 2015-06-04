package contextproject.sorters;

import contextproject.helpers.TrackCompatibility;
import contextproject.models.Playlist;
import contextproject.models.Track;

import java.util.ArrayList;

public class GreedyPlaylistSorter implements PlaylistSorter {

  private ArrayList<Track> addedTracks;
  private Playlist mixablePlaylist;
  private Track currentTrack;
  private double score;
  private double bestScore;
  private Track bestMatch;

  /**
   * Sets up the playlist to be sorted and adds the first Track to the mix.
   */
  public void setUpPlaylist(Playlist playlist) {
    addedTracks = new ArrayList<Track>();
    mixablePlaylist = new Playlist();
    currentTrack = playlist.get(0);
    mixablePlaylist.add(currentTrack);
  }

  /**
   * This is the portion of the algorithm that iterates over the collection of tracks. They are also
   * matched.
   */
  public void match(Playlist playlist) {
    for (int j = 1; j < playlist.size(); j++) {
      if (currentTrack != playlist.get(j)) {
        score = TrackCompatibility.getScore(currentTrack, playlist.get(j));
        if (score > bestScore && !addedTracks.contains(playlist.get(j))) {
          bestScore = score;
          bestMatch = playlist.get(j);
        }
      }
    }
  }

  @Override
  public Playlist sort(Playlist playlist) {

    setUpPlaylist(playlist);
    while (currentTrack != null) {
      addedTracks.add(currentTrack);
      bestMatch = null;
      bestScore = 0.0f;
      match(playlist);
      if (bestMatch == null) {
        break;
      }

      mixablePlaylist.add(bestMatch);
      currentTrack = bestMatch;
    }

    return mixablePlaylist;
  }
}
