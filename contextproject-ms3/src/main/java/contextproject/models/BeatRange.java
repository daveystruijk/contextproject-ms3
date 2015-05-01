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

  @Override
  public boolean equals(Object other) {
    if (other instanceof BeatRange) {
      if (this.start == ((BeatRange) other).start && this.length == ((BeatRange) other).length) {
        return true;
      }
    }
    return false;
  }
}
