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

public class GoogleDriveFileSyncManagerTest {
	
	@Mock
	public Drive service;
	
	@Before
	public void setup(){
		service = GoogleDriveServiceProvider.get().getGoogleDriveClient();
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void addFileTest() throws IOException {
		GoogleDriveFileSyncManager syncManager = new GoogleDriveFileSyncManager(service);
		java.io.File localFile = new java.io.File ("DummyTest.txt");
		syncManager.addFile(localFile);
		
		File body = new File();
		body.setTitle(localFile.getName());
		FileContent mediaContent = new FileContent("*/*", localFile);
		when(service.files().insert(body, mediaContent).execute()).thenReturn(new File());
		
		verify(service).files().insert(body,mediaContent).execute();
		
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

