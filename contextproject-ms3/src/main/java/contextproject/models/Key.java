package contextproject.models;

import com.mpatric.mp3agic.Mp3File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Key {
  private static Logger log = LogManager.getLogger(Key.class.getName());
  private int keyNumberPart;
  private String keyCharPart;
  private String keyMusicNotiation;

  /**
   * Get the key of a song from the meta data.
   * 
   * @param song
   *          song to get key from
   */
  public Key(Mp3File song) {
    if (song.hasId3v2Tag()) {
      keyMusicNotiation = song.getId3v2Tag().getKey();
      convertKey();
    } else {
      log.info(song.getFilename() + " has no Id3v2Tag");
    }
  }

  /**
   * Convert a key in common form to the camelot wheel form.
   */
  private void convertKey() {
    switch (keyMusicNotiation) {
      case "A" :
        keyNumberPart = 11;
        keyCharPart = "B";
        break;

      case "Bb" :
        keyNumberPart = 6;
        keyCharPart = "B";
        break;

      case "B" :
        keyNumberPart = 1;
        keyCharPart = "B";
        break;

      case "C" :
        keyNumberPart = 8;
        keyCharPart = "B";
        break;

      case "Dd" :
        keyNumberPart = 3;
        keyCharPart = "B";
        break;

      case "D" :
        keyNumberPart = 10;
        keyCharPart = "B";
        break;

      case "Eb" :
        keyNumberPart = 5;
        keyCharPart = "B";
        break;

      case "E" :
        keyNumberPart = 12;
        keyCharPart = "B";
        break;

      case "F" :
        keyNumberPart = 7;
        keyCharPart = "B";
        break;

      case "Gb" :
        keyNumberPart = 2;
        keyCharPart = "B";
        break;

      case "G" :
        keyNumberPart = 9;
        keyCharPart = "B";
        break;

      case "Ab" :
        keyNumberPart = 4;
        keyCharPart = "B";
        break;

      case "Am" :
        keyNumberPart = 8;
        keyCharPart = "A";
        break;

      case "Bbm" :
        keyNumberPart = 3;
        keyCharPart = "A";
        break;

      case "Bm" :
        keyNumberPart = 10;
        keyCharPart = "A";
        break;

      case "Cm" :
        keyNumberPart = 5;
        keyCharPart = "A";
        break;

      case "Dbm" :
        keyNumberPart = 12;
        keyCharPart = "A";
        break;

      case "Dm" :
        keyNumberPart = 7;
        keyCharPart = "A";
        break;

      case "Ebm" :
        keyNumberPart = 2;
        keyCharPart = "A";
        break;

      case "Em" :
        keyNumberPart = 9;
        keyCharPart = "A";
        break;

      case "Fm" :
        keyNumberPart = 4;
        keyCharPart = "A";
        break;

      case "Gbm" :
        keyNumberPart = 11;
        keyCharPart = "A";
        break;

      case "Gm" :
        keyNumberPart = 6;
        keyCharPart = "A";
        break;

      case "Abm" :
        keyNumberPart = 1;
        keyCharPart = "A";
        break;

      default :
        keyNumberPart = 0;
        keyCharPart = null;
        log.warn("Song has no key in ID3 information.");
        break;
    }
  }

  /**
   * returns the key in original music notation.
   * 
   * @return String
   */
  public String getMusicKey() {
    return keyMusicNotiation;
  }

  /**
   * returns the key in camelot wheel notation.
   * 
   * @return String
   */
  public String getCamelotWheelKey() {
    if (keyNumberPart != 0) {
      return keyNumberPart + keyCharPart;
    } else {
      return keyNumberPart + "";
    }

  }

  /**
   * Get the neighbor keys using camelot wheel.
   * 
   * @return ArrayList
   */
  public ArrayList<String> getNeighborKeys() {
    ArrayList<String> neighbors = new ArrayList<String>();

    if (keyNumberPart != 0) {
      for (int i = keyNumberPart - 1; i < keyNumberPart + 2; i++) {
        if (i == keyNumberPart) {
          neighbors.add(Math.round(((i + 12) % 12.1)) + "A");
          neighbors.add(Math.round(((i + 12) % 12.1)) + "B");
        } else {
          neighbors.add(Math.round(((i + 12) % 12.1)) + keyCharPart);
        }
      }
    }
    return neighbors;
  }

}
