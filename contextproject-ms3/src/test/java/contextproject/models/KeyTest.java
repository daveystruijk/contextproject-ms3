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
    new MusicalKey(null);
  }

  /**
   * test for situation with null key.
   */
  @Test
  public void nullStartTest() {
    MusicalKey key = new MusicalKey();
    key.setNormalizedKeyNumber(0);
    assertEquals(key.getNormalizedKeyNumber(), 0);
    assertEquals(key.getNeighborKeys().size(), 0);
  }

  /**
   * test for situation with key Abm-> 1A.
   */
  @Test
  public void key1ATest() {
    MusicalKey key = new MusicalKey("Abm");
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
    MusicalKey key = new MusicalKey("E");
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
    MusicalKey key = new MusicalKey("11B");
    assertEquals(key.getNormalizedKeyString(), "11B");
    assertEquals(key.getNeighborKeys().size(), 4);
    assertTrue(key.getNeighborKeys().contains("12B"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void keyNormalizedInvalidTest() {
    new MusicalKey("40B");
  }

  @Test
  public void setTest() {
    MusicalKey key = new MusicalKey("Bb");
    key.setMusicalKeyString("test");
    key.setNormalizedKeyFlag("check");
    key.setNormalizedKeyNumber(10);
    assertTrue(key.getMusicalKeyString().equals("test"));
    assertTrue(key.getNormalizedKeyFlag().equals("check"));
    assertEquals(key.getNormalizedKeyNumber(), 10);
  }

  @Test
  public void zeroKeyTest() {
    MusicalKey key = new MusicalKey("Bb");
    key.setNormalizedKeyNumber(0);
    assertEquals(key.getNormalizedKeyString(), "0");
  }

  @Test
  public void setTestTwo() {
    MusicalKey key = new MusicalKey("C");
    assertEquals(key.getNormalizedKeyNumber(), 8);
    assertEquals(key.getNormalizedKeyFlag(), "B");
  }

  @Test
  public void setTestThree() {
    MusicalKey key = new MusicalKey("Db");
    assertEquals(key.getNormalizedKeyNumber(), 3);
    assertEquals(key.getNormalizedKeyFlag(), "B");
  }

  @Test
  public void setTestFour() {
    MusicalKey key = new MusicalKey("D");
    assertEquals(key.getNormalizedKeyNumber(), 10);
    assertEquals(key.getNormalizedKeyFlag(), "B");
  }

  @Test
  public void setTestFive() {
    MusicalKey key = new MusicalKey("Eb");
    assertEquals(key.getNormalizedKeyNumber(), 5);
    assertEquals(key.getNormalizedKeyFlag(), "B");
  }

  @Test
  public void setTestSix() {
    MusicalKey key = new MusicalKey("F");
    assertEquals(key.getNormalizedKeyNumber(), 7);
    assertEquals(key.getNormalizedKeyFlag(), "B");
  }

  @Test
  public void setTestSeven() {
    MusicalKey key = new MusicalKey("Gb");
    assertEquals(key.getNormalizedKeyNumber(), 2);
    assertEquals(key.getNormalizedKeyFlag(), "B");
  }

  @Test
  public void setTestEight() {
    MusicalKey key = new MusicalKey("G");
    assertEquals(key.getNormalizedKeyNumber(), 9);
    assertEquals(key.getNormalizedKeyFlag(), "B");
  }

  @Test
  public void setTestNine() {
    MusicalKey key = new MusicalKey("Ab");
    assertEquals(key.getNormalizedKeyNumber(), 4);
    assertEquals(key.getNormalizedKeyFlag(), "B");
  }

  @Test
  public void setTestTen() {
    MusicalKey key = new MusicalKey("Am");
    assertEquals(key.getNormalizedKeyNumber(), 8);
    assertEquals(key.getNormalizedKeyFlag(), "A");
  }

  @Test
  public void setTestEleven() {
    MusicalKey key = new MusicalKey("Bbm");
    assertEquals(key.getNormalizedKeyNumber(), 3);
    assertEquals(key.getNormalizedKeyFlag(), "A");
  }

  @Test
  public void setTestTwelve() {
    MusicalKey key = new MusicalKey("Bm");
    assertEquals(key.getNormalizedKeyNumber(), 10);
    assertEquals(key.getNormalizedKeyFlag(), "A");
  }

  @Test
  public void setTestThirteen() {
    MusicalKey key = new MusicalKey("Cm");
    assertEquals(key.getNormalizedKeyNumber(), 5);
    assertEquals(key.getNormalizedKeyFlag(), "A");
  }

  @Test
  public void setTestFourteen() {
    MusicalKey key = new MusicalKey("Dbm");
    assertEquals(key.getNormalizedKeyNumber(), 12);
    assertEquals(key.getNormalizedKeyFlag(), "A");
  }

  @Test
  public void setTestFifteen() {
    MusicalKey key = new MusicalKey("Dm");
    assertEquals(key.getNormalizedKeyNumber(), 7);
    assertEquals(key.getNormalizedKeyFlag(), "A");
  }

  @Test
  public void setTestSixteen() {
    MusicalKey key = new MusicalKey("Ebm");
    assertEquals(key.getNormalizedKeyNumber(), 2);
    assertEquals(key.getNormalizedKeyFlag(), "A");
  }

  @Test
  public void setTestSeventeen() {
    MusicalKey key = new MusicalKey("Em");
    assertEquals(key.getNormalizedKeyNumber(), 9);
    assertEquals(key.getNormalizedKeyFlag(), "A");
  }

  @Test
  public void setTestEighteen() {
    MusicalKey key = new MusicalKey("Fm");
    assertEquals(key.getNormalizedKeyNumber(), 4);
    assertEquals(key.getNormalizedKeyFlag(), "A");
  }

  @Test
  public void setTestNineteen() {
    MusicalKey key = new MusicalKey("Gbm");
    assertEquals(key.getNormalizedKeyNumber(), 11);
    assertEquals(key.getNormalizedKeyFlag(), "A");
  }

  @Test
  public void setTestTwenty() {
    MusicalKey key = new MusicalKey("Gm");
    assertEquals(key.getNormalizedKeyNumber(), 6);
    assertEquals(key.getNormalizedKeyFlag(), "A");
  }

  @Test
  public void toStringTest() {
    MusicalKey key = new MusicalKey("Fm");
    assertEquals(key.toString(), "4A");
  }
}
