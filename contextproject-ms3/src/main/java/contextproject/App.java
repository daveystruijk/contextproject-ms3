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

  public static void main(String[] args) {
    System.out.println("Hello World!");
    System.out.println("test test test");
    elog.trace("elog trace");
    dlog.trace("dlog trace");
    try{
      throw new NullPointerException();
    }catch(NullPointerException e){
      elog.error("error in paradise");
      elog.info(StackTrace.stackTrace(e));
    }
  }
}
