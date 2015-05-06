package contextproject.models;

public class BeatGrid {

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
  public BeatGrid(Long length, float bpm, long firstBeat, int startBeatIntro, int introBeatLength,
      int startBeatOutro, int outroBeatLength) {
    this.firstBeat = firstBeat;
    this.timePerBeat = (60000 / bpm);
    this.maxBeat = (int) ((length - this.firstBeat) / this.timePerBeat) + 1;
    if (startBeatIntro != 0 && introBeatLength != 0) {
      this.intro = new BeatRange(startBeatIntro, introBeatLength);
    } else {
      this.intro = new BeatRange(1, 32);
    }
    if (startBeatOutro != 0 && outroBeatLength != 0) {
      this.outro = new BeatRange(startBeatOutro, outroBeatLength);
    } else {
      this.outro = new BeatRange(maxBeat - 32, 32);
    }
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
