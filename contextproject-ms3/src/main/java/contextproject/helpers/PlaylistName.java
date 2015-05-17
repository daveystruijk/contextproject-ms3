package contextproject.helpers;

public class PlaylistName {
  /**
   * get the name of the folder.
   * 
   * @param path
   *          the ablosute path of the folder.
   * @return the name of the folder.
   */
  public static String getName(String path) {
    String[] temp;
    String delimiter = "/";
    temp = path.split(delimiter);
    int size = temp.length;
    String name = temp[(size - 1)];
    System.out.println(name.replaceAll(" ", "_").toLowerCase());
    return name.replaceAll(" ", "_").toLowerCase();
  }
}
