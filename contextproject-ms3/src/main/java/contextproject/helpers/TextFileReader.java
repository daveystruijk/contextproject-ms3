package contextproject.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TextFileReader {
  private static Logger log = LogManager.getLogger(TextFileReader.class.getName());

  /**
   * read in a text file.
   * @param file the text to be read.
   * @return an array of the string lines.
   */
  public static ArrayList<String> read(String file) {
    ArrayList<String> lines = new ArrayList<String>();
    String line;
    try {
      BufferedReader br = new BufferedReader(new FileReader(file));

      while ((line = br.readLine()) != null) {
        lines.add(line);
      }
      br.close();
    } catch (IOException ex) {
      log.error("couldn't read file");;
    }

    return lines;
  }
}
