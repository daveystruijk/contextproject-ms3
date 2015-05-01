package contextproject.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BeatGridTest {

  @Test
  public void beatGridTest() {
    BeatGrid beatGrid = new BeatGrid((long) (1875001), 128, 469); //4000 beats; 468.75 ms / beat
    assertEquals(beatGrid.getBeatTime(20), (long) (469 + (double) 19
        * ((double) 60000 / (double) 128)));
    assertEquals(beatGrid.getIntro(), new BeatRange(1, 32));
    assertEquals(beatGrid.getOutro(), new BeatRange(3968, 32));
    assertEquals(beatGrid.getBeatTime(5000),-1);
  }

}
