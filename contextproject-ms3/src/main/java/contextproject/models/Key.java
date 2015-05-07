package contextproject.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Key implements Serializable {

  private static final long serialVersionUID = -3532632476298869674L;
  private int normalizedKeyNumber;
  private String normalizedKeyFlag;
  private String musicalKeyString;

  /**
   * Constructor without arguments.
   */
  public Key() {

  }

  /**
   * Get the key of a song from the meta data.
   * 
   * @param keyString
   *          String key from ID3 information
   */
  public Key(String keyString) throws IllegalArgumentException {
    if (keyString != null) {
      normalizeKey(keyString);
    } else {
      throw new IllegalArgumentException("Key should not be null");
    }
  }

  /**
   * Convert a key in common form to the camelot wheel form.
   */
  private void normalizeKey(String keyString) throws IllegalArgumentException,
      NumberFormatException {
    this.musicalKeyString = keyString;
    switch (keyString) {
      case "A" :
        normalizedKeyNumber = 11;
        normalizedKeyFlag = "B";
        break;

      case "Bb" :
        normalizedKeyNumber = 6;
        normalizedKeyFlag = "B";
        break;

      case "B" :
        normalizedKeyNumber = 1;
        normalizedKeyFlag = "B";
        break;

      case "C" :
        normalizedKeyNumber = 8;
        normalizedKeyFlag = "B";
        break;

      case "Dd" :
        normalizedKeyNumber = 3;
        normalizedKeyFlag = "B";
        break;

      case "D" :
        normalizedKeyNumber = 10;
        normalizedKeyFlag = "B";
        break;

      case "Eb" :
        normalizedKeyNumber = 5;
        normalizedKeyFlag = "B";
        break;

      case "E" :
        normalizedKeyNumber = 12;
        normalizedKeyFlag = "B";
        break;

      case "F" :
        normalizedKeyNumber = 7;
        normalizedKeyFlag = "B";
        break;

      case "Gb" :
        normalizedKeyNumber = 2;
        normalizedKeyFlag = "B";
        break;

      case "G" :
        normalizedKeyNumber = 9;
        normalizedKeyFlag = "B";
        break;

      case "Ab" :
        normalizedKeyNumber = 4;
        normalizedKeyFlag = "B";
        break;

      case "Am" :
        normalizedKeyNumber = 8;
        normalizedKeyFlag = "A";
        break;

      case "Bbm" :
        normalizedKeyNumber = 3;
        normalizedKeyFlag = "A";
        break;

      case "Bm" :
        normalizedKeyNumber = 10;
        normalizedKeyFlag = "A";
        break;

      case "Cm" :
        normalizedKeyNumber = 5;
        normalizedKeyFlag = "A";
        break;

      case "Dbm" :
        normalizedKeyNumber = 12;
        normalizedKeyFlag = "A";
        break;

      case "Dm" :
        normalizedKeyNumber = 7;
        normalizedKeyFlag = "A";
        break;

      case "Ebm" :
        normalizedKeyNumber = 2;
        normalizedKeyFlag = "A";
        break;

      case "Em" :
        normalizedKeyNumber = 9;
        normalizedKeyFlag = "A";
        break;

      case "Fm" :
        normalizedKeyNumber = 4;
        normalizedKeyFlag = "A";
        break;

      case "Gbm" :
        normalizedKeyNumber = 11;
        normalizedKeyFlag = "A";
        break;

      case "Gm" :
        normalizedKeyNumber = 6;
        normalizedKeyFlag = "A";
        break;

      case "Abm" :
        normalizedKeyNumber = 1;
        normalizedKeyFlag = "A";
        break;

      default :
        boolean foundNormalizedKey = setNormalizedString(keyString);
        if (!foundNormalizedKey) {
          throw new IllegalArgumentException("Key cannot be detected");
        }
    }
  }

  /**
   * Try to parse the key string if it is normalized already.
   * 
   * @param keyString
   *          The given key as text
   * @return Boolean whether the parsing succeeded or not
   * @throws NumberFormatException
   *           When matching fails
   */
  private boolean setNormalizedString(String keyString) throws NumberFormatException {
    Pattern pattern = Pattern.compile("([0-9]{1,2})([AB])");
    Matcher matcher = pattern.matcher(keyString);
    if (matcher.matches()) {
      int number = Integer.parseInt(matcher.group(1));
      String flag = matcher.group(2);
      if (number > 0 && number < 13 && (flag.equals("A") || flag.equals("B"))) {
        this.normalizedKeyNumber = number;
        this.normalizedKeyFlag = flag;
        return true;
      }
    }
    return false;
  }

  /**
   * returns the key in original music notation.
   * 
   * @return String
   */
  public String getMusicalKeyString() {
    return musicalKeyString;
  }

  /**
   * Set the musical key of the key.
   * 
   * @param mk
   *          musicalKey
   */
  public void setMusicalKeyString(String mk) {
    musicalKeyString = mk;
  }

  /**
   * returns the key in camelot wheel notation.
   * 
   * @return String
   */
  public String getNormalizedKeyString() {
    if (normalizedKeyNumber != 0) {
      return normalizedKeyNumber + normalizedKeyFlag;
    } else {
      return normalizedKeyNumber + "";
    }

  }

  /**
   * Get normalized key flag of the key.
   * 
   * @return Normalized key flag.
   */
  public String getNormalizedKeyFlag() {
    return normalizedKeyFlag;
  }

  /**
   * get normalizedkKey number of the key.
   * 
   * @return Normalized key number.
   */
  public int getNormalizedKeyNumber() {
    return normalizedKeyNumber;
  }

  /**
   * Set the normalized they flag of the key.
   * 
   * @param nk
   *          string representation of the normalized key flag.
   */
  public void setNormalizedKeyFlag(String nk) {
    normalizedKeyFlag = nk;
  }

  /**
   * Set the normalized they number of the key.
   * 
   * @param nk
   *          integer representation of the normalized key number.
   */
  public void setNormalizedKeyNumber(int nk) {
    normalizedKeyNumber = nk;
  }

  /**
   * Get the neighbor keys using camelot wheel.
   * 
   * @return ArrayList
   */
  public ArrayList<String> getNeighborKeys() {
    ArrayList<String> neighbors = new ArrayList<String>();

    if (normalizedKeyNumber != 0) {
      for (int i = normalizedKeyNumber - 1; i < normalizedKeyNumber + 2; i++) {
        if (i == normalizedKeyNumber) {
          neighbors.add(Math.round(((i + 12) % 12.1)) + "A");
          neighbors.add(Math.round(((i + 12) % 12.1)) + "B");
        } else {
          neighbors.add(Math.round(((i + 12) % 12.1)) + normalizedKeyFlag);
        }
      }
    }
    return neighbors;
  }

}
