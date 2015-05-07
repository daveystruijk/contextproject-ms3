package contextproject.loaders;

import contextproject.models.Playlist;
/**
 * Interface for the loaders to ensure it has the right functionality.
 *
 */
public interface PlaylistLoader {
  /**
   * Main method to load all the names of the mp3 files.
   * 
   * @return list of names of the mp3 files
   */
  public Playlist load();

}
