package contextproject.audio;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineUnavailableException;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.GainProcessor;
import be.tarsos.dsp.WaveformSimilarityBasedOverlapAdd;
import be.tarsos.dsp.WaveformSimilarityBasedOverlapAdd.Parameters;
import be.tarsos.dsp.io.TarsosDSPAudioInputStream;
import be.tarsos.dsp.io.jvm.AudioPlayer;
import be.tarsos.dsp.io.jvm.JVMAudioInputStream;
import be.tarsos.transcoder.Attributes;
import be.tarsos.transcoder.Streamer;
import be.tarsos.transcoder.ffmpeg.EncoderException;
import contextproject.models.Track;

public class TrackProcessor {
  // State
  private PlayerState state;
  private Track track;
  
  // Data
  private Attributes attributes;
  private AudioFormat format;
  
  // Audio Processing
  private AudioInputStream inputStream;
  private TarsosDSPAudioInputStream tarsosStream;
  private AudioPlayer audioPlayer;
  private WaveformSimilarityBasedOverlapAdd wsola;
  private GainProcessor gainProcessor;
  private AudioDispatcher dispatcher;

  private double tempo;
  private double currentTime;
  private double pausedAt;
  private double totalDuration;

  public TrackProcessor(Attributes attributes) {
    try {
      this.attributes = attributes;
      this.format = Streamer.streamAudioFormat(attributes);
    } catch (EncoderException e) {
      e.printStackTrace();
    }
  }

  public void load(Track track) {
    if (state != PlayerState.NO_FILE_LOADED) {
      this.unload();
    }

    this.track = track;
    this.tempo = 1.0;
    this.pausedAt = 0;
    this.currentTime = 0;

    setState(PlayerState.FILE_LOADED);
  }

  public void unload() {
    if(dispatcher != null) {
      dispatcher.stop();
    }
    track = null;
    setState(PlayerState.NO_FILE_LOADED);
  }

  public void play(double startGain) throws EncoderException, LineUnavailableException {
    inputStream = Streamer.stream(track.getPath(), attributes);
    tarsosStream = new JVMAudioInputStream(inputStream);
    
    audioPlayer = new AudioPlayer(format);
    wsola = new WaveformSimilarityBasedOverlapAdd(Parameters.musicDefaults(tempo,
        format.getSampleRate()));
    gainProcessor = new GainProcessor(startGain);
    dispatcher = new AudioDispatcher(tarsosStream, wsola.getInputBufferSize(), wsola.getOverlap());

    wsola.setDispatcher(dispatcher);
    dispatcher.addAudioProcessor(wsola);
    dispatcher.addAudioProcessor(gainProcessor);
    dispatcher.addAudioProcessor(audioPlayer);
    
    Thread thread = new Thread(dispatcher);
    thread.start();
    setState(PlayerState.PLAYING);
  }

  public void setGain(double gain) {
    gainProcessor.setGain(gain);
  }
  
  private void setState(PlayerState state) {
    this.state = state;
  }

  public static enum PlayerState {
    NO_FILE_LOADED, FILE_LOADED, PLAYING, PAUSED, STOPPED
  }
}
