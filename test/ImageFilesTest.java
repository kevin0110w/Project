package test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.ImageFiles;
/**
 * Testing methods within the ImageFiles class to ensure correct files are read
 *
 */
public class ImageFilesTest {
	private ImageFiles imageFiles;

	@Before
	public void setUp() throws Exception {
		this.imageFiles = new ImageFiles();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testIsCorrectFileMethod() {
		File file = new File("C:\\Users\\woohoo\\eclipse-workspace\\project\\mikons1\\1.gif");
		boolean isValidFile = this.imageFiles.isCorrectFile(file);
		assertTrue(isValidFile);
		file = new File("C:\\Users\\woohoo\\eclipse-workspace\\project\\art\\1.jpg");
		isValidFile = this.imageFiles.isCorrectFile(file);
		assertTrue(isValidFile);
		file = new File("C:\\Users\\woohoo\\eclipse-workspace\\project\\mikons1\\.gitignore");
		isValidFile = this.imageFiles.isCorrectFile(file);
		assertTrue(isValidFile);
	}
	
	@Test
	public void testRegistrationSetOnlySixtyImages() {
		imageFiles.createRegistrationSet(1);
		int listOneSize = imageFiles.getListOne().size();
		int listTwoSize = imageFiles.getListTwo().size();
		int listThreeSize = imageFiles.getListThree().size();
		assertTrue(listOneSize == 20);
		assertTrue(listTwoSize == 20);
		assertTrue(listThreeSize == 20);	
	}
	
	@Test
	public void testListsAreInitiallyEmpty() {
		assertEquals("List One should initially be empty", 0, imageFiles.getListOne().size());
		assertEquals("List Two should initially be empty", 0, imageFiles.getListTwo().size());
		assertEquals("List Three should initially be empty", 0, imageFiles.getListThree().size());
		assertEquals("Seen Images list should initially be empty", 0, imageFiles.getSeenImages().size());
		assertEquals("Unseen Images list should initially be empty", 0, imageFiles.getUnseenImages().size());
	}
	
	@Test
	public void testValidSelection() {
		List<String> allImages = new ArrayList<String>();
		assertEquals("Inital list size is 0", 0, allImages.size());
		allImages.addAll(imageFiles.returnFiles(1));
		assertTrue("allImages populated as valid selection", allImages.size() > 0);
		allImages.clear();
		allImages.addAll(imageFiles.returnFiles(2));
		assertTrue("allImages populated as valid selection", allImages.size() > 0);
		allImages.clear();
		allImages.addAll(imageFiles.returnFiles(3));
		assertTrue("allImages populated as valid selection", allImages.size() > 0);
		allImages.clear();
	}
	
	@Test (expected = NullPointerException.class)
	public void testInvalidSelection() {
		List<String> allImages = new ArrayList<String>();
		allImages.addAll(imageFiles.returnFiles(4));
		allImages.addAll(imageFiles.returnFiles(0));
	}
	
	@Test
	public void testSeenImagesIsPopulated() {
		imageFiles.createRegistrationSet(1);
		assertTrue("Seen images list should be greater than 0", imageFiles.getSeenImages().size() > 0);
		
	}
	
	@Test
	public void testUnseenImagesIsPopulated() {
		imageFiles.createRegistrationSet(1);
		assertTrue("Unseen images list should be greater than 0", imageFiles.getUnseenImages().size() > 0);
	}
	
	@Test (expected = NullPointerException.class)
	public void testSeenImagesShouldntBePopulated() {
		imageFiles.createRegistrationSet(0);
		assertTrue("Seen images list should be 0", imageFiles.getSeenImages().size() == 0);
	}
	
	@Test (expected = NullPointerException.class)
	public void testUnseenImagesShouldntBePopulated() {
		imageFiles.createRegistrationSet(0);
		assertTrue("Unseen images list should be 0", imageFiles.getUnseenImages().size() == 0);
	}

	
}
