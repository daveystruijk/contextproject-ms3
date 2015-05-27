package contextproject.sorters;

import static org.junit.Assert.assertEquals;

import contextproject.models.Key;
import contextproject.models.Playlist;
import contextproject.models.Track;
import contextproject.sorters.GreedyPlaylistSorter;

import org.junit.Before;
import org.junit.Test;

public class GreedyPlaylistSorterTest {

  private Playlist playlist = new Playlist();
  private Track track = new Track();
  private Track track2 = new Track();
  private Track track3 = new Track();
  private Track track4 = new Track();
  private Track track5 = null;
  /**
   * Setting up playlist that can be sorted.
   */
  @Before
  public void setUp() {
    playlist.setName("testList");
    track.setBpm(128);
    track2.setBpm(128);
    track3.setBpm(126);
    track.setKey(new Key("12A"));
    track2.setKey(new Key("9A"));
    track3.setKey(new Key("4A"));
    playlist.add(track);
    playlist.add(track2);

  }

  @Test
  public void sortNoMatchTrackTest() {
    GreedyPlaylistSorter greedySort = new GreedyPlaylistSorter();
    greedySort.sort(playlist);
  }

}
