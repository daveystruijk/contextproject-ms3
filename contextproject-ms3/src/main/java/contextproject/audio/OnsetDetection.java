package contextproject.audio;

import be.tarsos.dsp.onsets.ComplexOnsetDetector;

import java.lang.reflect.Field;


public class OnsetDetection extends ComplexOnsetDetector{

  public OnsetDetection(int fftSize, double peakThreshold, double minimumInterOnsetInterval,
      double silenceThreshold) {
    super(fftSize, peakThreshold, minimumInterOnsetInterval, silenceThreshold);
  }
  
  public double getLastOnset() {
    Field field = null;
    try {
      field = ComplexOnsetDetector.class.getDeclaredField("lastOnset");
    } catch (NoSuchFieldException | SecurityException e) {
      e.printStackTrace();
    }
    //field.get(obj);
    return 0;
  }

}
