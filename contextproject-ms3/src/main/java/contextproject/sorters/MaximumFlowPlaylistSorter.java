package contextproject.sorters;

import contextproject.models.Playlist;

public class MaximumFlowPlaylistSorter implements PlaylistSorter{

  @Override
  public Playlist sort(Playlist playlist) {
    System.out.println("start sorting");
    Graph graph = new Graph(playlist);
    System.out.println("created graph");
    MaxFlow mx = new MaxFlow(graph);
    System.out.println("created maxflow");
    return (Playlist) mx.getOptimalPath();
  }

}
