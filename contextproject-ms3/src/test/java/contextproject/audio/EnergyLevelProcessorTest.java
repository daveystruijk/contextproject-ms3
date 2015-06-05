package contextproject.audio;

import contextproject.models.Track;

import static org.junit.Assert.assertNotEquals;

import be.tarsos.transcoder.Attributes;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.sound.sampled.LineUnavailableException;

public class EnergyLevelProcessorTest {

  private Attributes mockAttributes;
  private EnergyLevelProcessor energyProcessor;

  /**
   * This test the processor for the Energy Level. For now relies on Mockito to mock certain Tarsos
   * elements.
   */
  @Before
  public void setUp() {
    mockAttributes = Mockito.mock(Attributes.class);
    energyProcessor = new EnergyLevelProcessor(mockAttributes);
    assertNotEquals(energyProcessor, null);
  }

  @Test
  public void constTest() {
    assertNotEquals(new EnergyLevelProcessor(mockAttributes), energyProcessor);
  }
}
