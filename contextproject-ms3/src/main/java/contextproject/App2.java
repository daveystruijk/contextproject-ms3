package contextproject;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App2 {

  static Logger log = LogManager.getLogger(App2.class.getName());
  static StringWriter error = new StringWriter();
  static public void Test() {

    log.trace("start test app2");
    try {
      throw new NullPointerException();
    } catch (NullPointerException e) {
      log.error("error: Nullpointer ");
    }

    try {
      throw new NullPointerException();
    } catch (NullPointerException e) {
      log.error("error: Nullpointer");
    }
    log.trace("end test app2");
  }
}
