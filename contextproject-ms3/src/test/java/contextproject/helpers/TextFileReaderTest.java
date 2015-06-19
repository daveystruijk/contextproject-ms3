package contextproject.helpers;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class TextFileReaderTest {

  @Test
  public void test() {
    TextFileReader reader = new TextFileReader();
    assertNotEquals(reader, null);
    TextFileReader.read("library.xml");
    TextFileReader.read("about.txt");
  }
}
