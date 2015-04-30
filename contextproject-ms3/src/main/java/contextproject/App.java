package contextproject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hello world.
 *
 */
public class App {
  static Logger elog = LogManager.getLogger("Error-log");
  static Logger dlog = LogManager.getLogger("Debug-log");
  static Logger log = LogManager.getLogger(App.class.getName());
  
  /**
   * test method for log4j2.
   * @param args args.
   */
  public static void main(String[] args) {
    System.out.println("Hello World!");
    System.out.println("test test test");
    elog.trace("elog trace");
    dlog.trace("dlog trace");
    log.trace("checking the root logger");
    try {
      throw new NullPointerException();
    } catch (NullPointerException e) {
      elog.error("error in paradise");
      elog.trace(StackTrace.stackTrace(e));
      log.error("error in paradise");
      log.trace(StackTrace.stackTrace(e));
      
    }
  }
}
