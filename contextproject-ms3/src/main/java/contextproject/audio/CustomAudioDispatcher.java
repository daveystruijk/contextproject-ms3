package contextproject.audio;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.io.TarsosDSPAudioInputStream;

public class CustomAudioDispatcher extends AudioDispatcher {

  public CustomAudioDispatcher(TarsosDSPAudioInputStream stream, int audioBufferSize,
      int bufferOverlap) {
    super(stream, audioBufferSize, bufferOverlap);
  }
}
