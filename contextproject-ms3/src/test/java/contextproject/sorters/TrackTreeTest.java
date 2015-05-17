package contextproject.sorters;

import static org.junit.Assert.assertEquals;

import contextproject.models.Key;
import contextproject.models.Playlist;
import contextproject.models.Track;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


public class TrackTreeTest {
  private Track track1;
  private Track track2;
  private Track track3;
  private Track track4;

  private TrackNode tracknode1;
  private TrackNode tracknode2;
  private TrackNode tracknode3;
  private TrackNode tracknode4;
  private TrackNode tracknode5;
  private TrackNode tracknode6;

  private TrackTree trackTree;

  /**
   * Initialize all variables for TrackTreeTest.
   */
  @Before
  public void initialize() {
    track1 = new Track();
    track1.setBpm(120.0);
    track1.setKey(new Key("11B"));
    track1.setPath("a");
    track1.setTitle("Track1");

    track2 = new Track();
    track2.setBpm(124.0);
    track2.setKey(new Key("12B"));
    track2.setPath("b");
    track2.setTitle("Track2");

    track3 = new Track();
    track3.setBpm(128.0);
    track3.setKey(new Key("1A"));
    track3.setPath("c");
    track3.setTitle("Track3");

    track4 = new Track();
    track4.setBpm(130.0);
    track4.setKey(new Key("2A"));
    track4.setPath("d");
    track4.setTitle("Track4");

    tracknode1 = new TrackNode(track1);
    tracknode2 = new TrackNode(track2, 1.0);
    tracknode3 = new TrackNode(track2, 3.0);
    tracknode4 = new TrackNode(track3, 4.0);
    tracknode5 = new TrackNode(track3, 5.0);
    tracknode6 = new TrackNode(track4, 100.0);

    tracknode1.addChild(tracknode2);
    tracknode2.setParent(tracknode1);

    tracknode1.addChild(tracknode3);
    tracknode3.setParent(tracknode1);

    tracknode2.addChild(tracknode4);
    tracknode4.setParent(tracknode2);
    tracknode2.addChild(tracknode5);
    tracknode5.setParent(tracknode2);

    trackTree = new TrackTree(tracknode1);
  }

  @Test
  public void constructorTest() {
    assertEquals(tracknode1, trackTree.getRoot());
  }

  @Test
  public void hasAncestortest() {
    tracknode5.addChild(tracknode6);
    tracknode6.setParent(tracknode5);
    assertEquals(true, trackTree.hasAncestor(tracknode5, tracknode1));
    assertEquals(false, trackTree.hasAncestor(tracknode5, tracknode6));
  }

  @Test
  public void optimalPathTest() {
    Playlist result = new Playlist();
    result.add(track1);
    result.add(track2);
    result.add(track3);
    ArrayList<TrackNode> endPaths = new ArrayList<TrackNode>();
    endPaths.add(tracknode4);
    endPaths.add(tracknode5);
    assertEquals(result, trackTree.optimalPath(endPaths));

    tracknode3.addChild(tracknode6);
    tracknode6.setParent(tracknode3);
    endPaths.add(tracknode6);
    result.remove(track3);
    result.add(track4);
    assertEquals(result, trackTree.optimalPath(endPaths));

    tracknode6.setScore(3.0);
    assertEquals(result, trackTree.optimalPath(endPaths));
  }

}
