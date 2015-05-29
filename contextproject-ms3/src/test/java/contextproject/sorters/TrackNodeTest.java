package contextproject.sorters;

import static org.junit.Assert.assertEquals;

import contextproject.models.Key;
import contextproject.models.Track;

import org.junit.Before;
import org.junit.Test;

public class TrackNodeTest {
  private Track track1;
  private Track track2;
  private Track track3;

  private TrackNode tracknode1;
  private TrackNode tracknode2;
  private TrackNode tracknode3;

  /**
   * Initialize all variables for TrackNodeTest.
   */
  @Before
  public void initialize() {
    track1 = new Track();
    track1.setBpm(120.0);
    track1.setKey(new Key("11B"));
    track1.setPath("a");
    track1.setTitle("succesful test");

    track2 = new Track();
    track2.setBpm(124.0);
    track2.setKey(new Key("12B"));
    track2.setPath("b");

    track3 = new Track();
    track3.setBpm(128.0);
    track3.setKey(new Key("1A"));
    track3.setPath("c");

    tracknode1 = new TrackNode(track1);
    tracknode2 = new TrackNode(track2);
    tracknode3 = new TrackNode(track3, 1.0);
  }

  @Test
  public void constructorTest() {
    assertEquals(track1, tracknode1.getTrack());
  }

  @Test
  public void constructorScoreTest() {
    assertEquals(0, (int) tracknode1.getScore());
    assertEquals(1, (int) tracknode3.getScore());
  }

  @Test
  public void scoreTest() {
    assertEquals(0, (int) tracknode2.getScore());
    tracknode2.setScore(2.0);
    assertEquals(2, (int) tracknode2.getScore());
  }

  @Test
  public void parentTest() {
    assertEquals(false, tracknode2.hasParent());
    tracknode2.setParent(tracknode1);
    assertEquals(true, tracknode2.hasParent());
    assertEquals(tracknode1, tracknode2.getParent());
  }

  @Test
  public void equalsTest() {
    assertEquals(true, tracknode2.equals(new TrackNode(track2)));
    assertEquals(false, tracknode2.equals(new TrackNode(track3)));
    assertEquals(false, tracknode2.equals(track2));
  }

  @Test
  public void childrenTest() {
    assertEquals(false, tracknode1.containsChild(tracknode2));
    assertEquals(0, tracknode1.getChildren().size());
    tracknode1.addChild(tracknode2);
    assertEquals(true, tracknode1.containsChild(tracknode2));
  }

  @Test
  public void toStringTest() {
    assertEquals("succesful test", tracknode1.toString());
  }
}
