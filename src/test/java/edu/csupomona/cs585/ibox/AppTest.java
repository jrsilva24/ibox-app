package edu.csupomona.cs585.ibox;


import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.api.services.drive.Drive;

import edu.csupomona.cs585.ibox.WatchDir;
import edu.csupomona.cs585.ibox.sync.GoogleDriveFileSyncManager;
import edu.csupomona.cs585.ibox.sync.GoogleDriveServiceProvider;

/**
 * Placeholder for unit test
 */
public class AppTest extends TestCase {
	public static final String DIR_PATH = "Testsync";
 	public static final String FILE_NAME = "CSUP1.txt";
 	public static boolean boolAddSync = Boolean.FALSE;
 	static GoogleDriveFileSyncManager gsync;
 	// Google Drive service
 	public Drive service;
 	
  @Before
 	 	public void setUp() throws Exception {
 	 		gsync = new GoogleDriveFileSyncManager(GoogleDriveServiceProvider.get()
 	 				.getGoogleDriveClient());
 	 	}

    /**
     * Create the test case
     *
     * @param testName name of the test case
     * @throws IOException 
     */
  @Test
    public void testAddSync( String testName ) throws IOException
    {
 		Path dir = Paths.get(DIR_PATH);
 		new TestADDDELETE().start();
 		new WatchDir(dir, gsync).processEvents();
 		assertTrue(boolAddSync); // File added to G Drive
    }
  @Test
 	public void testDeleteSync() throws IOException {
 		assertEquals(gsync.getFileId(FILE_NAME), null);
 	}


    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}

class TestADDDELETE extends Thread {
	 
	 	@Override
	 	public void run() {
	 		try {
	 			File file = new File(AppTest.DIR_PATH);
	 			if (!file.exists()) {
	 				if (file.mkdir()) {
	 					System.out.println("Directory is created!");
	 				} else {
	 					System.out.println("Failed to create directory!");
	 				}
	 			}
	 
	 			Thread.sleep(8000); // wait here to copy past code in console
	 			Path file1 = Paths.get(AppTest.DIR_PATH + "\\"
	 					+ AppTest.FILE_NAME);
	 			Files.createFile(file1); // add file1
	 			Thread.sleep(8000);
	 			System.out.println("File created in Folder to sync");
	 			if (AppTest.gsync.getFileId(AppTest.FILE_NAME) != null) {
	 				System.out.println("File uploaded to Drive");
	 				AppTest.boolAddSync = true;
	 			}
	 			Files.delete(file1); // delete file1
	 			System.out.println("File deleted from local directory");
	 			Thread.sleep(4000);
	 			file.delete();
	 			System.out.println("Directory deleted");
	 			Thread.sleep(2000);
	 		} catch (IOException e) {
	 			e.printStackTrace();
	 			fail();
	 		} catch (InterruptedException e) {
	 			fail();
	 		}
	 	}
	 }
