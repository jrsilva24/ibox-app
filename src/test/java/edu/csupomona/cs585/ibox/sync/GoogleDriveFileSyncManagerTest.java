package edu.csupomona.cs585.ibox.sync;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import edu.csupomona.cs585.ibox.*;

import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;


public class GoogleDriveFileSyncManagerTest {
	
	@Mock
	public Drive service;
	
	@Before
	public void setup(){
		
	}
	
	@Test
	public void addFileTest() throws IOException {
		
		//Drive mockDriveService = mock(Drive.class);
		Drive mockDriveService = 
        		GoogleDriveServiceProvider.get().getGoogleDriveClient();
		AbstractGoogleClientRequest mockDriveServiceAGCR = mock(AbstractGoogleClientRequest.class);
		File body = new File();
		java.io.File localFile = new java.io.File ("DummyTest.txt");
		body.setTitle(localFile.getName());
		//FileContent mediaContent = new FileContent("*/*", localFile);
		
		when(mockDriveServiceAGCR.execute()).thenReturn(new File());
		
		GoogleDriveFileSyncManager syncManager = new GoogleDriveFileSyncManager(mockDriveService);
	
		
		
		syncManager.addFile(localFile);
		
		assertTrue(true);
	}
	
	@Test
	public void updateFileTest() throws IOException {
		
		
		assertTrue(true);
	}
	
	@Test
	public void deleteFileTest() throws IOException {
		
		
		assertTrue(true);
	}
	
	@Test
	public void getFileIdTest() throws IOException {
		
		
		assertTrue(true);
	}

}

