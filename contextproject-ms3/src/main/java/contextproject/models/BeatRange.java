package contextproject.models;

import java.io.Serializable;

public class BeatRange implements Serializable {

  private static final long serialVersionUID = -4211151573599395503L;
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

  /**
   * Constructor without arguments.
   */
  public BeatRange() {

  }

  /**
   * Method to check if two beat ranges are equals to each other.
   */
  @Override
  public boolean equals(Object other) {
    if (other instanceof BeatRange) {
      if (this.start == ((BeatRange) other).start && this.length == ((BeatRange) other).length) {
        return true;
      }
    }
    return false;
  }

  /**
   * Get the start of the beat range.
   * 
   * @return start of the beat range.
   */
  public int getStart() {
    return start;
  }

  /**
   * Get the length of the beat range.
   * 
   * @return the length of the beat range.
   */
  public int getLength() {
    return length;
  }

  /**
   * Set the start of the beat range.
   * 
   * @param st
   *          start of the beat range.
   */
  public void setStart(int st) {
    start = st;
  }

  /**
   * Set the length of the beat range.
   * 
   * @param le
   *          length of the beat range.
   */
  public void setLength(int le) {
    length = le;
  }
}
