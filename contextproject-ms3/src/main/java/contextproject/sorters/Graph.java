package contextproject.sorters;

import contextproject.helpers.TrackCompatibility;
import contextproject.models.Playlist;
import contextproject.models.Track;

import org.jgrapht.graph.DefaultDirectedWeightedGraph;

public class Graph extends DefaultDirectedWeightedGraph<Track, WeightedEdge> {

  private static final long serialVersionUID = 1L;
  private Playlist playlist;

  /**
   * Graph constructor.
   * @param playlist PlayList
   */
  public Graph(Playlist playlist) {
    super(WeightedEdge.class);
    this.playlist = playlist;
    initialize();
  }

  /**
   * Initialize graph.
   */
  private void initialize() {
    createVertices();
    createEdges();
  }

  /**
   * Create vertices.
   */
  private void createVertices() {
    for (Track track : playlist) {
      this.addVertex(track);
    }
  }

  /**
   * Create edges.
   */
  private void createEdges() {
    for (int i = 0; i < playlist.size() - 1; i++) {
      Track currentTrack = playlist.get(i);
      for (int j = i + 1; j < playlist.size(); j++) {
        Track tempTrack = playlist.get(j);
        int score = getConvertedScore(TrackCompatibility.getScore(currentTrack,
            tempTrack));
        this.setEdgeWeight(this.addEdge(currentTrack, tempTrack), score);
        this.setEdgeWeight(this.addEdge(tempTrack, currentTrack), score);
      }
    }
  }

  /**
   * Get converted score.
   * @param score Double
   * @return Integer
   */
  private int getConvertedScore(double score) {
    return (int) Math.pow((1.0 - score), -1);
  }

  /**
   * Overrides normal toString method.
   */
  public String toString() {
    String res;
    res = "Vertices:" + this.vertexSet().toString() + "\n" + "edges:"
        + this.edgeSet().toString() + "\n" + "size of vertices: "
        + this.vertexSet().size() + "\n" + "size of edges: "
        + this.edgeSet().size();

    return res;
  }
}
