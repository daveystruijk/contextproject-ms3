package contextproject.models;

public class BeatGrid {

  private long length;
  private float bpm;
  private long firstBeat;
  private float timePerBeat;
  private int maxBeat;
  private BeatRange intro;
  private BeatRange outro;

  /**
   * BeatGrid constructor.
   * 
   * @param length
   *          Long
   * @param bpm
   *          float
   * @param firstBeat
   *          Long
   */
  public BeatGrid(Long length, float bpm, long firstBeat) {
    this.length = length;
    this.bpm = bpm;
    this.firstBeat = firstBeat;
    this.timePerBeat = (60000 / this.bpm);
    this.maxBeat = (int) ((this.length - this.firstBeat) / this.timePerBeat) + 1;
    this.intro = new BeatRange(1, 32);
    this.outro = new BeatRange(maxBeat - 32, 32);
  }

  /**
   * Returns the time of the nth beat. Note: beat count start a beat 1.
   * 
   * @param beat
   *          int nth beat
   * @return Long time of beat is ms
   */
  public long getBeatTime(int beat) {
    if (beat <= maxBeat) {
      return (long) (firstBeat + (timePerBeat * (beat - 1)));
    } else {
      return -1;
    }
  }

  /**
   * Getter for beat range intro.
   * 
   * @return BeatRange
   */
  public BeatRange getIntro() {
    return intro;
  }

  /**
   * Getter for beat range outro.
   * 
   * @return BeatRange
   */
  public BeatRange getOutro() {
    return outro;
  }

}
