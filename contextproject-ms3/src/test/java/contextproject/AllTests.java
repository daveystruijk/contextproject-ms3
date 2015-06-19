package contextproject;

import contextproject.audio.PlayerServiceTest;
import contextproject.controllers.LibraryControllerTest;
import contextproject.controllers.PlayerControlsControllerTest;
import contextproject.controllers.PlaylistControllerTest;
import contextproject.controllers.WindowControllerTest;
import contextproject.formats.M3UBuilderTest;
import contextproject.formats.XmlExportTest;
import contextproject.helpers.FileNameTest;
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
import contextproject.models.TrackPropertyTest;
import contextproject.models.TrackTest;
import contextproject.sorters.GraphTest;
import contextproject.sorters.GreedyPlaylistSorterTest;
import contextproject.sorters.TrackNodeTest;
import contextproject.sorters.TrackTreeTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
    //main package.
    AppTest.class,
    //audio package.
    PlayerServiceTest.class,
    //controllers package.
    LibraryControllerTest.class,
    PlayerControlsControllerTest.class,
    PlaylistControllerTest.class,
    WindowControllerTest.class,
    //formats package.
    M3UBuilderTest.class,
    XmlExportTest.class,
    //helpers package.
    FileNameTest.class,
    PlaylistNameTest.class,
    StackTraceTest.class,
    TrackCompatibilityTest.class,
    //loaders package.
    FolderLoaderTest.class,
    LibraryLoaderTest.class,
    //models package.
    BeatGridTest.class,
    BeatRangeTest.class,
    KeyTest.class,
    LibraryTest.class,
    PropertyTest.class,
    TrackPropertyTest.class,
    TrackTest.class,
    //sorters package.
    GraphTest.class,
    GreedyPlaylistSorterTest.class,
    TrackNodeTest.class,
    TrackTreeTest.class })
public class AllTests {

}
