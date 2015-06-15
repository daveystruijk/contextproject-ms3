package contextproject.sorters;

import static org.junit.Assert.assertEquals;

import contextproject.models.MusicalKey;
import contextproject.models.Playlist;
import contextproject.models.Track;

import org.junit.Before;
import org.junit.Test;

public class MaximumFlowPlaylistSorterTest {

  private Playlist playlist;
  private Track track;
  private Track track2;
  private Track track3;

  /**
   * Set up variables.
   */
  @Before
  public void setUp() {
    track = new Track();
    track.setBpm(120);
    track.setKey(new MusicalKey("11B"));
    track.setPath("a");
    track2 = new Track();
    track2.setBpm(125);
    track2.setKey(new MusicalKey("7B"));
    track2.setPath("b");
    track3 = new Track();
    track3.setBpm(122);
    track3.setKey(new MusicalKey("9B"));
    track3.setPath("c");
    playlist = new Playlist();
    playlist.add(track);
    playlist.add(track2);
    playlist.add(track3);
  }

  @Test
  public void test() {
    MaximumFlowPlaylistSorter max = new MaximumFlowPlaylistSorter();
    assertEquals(max.sort(playlist).size(), 3);
  }

}
