package contextproject.helpers;

import contextproject.models.Key;
import contextproject.models.Track;

/**
 * Track Compatibility Helper Class.
 * 
 * <p>Calculates the overall compatibility between
 * two tracks, taking into account:
 *    - BPM
 *    - Key
 *    - Energy level</p>
 */
public class TrackCompatibility {
  public static final float BPM_LINEAR_FACTOR = 0.2f; // per 1 bpm difference
  public static final float KEY_SCORE_INCOMPATIBLE = 0.5f;
  public static final float KEY_SCORE_COMPATIBLE = 1.0f;
  
  /**
   * Calculates the overall compatibility score between two tracks.
   * @param track1 The first track
   * @param track2 The second track
   * @return float THe compatibility score
   */
  public static float getScore(Track track1, Track track2) {
    float score = (
          getBpmScore(track1.getBpm(), track2.getBpm())
          +
          getKeyScore(track2.getKey(), track2.getKey())
          / 2
        );
    return Math.max(0.0f, score);
  }
  
  /**
   * Calculates the compatibility of two bpm values.
   * @param bpm1 The first value
   * @param bpm2 The second value
   * @return float A score, ranging [0..1]
   */
  public static float getBpmScore(float bpm1, float bpm2) {
    float difference = Math.abs(bpm1 - bpm2);
    return 1.0f - (BPM_LINEAR_FACTOR * difference);
  }
  
  /**
   * Returns a fixed score based on whether the keys match or not.
   * @param key1 The first key object
   * @param key2 The second key object
   * @return float A score, ranging [0..1]
   */
  public static float getKeyScore(Key key1, Key key2) {
    boolean matchingKeys = key1.getNeighborKeys().contains(key2);
    if (matchingKeys) {
      return KEY_SCORE_COMPATIBLE;
    } else {
      return KEY_SCORE_INCOMPATIBLE;
    }
  }
}
