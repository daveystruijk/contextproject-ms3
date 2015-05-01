package contextproject.models;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BeatGridTest {

  private BeatGrid beatGrid;

  @Before
  public void setup() {
    beatGrid = new BeatGrid((long) (1875001), 128, 469); // 4000 beats; 468.75 ms / beat
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
  public void getOutroTest() {
    assertEquals(beatGrid.getOutro(), new BeatRange(3968, 32));
  }

}
