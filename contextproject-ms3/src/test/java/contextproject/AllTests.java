package contextproject;

import contextproject.controllers.LibraryControllerTest;
import contextproject.formats.M3UBuilderTest;
import contextproject.formats.XmlExportTest;
import contextproject.helpers.TrackCompatibilityTest;
import contextproject.loaders.FolderLoaderTest;
import contextproject.loaders.LibraryLoaderTest;
import contextproject.models.BeatGridTest;
import contextproject.models.BeatRangeTest;
import contextproject.models.KeyTest;
import contextproject.models.TrackTest;
import contextproject.sorters.GraphTest;
import contextproject.sorters.GreedyPlaylistSorterTest;
import contextproject.sorters.TrackNodeTest;
import contextproject.sorters.TrackTreeTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ M3UBuilderTest.class, TrackCompatibilityTest.class, FolderLoaderTest.class,
    BeatGridTest.class, KeyTest.class, TrackTest.class, AppTest.class, BeatRangeTest.class,
    LibraryLoaderTest.class, XmlExportTest.class, GraphTest.class, GreedyPlaylistSorterTest.class,
    LibraryControllerTest.class, TrackNodeTest.class, TrackTreeTest.class})
public class AllTests {

}
