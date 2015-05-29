package contextproject;

import contextproject.audio.PlayerServiceTest;
import contextproject.controllers.PlaylistControllerTest;
import contextproject.formats.M3UBuilderTest;
import contextproject.formats.XmlExportTest;
import contextproject.helpers.PlaylistNameTest;
import contextproject.helpers.StackTraceTest;
import contextproject.helpers.TrackCompatibilityTest;
import contextproject.loaders.FolderLoaderTest;
import contextproject.loaders.LibraryLoaderTest;
import contextproject.models.BeatGridTest;
import contextproject.models.BeatRangeTest;
import contextproject.models.KeyTest;
import contextproject.models.LibraryTest;
import contextproject.models.PropertyTest;
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
    GraphTest.class, GreedyPlaylistSorterTest.class, TrackNodeTest.class, TrackTreeTest.class,
    M3UBuilderTest.class, LibraryLoaderTest.class, XmlExportTest.class,
    PlaylistControllerTest.class, M3UBuilderTest.class, PlaylistNameTest.class,
    StackTraceTest.class, PropertyTest.class, PlayerServiceTest.class, LibraryTest.class })
public class AllTests {

}
