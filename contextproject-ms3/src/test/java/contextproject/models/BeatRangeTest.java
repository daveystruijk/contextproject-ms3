package contextproject.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class BeatRangeTest {

  private BeatRange beatRange;
  private BeatRange beatRange2;

  /**
   * Before running the test this method is done first to initialize the loader.
   */
  @Before
  public void initialize() {
    beatRange = new BeatRange(10, 10);
    beatRange2 = new BeatRange(11, 11);
  }

  @Test
  public void emptyConstructorTest() {
    BeatRange beatrange = new BeatRange();
    BeatRange beatrange1 = new BeatRange();
    assertTrue(beatrange.equals(beatrange1));
  }

  @Test
  public void getStartTest() {
    assertEquals(beatRange.getStart(), 10);
    assertEquals(beatRange2.getStart(), 11);
  }

  @Test
  public void getLengthTest() {
    assertEquals(beatRange.getLength(), 10);
    assertEquals(beatRange2.getLength(), 11);
  }

  @Test
  public void setStartTest() {
    beatRange.setStart(9);
    assertEquals(beatRange.getStart(), 9);
  }

  @Test
  public void setLenghtTest() {
    beatRange.setLength(9);
    assertEquals(beatRange.getLength(), 9);
  }

  @Test
  public void equalsTest() {
    assertTrue(beatRange.equals(beatRange));
    assertFalse(beatRange.equals(null));
    assertFalse(beatRange.equals(beatRange2));
  }
}
