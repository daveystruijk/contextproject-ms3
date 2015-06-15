package contextproject.helpers;

import static org.junit.Assert.*;

import org.junit.Test;

public class KeyBpmFinderTest {

  @Test
  public void nonExistentTest() {
    KeyBpmFinder keyBpm = new KeyBpmFinder();
    keyBpm.findKeyBpm("lol");
  }
}
