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

}
