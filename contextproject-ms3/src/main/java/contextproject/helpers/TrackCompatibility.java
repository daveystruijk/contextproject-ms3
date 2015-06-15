package contextproject.helpers;

import contextproject.models.MusicalKey;
import contextproject.models.Track;

/**
 * Track Compatibility Helper Class.
 * 
 * <p>
 * Calculates the overall compatibility between two tracks, taking into account: - BPM - Key -
 * Energy level
 * </p>
 */
public class TrackCompatibility {
  public static final float BPM_LINEAR_FACTOR = 0.01f; // * difference^2
  public static final float KEY_SCORE_INCOMPATIBLE = 0.7f;
  public static final float KEY_SCORE_COMPATIBLE = 1.0f;

  /**
   * Calculates the overall compatibility score between two tracks.
   * 
   * @param track1
   *          The first track
   * @param track2
   *          The second track
   * @return float THe compatibility score
   */
  public static double getScore(Track track1, Track track2) {
    double score = (getBpmScore(track1.getBpm(), track2.getBpm())
        + getEnergyScore(track1.getAverageEnergy(), track2.getAverageEnergy()) + 2 * getKeyScore(
        track1.getKey(), track2.getKey())) / 4.0f;
    return Math.max(0.0f, score);
  }

  /**
   * Calculates the compatibility of two bpm values.
   * 
   * @param d
   *          The first value
   * @param e
   *          The second value
   * @return float A score, ranging [0..1]
   */
  public static double getBpmScore(double bpm1, double bpm2) {
    double difference = Math.abs(bpm1 - bpm2);
    return (1.0f - (BPM_LINEAR_FACTOR * Math.pow(difference, 2)));
  }

  /**
   * Returns a fixed score based on whether the keys match or not.
   * 
   * @param key1
   *          The first key object
   * @param key2
   *          The second key object
   * @return float A score, ranging [0..1]
   */
  public static float getKeyScore(MusicalKey key1, MusicalKey key2) {
    boolean matchingKeys = key1.getNeighborKeys().contains(key2.getNormalizedKeyString());
    if (matchingKeys) {
      return KEY_SCORE_COMPATIBLE;
    } else {
      return KEY_SCORE_INCOMPATIBLE;
    }
  }

  /**
   * get Energy score.
   * 
   * @param energy1
   *          the first track energy
   * @param energy2
   *          the second track energy
   * @return double score
   */
  public static double getEnergyScore(double energy1, double energy2) {
    double difference = Math.abs(energy1 - energy2);
    return Math.pow((1.0f - difference), 2);
  }
}
