package edu.csupomona.cs585.ibox;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import edu.csupomona.cs585.ibox.sync.GoogleDriveFileSyncManagerTest;

@RunWith(value = Suite.class)
@SuiteClasses(value = { GoogleDriveFileSyncManagerTest.class,
		WatchDirTest.class,AppTest.class
		})

public class TestSuite {
}