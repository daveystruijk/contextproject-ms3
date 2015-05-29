package contextproject.audio;

import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;

public class MixerProcessor implements AudioProcessor {
  private double gain = 1.0f;

  @Override
  public boolean process(AudioEvent audioEvent) {
    float[] floatBuffer = audioEvent.getFloatBuffer();
    for (int i = audioEvent.getOverlap(); i < floatBuffer.length; i++) {
      float newValue = manipulateSignal(floatBuffer[i]);

      // Clipping
      if (newValue > 1.0f) {
        newValue = 1.0f;
      } else if (newValue < -1.0f) {
        newValue = -1.0f;
      }
      floatBuffer[i] = newValue;
    }
    return true;
  }

  public float manipulateSignal(float signal) {
    gain += 0.0001f;
    return (float) (signal * gain);
  }

  @Override
  public void processingFinished() {
  }

}
