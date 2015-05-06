package contextproject.models;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BeatGridTest {

  private BeatGrid beatGrid;
  private BeatGrid beatGrid2;
  private BeatGrid beatGrid3;

  /**
   * @Before.
   */
  @Before
  public void setup() {
    beatGrid = new BeatGrid((long) (1875001), 128, 469, 0, 0, 0, 0); // 4000 beats; 468.75 ms / beat
    beatGrid2 = new BeatGrid((long) (1875001), 128, 469, 12, 0, 100, 0); // 4000 beats; 468.75 ms /
                                                                         // beat
    beatGrid3 = new BeatGrid((long) (1875001), 128, 469, 12, 20, 100, 14); // 4000 beats; 468.75 ms
                                                                           // / beat
  }

  @Test
  public void getBeatTimeTest() {
    assertEquals(beatGrid.getBeatTime(20), (long) (469 + (double) 19
        * ((double) 60000 / (double) 128)));
  }

  @Test
  public void getBeatTimeFailTest() {
    assertEquals(beatGrid.getBeatTime(5000), -1);
  }

  @Test
  public void getIntroTest() {
    assertEquals(beatGrid.getIntro(), new BeatRange(1, 32));
  }

  @Test
  public void getIntroFalseTest() {
    assertEquals(beatGrid2.getIntro(), new BeatRange(1, 32));
  }

  @Test
  public void getIntroTrueTest() {
    assertEquals(beatGrid3.getIntro(), new BeatRange(12, 20));
  }

  @Test
  public void getOutroTest() {
    assertEquals(beatGrid.getOutro(), new BeatRange(3968, 32));
  }

  @Test
  public void getOutroFalseTest() {
    assertEquals(beatGrid2.getOutro(), new BeatRange(3968, 32));
  }

  @Test
  public void getOutroTrueTest() {
    assertEquals(beatGrid3.getOutro(), new BeatRange(100, 14));
  }

}
