package contextproject.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class KeyTest {
  /**
   * test for situation without a key.
   */
  @Test(expected = IllegalArgumentException.class)
  public void keyNotExistTest() {
    new Key(null);
  }

  /**
   * test for situation with key Abm-> 1A.
   */
  @Test
  public void key1ATest() {
    Key key = new Key("Abm");
    assertEquals(key.getMusicalKeyString(), "Abm");
    assertEquals(key.getNormalizedKeyString(), "1A");
    assertEquals(key.getNeighborKeys().size(), 4);
    assertTrue(key.getNeighborKeys().contains("12A"));
  }
  
  /**
   * test for situation with key E -> 12B.
   */
  @Test
  public void keyETest() {
    Key key = new Key("E");
    assertEquals(key.getMusicalKeyString(), "E");
    assertEquals(key.getNormalizedKeyString(), "12B");
    assertEquals(key.getNeighborKeys().size(), 4);
    assertTrue(key.getNeighborKeys().contains("1B"));
  }
  
  /**
   * test direct normalized key input.
   */
  @Test
  public void keyNormalizedTest() {
    Key key = new Key("11B");
    assertEquals(key.getNormalizedKeyString(), "11B");
    assertEquals(key.getNeighborKeys().size(), 4);
    assertTrue(key.getNeighborKeys().contains("12B"));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void keyNormalizedInvalidTest() {
    new Key("40B");
  }
  
  @Test
  public void setTest() {
    Key key = new Key("Bb");
    key.setMusicalKeyString("test");
    key.setNormalizedKeyFlag("check");
    key.setNormalizedKeyNumber(10);
    assertTrue(key.getMusicalKeyString().equals("test"));
    assertTrue(key.getNormalizedKeyFlag().equals("check"));
    assertTrue(key.getNormalizedKeyNumber() == 10);
  }
}
