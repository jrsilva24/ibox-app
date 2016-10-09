package edu.csupomona.cs585.ibox.sync;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.api.services.drive.Drive;

public class GoogleDriveFileSyncManagerTest {
	
	@Mock
	public Drive service;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void addFileTest() throws IOException {
		
		
		assertTrue(true);
	}

}

