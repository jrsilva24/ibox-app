package edu.csupomona.cs585.ibox.sync;

import static org.junit.Assert.*;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.Drive.Files;
import com.google.api.services.drive.Drive.Files.Delete;
import com.google.api.services.drive.Drive.Files.Insert;
import com.google.api.services.drive.Drive.Files.Update;


public class GoogleDriveFileSyncManagerTest {
	
	@Mock
	public Drive MockDriveService;
	GoogleDriveFileSyncManager DriveSyncManager;
	
	@Before
	public void setup(){
		MockDriveService = Mockito.mock(Drive.class);
		DriveSyncManager = new GoogleDriveFileSyncManager(MockDriveService);
		
	}
	
	@Test
	public void addFileTest() throws IOException {
		File mLocalFile = Mockito.mock(File.class);
 		Files mTestDir = Mockito.mock(Files.class);
 		
 		when(MockDriveService.files()).thenReturn(mTestDir);
 		Insert insertTest = Mockito.mock(Insert.class);
 		
 		when(mTestDir.insert(isA(com.google.api.services.drive.model.File.class),isA(AbstractInputStreamContent.class))).
 		thenReturn(insertTest);
 		
 		
 		com.google.api.services.drive.model.File finalFile = new com.google.api.services.drive.model.File();
 		finalFile.setId("Mock ID");
 		
 		when(insertTest.execute()).thenReturn(finalFile);
 		
 		finalFile.getId();
 		DriveSyncManager.addFile(mLocalFile);
 		verify(MockDriveService).files();
	}
	
	@Test(expected = Exception.class)
 	public void testExceptionUpdateFile() throws IOException {
 		File localFile = Mockito.mock(File.class);
 		when(localFile.getName()).thenReturn(null);
 		DriveSyncManager.updateFile(localFile);
 	}
	
	@Test
	public void updateFileTest() throws IOException {
		File localFile = Mockito.mock(File.class);
 		when(localFile.getName()).thenReturn("Dummy.txt");
 		Files testDir = mock(Files.class);
 		when(MockDriveService.files()).thenReturn(testDir);
 
 		com.google.api.services.drive.Drive.Files.List mockList = mock(com.google.api.services.drive.Drive.Files.List.class);
 		when(testDir.list()).thenReturn(mockList);
 
 		com.google.api.services.drive.model.File finalFile = new com.google.api.services.drive.model.File();
 		finalFile.setId("Mock ID");
 		finalFile.setTitle("Dummy.txt");
 
 		com.google.api.services.drive.model.FileList files = new com.google.api.services.drive.model.FileList();
 		List<com.google.api.services.drive.model.File> items = new ArrayList<com.google.api.services.drive.model.File>();
 		items.add(finalFile);
 		files.setItems(items);
 
 		when(mockList.execute()).thenReturn(files);
 
 		String fileId = DriveSyncManager.getFileId("Dummy.txt");
 
 		Update updateTest = Mockito.mock(Update.class);
 		when(
 				testDir.update(eq(fileId),
 						isA(com.google.api.services.drive.model.File.class),
 						isA(AbstractInputStreamContent.class))).thenReturn(
 				updateTest);
 
 		com.google.api.services.drive.model.File finalFile2 = new com.google.api.services.drive.model.File();
 		when(updateTest.execute()).thenReturn(finalFile2);
 
 		DriveSyncManager.updateFile(localFile);
 		verify(MockDriveService, times(3)).files();
	}
	
	@Test(expected = Exception.class)
 	public void testExceptionDeleteFile() throws IOException {
 		Drive MockDriveService = Mockito.mock(Drive.class);
 		GoogleDriveFileSyncManager DriveSyncManager = new GoogleDriveFileSyncManager(
 				MockDriveService);
 
 		File localFile = Mockito.mock(File.class);
 		when(localFile.getName()).thenReturn(null);
 
 		DriveSyncManager.deleteFile(localFile);
 	}
	
	@Test
	public void deleteFileTest() throws IOException {
		Files testDir = mock(Files.class);
 		when(MockDriveService.files()).thenReturn(testDir);
 
 		com.google.api.services.drive.Drive.Files.List mockList = mock(com.google.api.services.drive.Drive.Files.List.class);
 		when(testDir.list()).thenReturn(mockList);
 
 		com.google.api.services.drive.model.File fFile = new com.google.api.services.drive.model.File();
 		fFile.setId("Mock ID");
 		fFile.setTitle("Dummy.txt");
 
 		com.google.api.services.drive.model.FileList files = new com.google.api.services.drive.model.FileList();
 		List<com.google.api.services.drive.model.File> items = new ArrayList<com.google.api.services.drive.model.File>();
 		items.add(fFile);
 		files.setItems(items);
 
 		when(mockList.execute()).thenReturn(files);
 
 		String getId = DriveSyncManager.getFileId("Dummy.txt");
 		assertEquals("Mock ID", getId);
	}
	
	@Test
	public void getFileIdTest() throws IOException {
		
		Files testDir = mock(Files.class);
 		when(MockDriveService.files()).thenReturn(testDir);
 
 		com.google.api.services.drive.Drive.Files.List mockList = mock(com.google.api.services.drive.Drive.Files.List.class);
 		when(testDir.list()).thenReturn(mockList);
 
 		com.google.api.services.drive.model.File finalFile = new com.google.api.services.drive.model.File();
 		finalFile.setId("Mock ID");
 		finalFile.setTitle("Dummy.txt");
 
 		com.google.api.services.drive.model.FileList files = new com.google.api.services.drive.model.FileList();
 		List<com.google.api.services.drive.model.File> items = new ArrayList<com.google.api.services.drive.model.File>();
 		items.add(finalFile);
 		files.setItems(items);
 
 		when(mockList.execute()).thenReturn(files);
 
 		String getId = DriveSyncManager.getFileId("Dummy.txt");
 		assertEquals("Mock ID", getId);
	}

}

