package contextproject.sorters;

import static org.junit.Assert.assertEquals;

import contextproject.models.Key;
import contextproject.models.Playlist;
import contextproject.models.Track;

import org.junit.Before;
import org.junit.Test;

public class MaxFlowTest {

  private Track track;
  private Track track2;
  private Playlist playlist;

  /**
   * This unit test is present to test our MaxFlow algorithm.
   */

  @Before
  public void setUp() {
    track = new Track();
    track2 = new Track();
    track.setBpm(120);
    track2.setBpm(125);
    track.setKey(new Key("11A"));
    track2.setKey(new Key("9A"));
    playlist = new Playlist();
    playlist.add(track);
    playlist.add(track2);
  }

  @Test
  public void test() {
    Graph graph = new Graph(playlist);

  }

}
