package contextproject.sorters;

import contextproject.models.Playlist;

public interface PlaylistSorter {
  
  /**
   * Sort the tracks of a playlist so that they mix well together.
   * 
   * @return Playlist object
   */
  public Playlist sort(Playlist playlist);
}
