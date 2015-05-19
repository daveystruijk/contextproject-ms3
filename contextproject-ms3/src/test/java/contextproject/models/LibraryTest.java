package contextproject.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class LibraryTest {

  @Test
  public void emptyConstructorTest() {
    Library lib = new Library();
    assertTrue(lib.isEmpty());
  }
  
  @Test
  public void argumentConstructorTest() {
    ArrayList<Playlist> array = new ArrayList<Playlist>();
    Playlist pl = new Playlist();
    array.add(pl);
    Library lib = new Library(array);
    assertEquals(lib.size(), 1);
  }

}
