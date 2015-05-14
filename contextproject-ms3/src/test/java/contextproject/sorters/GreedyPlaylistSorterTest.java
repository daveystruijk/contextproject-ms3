package contextproject.sorters;

import contextproject.models.Playlist;
import contextproject.sorters.GreedyPlaylistSorter;

import org.junit.Test;
import org.mockito.Mockito;

public class GreedyPlaylistSorterTest {

  private Playlist playlist = Mockito.mock(Playlist.class);

  @Test
  public void sortTest() {
    GreedyPlaylistSorter greedySort = new GreedyPlaylistSorter();
    greedySort.sort(playlist);
  }
}
