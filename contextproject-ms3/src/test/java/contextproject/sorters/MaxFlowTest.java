package contextproject.sorters;

import static org.junit.Assert.assertEquals;

import contextproject.models.MusicalKey;
import contextproject.models.Playlist;
import contextproject.models.Track;

import org.junit.Before;
import org.junit.Test;

public class MaxFlowTest {

  private Graph graph;
  private Playlist playlist;
  private Track track;
  private Track track2;
  private Track track3;

  /**
   * set up variables.
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
    graph = new Graph(playlist);

  }

  @Test
  public void test() {
    MaxFlow max = new MaxFlow(graph);
    assertEquals(max.getOptimalPath().size(), 2);
  }

  @Test
  public void testMoreTracks() {
    playlist.add(track3);
    Graph biggerGraph = new Graph(playlist);
    MaxFlow max = new MaxFlow(biggerGraph);
    assertEquals(max.getOptimalPath().size(), 3);
  }

  @Test
  public void hugeTest() {
    playlist.add(track3);
    Track track4 = new Track();
    track4.setKey(new MusicalKey("11A"));
    track4.setPath("d");
    track4.setBpm(120);
    playlist.add(track4);
    Track track5 = new Track();
    track5.setKey(new MusicalKey("4A"));
    track5.setBpm(125);
    track5.setPath("e");
    playlist.add(track5);
    Track track6 = new Track();
    track6.setKey(new MusicalKey("7A"));
    track6.setBpm(120);
    track6.setPath("f");
    playlist.add(track6);
    Track track7 = new Track();
    track7.setBpm(125);
    track7.setKey(new MusicalKey("6A"));
    track7.setPath("g");
    track7.setBpm(120);
    playlist.add(track7);
    Track track8 = new Track();
    track8.setBpm(125);
    track8.setKey(new MusicalKey("9A"));
    track8.setPath("h");
    playlist.add(track8);
    Track track9 = new Track();
    track9.setBpm(125);
    track9.setKey(new MusicalKey("10A"));
    track9.setPath("i");
    playlist.add(track9);
    Track track10 = new Track();
    track10.setBpm(125);
    track10.setKey(new MusicalKey("5A"));
    track10.setPath("j");
    playlist.add(track10);
    Track track11 = new Track();
    track11.setBpm(125);
    track11.setKey(new MusicalKey("6A"));
    track11.setPath("k");
    playlist.add(track11);
    Graph hugeGraph = new Graph(playlist);
    MaxFlow biggestFlow = new MaxFlow(hugeGraph);
    assertEquals(biggestFlow.getOptimalPath().size(),9);
  }
}
