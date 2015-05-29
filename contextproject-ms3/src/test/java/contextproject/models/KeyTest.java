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
   * test for situation with null key.
   */
  @Test
  public void nullStartTest() {
    Key key = new Key();
    key.setNormalizedKeyNumber(0);
    assertEquals(key.getNormalizedKeyNumber(), 0);
    assertEquals(key.getNeighborKeys().size(), 0);
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
    assertEquals(key.getNormalizedKeyNumber(), 10);
  }

  @Test
  public void zeroKeyTest() {
    Key key = new Key("Bb");
    key.setNormalizedKeyNumber(0);
    assertEquals(key.getNormalizedKeyString(), "0");
  }

  @Test
  public void setTestTwo() {
    Key key = new Key("C");
    assertEquals(key.getNormalizedKeyNumber(), 8);
    assertEquals(key.getNormalizedKeyFlag(), "B");
  }

  @Test
  public void setTestThree() {
    Key key = new Key("Db");
    assertEquals(key.getNormalizedKeyNumber(), 3);
    assertEquals(key.getNormalizedKeyFlag(), "B");
  }

  @Test
  public void setTestFour() {
    Key key = new Key("D");
    assertEquals(key.getNormalizedKeyNumber(), 10);
    assertEquals(key.getNormalizedKeyFlag(), "B");
  }

  @Test
  public void setTestFive() {
    Key key = new Key("Eb");
    assertEquals(key.getNormalizedKeyNumber(), 5);
    assertEquals(key.getNormalizedKeyFlag(), "B");
  }

  @Test
  public void setTestSix() {
    Key key = new Key("F");
    assertEquals(key.getNormalizedKeyNumber(), 7);
    assertEquals(key.getNormalizedKeyFlag(), "B");
  }

  @Test
  public void setTestSeven() {
    Key key = new Key("Gb");
    assertEquals(key.getNormalizedKeyNumber(), 2);
    assertEquals(key.getNormalizedKeyFlag(), "B");
  }

  @Test
  public void setTestEight() {
    Key key = new Key("G");
    assertEquals(key.getNormalizedKeyNumber(), 9);
    assertEquals(key.getNormalizedKeyFlag(), "B");
  }

  @Test
  public void setTestNine() {
    Key key = new Key("Ab");
    assertEquals(key.getNormalizedKeyNumber(), 4);
    assertEquals(key.getNormalizedKeyFlag(), "B");
  }

  @Test
  public void setTestTen() {
    Key key = new Key("Am");
    assertEquals(key.getNormalizedKeyNumber(), 8);
    assertEquals(key.getNormalizedKeyFlag(), "A");
  }

  @Test
  public void setTestEleven() {
    Key key = new Key("Bbm");
    assertEquals(key.getNormalizedKeyNumber(), 3);
    assertEquals(key.getNormalizedKeyFlag(), "A");
  }

  @Test
  public void setTestTwelve() {
    Key key = new Key("Bm");
    assertEquals(key.getNormalizedKeyNumber(), 10);
    assertEquals(key.getNormalizedKeyFlag(), "A");
  }

  @Test
  public void setTestThirteen() {
    Key key = new Key("Cm");
    assertEquals(key.getNormalizedKeyNumber(), 5);
    assertEquals(key.getNormalizedKeyFlag(), "A");
  }

  @Test
  public void setTestFourteen() {
    Key key = new Key("Dbm");
    assertEquals(key.getNormalizedKeyNumber(), 12);
    assertEquals(key.getNormalizedKeyFlag(), "A");
  }

  @Test
  public void setTestFifteen() {
    Key key = new Key("Dm");
    assertEquals(key.getNormalizedKeyNumber(), 7);
    assertEquals(key.getNormalizedKeyFlag(), "A");
  }

  @Test
  public void setTestSixteen() {
    Key key = new Key("Ebm");
    assertEquals(key.getNormalizedKeyNumber(), 2);
    assertEquals(key.getNormalizedKeyFlag(), "A");
  }

  @Test
  public void setTestSeventeen() {
    Key key = new Key("Em");
    assertEquals(key.getNormalizedKeyNumber(), 9);
    assertEquals(key.getNormalizedKeyFlag(), "A");
  }

  @Test
  public void setTestEighteen() {
    Key key = new Key("Fm");
    assertEquals(key.getNormalizedKeyNumber(), 4);
    assertEquals(key.getNormalizedKeyFlag(), "A");
  }

  @Test
  public void setTestNineteen() {
    Key key = new Key("Gbm");
    assertEquals(key.getNormalizedKeyNumber(), 11);
    assertEquals(key.getNormalizedKeyFlag(), "A");
  }

  @Test
  public void setTestTwenty() {
    Key key = new Key("Gm");
    assertEquals(key.getNormalizedKeyNumber(), 6);
    assertEquals(key.getNormalizedKeyFlag(), "A");
  }

  @Test
  public void toStringTest() {
    Key key = new Key("Fm");
    assertEquals(key.toString(), "4A");
  }
}
