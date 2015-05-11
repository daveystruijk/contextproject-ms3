package contextproject;

import contextproject.formats.M3UBuilderTest;
import contextproject.formats.XmlExportTest;
import contextproject.helpers.TrackCompatibilityTest;
import contextproject.loaders.FolderLoaderTest;
import contextproject.loaders.LibraryLoaderTest;
import contextproject.models.BeatGridTest;
import contextproject.models.BeatRangeTest;
import contextproject.models.KeyTest;
import contextproject.models.LibraryTest;
import contextproject.models.TrackTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ M3UBuilderTest.class, TrackCompatibilityTest.class, FolderLoaderTest.class,
    BeatGridTest.class, KeyTest.class, TrackTest.class, AppTest.class, BeatRangeTest.class,
    LibraryLoaderTest.class, XmlExportTest.class, LibraryTest.class })
public class AllTests {

}
