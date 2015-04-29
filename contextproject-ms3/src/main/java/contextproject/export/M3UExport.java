package contextproject.export;

import contextproject.models.Playlist;
import contextproject.models.Track;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class M3UExport {

  private Playlist playlist;
  private String playlistName;
  private String pathName;

  /**
   * Constructor of the export.
   * 
   * @param pl
   *          playlist.
   * @param name
   *          name of playlist.
   */
  public M3UExport(Playlist pl, String name) {
    playlist = pl;
    playlistName = name;
    createPathName();
  }

  /**
   * export method.
   */
  public void export() {
    File file = new File(pathName);

    try {
      PrintWriter writer = new PrintWriter(file);
      for (Track track : playlist) {
        writer.println(track.getPath());
      }
      writer.close();
    } catch (FileNotFoundException e) {
      System.out.println("File doesn't excist.");
    }
  }

  /**
   * Create the absolute path name of the playlist.
   */
  private void createPathName() {
    pathName = System.getProperty("user.dir").toString() + File.separator + "src" + File.separator
        + "main" + File.separator + "playlists" + File.separator + playlistName + ".m3u";
  }

  /**
   * Get the playlist.
   * 
   * @return Playlist.
   */
  public Playlist getPlaylist() {
    return playlist;
  }

  /**
   * Get the name of the playlist.
   * 
   * @return String.
   */
  public String getName() {
    return playlistName;
  }

  /**
   * Get the name of the absolute path.
   * 
   * @return String.
   */
  public String getAbsolutePath() {
    return pathName;
  }

}
