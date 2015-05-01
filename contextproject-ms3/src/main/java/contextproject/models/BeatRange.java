package contextproject.models;

public class BeatRange {
  public int start;
  public int length;

  /**
   * Constructor for a beat range.
   * 
   * @param start
   *          int start beat
   * @param length
   *          int range length
   */
  public BeatRange(int start, int length) {
    this.start = start;
    this.length = length;
  }
}
