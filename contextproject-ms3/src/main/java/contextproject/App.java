package contextproject;

import contextproject.controllers.CLIController;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.io.TarsosDSPAudioInputStream;
import be.tarsos.dsp.io.jvm.AudioPlayer;
import be.tarsos.dsp.io.jvm.JVMAudioInputStream;
import be.tarsos.transcoder.Attributes;
import be.tarsos.transcoder.DefaultAttributes;
import be.tarsos.transcoder.Streamer;
import be.tarsos.transcoder.ffmpeg.EncoderException;

/**
 * Hello world.
 *
 */
public class App {
  /**
   * Should start our application. No GUI for now just CLI.
   */

  static Logger log = LogManager.getLogger(App.class.getName());

  public static void main(String[] args) {
    CLIController control = new CLIController();
    control.run();
  }
}
