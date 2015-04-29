package contextproject.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;

import org.junit.Before;
import org.junit.Test;



public class KeyTest {
  private Mp3File song = mock(Mp3File.class);
  private Mp3File song2 = mock(Mp3File.class);
  private Mp3File song3 = mock(Mp3File.class);

  private ID3v2 id31 = mock(ID3v2.class);
  private ID3v2 id32 = mock(ID3v2.class);
  private ID3v2 id33 = mock(ID3v2.class);

  /**
   * Stub all mocks.
   */
  @Before
  public void setup() {
    when(song.hasId3v2Tag()).thenReturn(true);
    when(song.getId3v2Tag()).thenReturn(id31);
    when(id31.getKey()).thenReturn("Bmm");

    when(song2.hasId3v2Tag()).thenReturn(true);
    when(song2.getId3v2Tag()).thenReturn(id32);
    when(id32.getKey()).thenReturn("Abm");

    when(song3.hasId3v2Tag()).thenReturn(true);
    when(song3.getId3v2Tag()).thenReturn(id33);
    when(id33.getKey()).thenReturn("E");
  }

  /**
   * test for situation without a key.
   */
  @Test
  public void keyNotExistTest() {
    Key key = new Key(song);
    assertEquals(key.getMusicKey(), "Bmm");
    assertEquals(key.getCamelotWheelKey(), "0");
    assertEquals(key.getNeighborKeys().size(),0);
  }

  /**
   * test for situation with key Abm-> 1A.
   */
  @Test
  public void key1ATest() {
    Key key = new Key(song2);
    assertEquals(key.getMusicKey(), "Abm");
    assertEquals(key.getCamelotWheelKey(), "1A");
    assertEquals(key.getNeighborKeys().size(), 4);
    assertTrue(key.getNeighborKeys().contains("12A"));
  }
  
  /**
   * test for situation with key E -> 12B.
   */
  @Test
  public void keyETest() {
    Key key = new Key(song3);
    assertEquals(key.getMusicKey(), "E");
    assertEquals(key.getCamelotWheelKey(), "12B");
    assertEquals(key.getNeighborKeys().size(), 4);
    assertTrue(key.getNeighborKeys().contains("1B"));
  }

}
