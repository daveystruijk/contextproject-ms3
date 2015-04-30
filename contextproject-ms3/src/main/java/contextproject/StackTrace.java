package contextproject;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class StackTrace {
  
  /**
   * method for turning the stacktrace into a string.
   * @param ex the exception which stacktrace will be converted.
   * @return the string of the stacktrace.
   */
  public static String stackTrace(Exception ex) {
    Writer writer = new StringWriter();
    PrintWriter printWriter = new PrintWriter(writer);
    ex.printStackTrace(printWriter);
    String str = writer.toString();
    return str;
  }
}
