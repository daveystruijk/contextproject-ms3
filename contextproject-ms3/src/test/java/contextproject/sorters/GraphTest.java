package contextproject.sorters;

import static org.junit.Assert.assertEquals;

import contextproject.models.Key;
import contextproject.models.Playlist;
import contextproject.models.Track;

import org.junit.Before;
import org.junit.Test;

public class GraphTest {

  private Playlist playlist;
  private Track track1;
  private Track track2;
  private Track track3;
  private Graph graph;

  /**
   * Initialize all needed variables for graph test.
   */
  @Before
  public void initialize() {
    playlist = new Playlist();
    track1 = new Track();
    track1.setBpm(120.0);
    track1.setKey(new Key("11B"));
    track1.setPath("a");

    track2 = new Track();
    track2.setBpm(124.0);
    track2.setKey(new Key("12B"));
    track2.setPath("b");

    track3 = new Track();
    track3.setBpm(128.0);
    track3.setKey(new Key("1A"));
    track3.setPath("c");

    playlist.add(track1);
    playlist.add(track2);
    playlist.add(track3);

    graph = new Graph(playlist);
  }

  @Test
  public void verticestest() {
    assertEquals(playlist.size(), graph.vertexSet().size());
  }

  @Test
  public void edgesTest() {
    assertEquals(track1, graph.getEdge(track1, track2).getEdgeSource());
    assertEquals(track2, graph.getEdge(track3, track2).getEdgeTarget());
    assertEquals(6, graph.edgeSet().size());
  }
  
  @Test
  public void convertScoreTest() {
    assertEquals(12, (int) graph.getEdgeWeight(graph.getEdge(track1, track2))); // (1-(11/12))^-1
  }
  
  @Test
  public void toStringTest() {
    String test = "Vertices:" + graph.vertexSet().toString() + "\n" + "edges:"
        + graph.edgeSet().toString() + "\n" + "size of vertices: "
        + graph.vertexSet().size() + "\n" + "size of edges: "
        + graph.edgeSet().size();
    assertEquals(test, graph.toString());
  }

}
