package contextproject.helpers;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class StackTraceTest {

  @Test
  public void test() {
    StackTrace stackTrace = new StackTrace();
    StackTrace similarStackTrace = new StackTrace();
    assertNotEquals(stackTrace, similarStackTrace);
  }
}
