package contextproject;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.logging.log4j.*;

/**
 * Hello world.
 *
 */
public class App {
  
  static Logger log = LogManager.getLogger(App.class.getName());
  static StringWriter error = new StringWriter();
  
  public static void main(String[] args) {
    System.out.println("Hello World!");
    System.out.println("test test test");
    App2.Test();
    log.trace("start test App1");
    try {
      throw new NullPointerException();
  }
  catch (NullPointerException e) {
    e.printStackTrace();
    log.error("error: Nullpointer");
  }
    
    try {
      throw new NullPointerException();
  }
  catch (NullPointerException ex) {
    //ex.printStackTrace(new PrintWriter(error));
    //log.error("error: "+error.toString());
    log.error("error: nullpointer");
  }
  log.trace("end test App1");
  }
}
