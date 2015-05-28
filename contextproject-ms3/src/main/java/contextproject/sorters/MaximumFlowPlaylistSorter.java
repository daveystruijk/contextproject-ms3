package contextproject.sorters;

import contextproject.models.Playlist;

public class MaximumFlowPlaylistSorter implements PlaylistSorter{

  @Override
  public Playlist sort(Playlist playlist) {
    Graph graph = new Graph(playlist);
    MaxFlow mx = new MaxFlow(graph);
    return (Playlist) mx.getOptimalPath();
  }

}
