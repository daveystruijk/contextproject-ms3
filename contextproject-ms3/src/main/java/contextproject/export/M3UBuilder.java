package contextproject.export;

import contextproject.models.Playlist;
import contextproject.models.Track;

public class M3UBuilder {

  private Playlist playlist;

  /**
   * Constructor of the M3U Builder.
   * 
   * @param pl
   *          corresponding playlist.
   */
  public M3UBuilder(Playlist pl) {
    playlist = pl;
  }

  /**
   * StringBuilder for a M3U file extension
   * 
   * @return string representation of M3U file.
   */
  public String build() {
    StringBuilder sb = new StringBuilder();
    for (Track track : playlist) {
      sb.append(track.getPath() + "\n");
    }
    return sb.toString();
  }

}
