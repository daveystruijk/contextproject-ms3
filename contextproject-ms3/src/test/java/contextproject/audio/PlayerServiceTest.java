package contextproject.audio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import be.tarsos.transcoder.ffmpeg.EncoderException;

import org.junit.Test;

import javax.sound.sampled.LineUnavailableException;

public class PlayerServiceTest {

  @Test
  public void testNullInstance() throws EncoderException, LineUnavailableException {

    assertNotEquals(PlayerService.getInstance(), new PlayerService());
    assertEquals(new PlayerService().getCurrentTrack(), null);
  }

}
