package contextproject;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class StackTrace {
  static public String stackTrace(Exception e){
  Writer writer = new StringWriter();
  PrintWriter printWriter = new PrintWriter(writer);
  e.printStackTrace(printWriter);
  String s = writer.toString();
  return s;
  }
}
