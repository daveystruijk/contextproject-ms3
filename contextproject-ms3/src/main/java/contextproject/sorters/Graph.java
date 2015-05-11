package contextproject.sorters;

import contextproject.helpers.TrackCompatibility;
import contextproject.models.Playlist;
import contextproject.models.Track;

import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

public class Graph extends DefaultDirectedWeightedGraph<Track, DefaultWeightedEdge> {

  private Playlist playlist;

  public Graph(Playlist playlist) {
    super(DefaultWeightedEdge.class);
    this.playlist = playlist;
    initialize();
  }

  private void initialize() {
    createVertices();
    createEdges();
  }

  private void createVertices() {
    for (Track track : playlist) {
      this.addVertex(track);
    }
  }

  private void createEdges() {
    for (int i = 0; i < playlist.size() - 1; i++) {
      Track currentTrack = playlist.get(i);
      for (int j = i + 1; j < playlist.size(); j++) {
        Track tempTrack = playlist.get(j);
        int score = getConvertedScore(TrackCompatibility.getScore(currentTrack, tempTrack));
        System.out.println(currentTrack + "  +  " + tempTrack + "   =   " + score);
        this.setEdgeWeight(this.addEdge(currentTrack, tempTrack), score);

        this.setEdgeWeight(this.addEdge(tempTrack, currentTrack), score);
      }
    }
  }
  
  private int getConvertedScore(double score) {
    return (int) Math.pow((1.0 - score), -1);
  }

  public String toString() {
    String res;
    res = "Vertices:" + this.vertexSet().toString() + "\n" + "edges:" + this.edgeSet().toString()
        + "\n" + "size of vertices: " + this.vertexSet().size() + "\n" + "size of edges: "
        + this.edgeSet().size();

    return res;
  }
}
