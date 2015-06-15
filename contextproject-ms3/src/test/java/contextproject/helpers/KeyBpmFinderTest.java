package contextproject.helpers;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class KeyBpmFinderTest {

  @Test
  public void nonExistentTest() {
    KeyBpmFinder keyBpm = new KeyBpmFinder();
    keyBpm.findKeyBpm("lol");
    assertNotEquals(keyBpm, null);
  }
}
