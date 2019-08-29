package test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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
	private String startingPath = System.getProperty("user.dir");
	private String exampleArtFile = startingPath + "\\art\\image (1).jpg";
	private String exampleMikonsGFile = startingPath + "\\mikons_1\\1.gif";
	private String exampleMikonsCFile = startingPath + "\\mikons_2\\1.gif";
	private Set<String> listArt, listMikonsGrey, listMikonsColour;

	@Before
	public void setUp() throws Exception {
		this.imageFiles = new ImageFiles();
		this.listArt = new HashSet<String>();
		this.listMikonsGrey = new HashSet<String>();
		this.listMikonsColour = new HashSet<String>();
		this.listArt.add(exampleArtFile);
		this.listMikonsGrey.add(exampleMikonsGFile);
		this.listMikonsColour.add(exampleMikonsCFile);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testEachListHasTwentyImages() {
		imageFiles.createRegistrationSet(1, listArt);
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
	
	/**
	 * The following tests check that the list containing seen images during registration should not contain elements from list art that contain elements that may have been seen in a prior registration
	 */
	@Test
	public void testSeenImagesIsPopulatedArtWithLoginMethodOne() {
		imageFiles.createRegistrationSet(1, listArt);
		String getArtImage = returnListElement(listArt);
		assertTrue("Seen images list should be greater than 0", imageFiles.getSeenImages().size() > 0);	
		assertTrue("Seen images should not contain the alternative image", !imageFiles.getSeenImages().contains(getArtImage));
	}
	
	/**
	 * The following tests check that the list containing seen images during registration should not contain elements from list art that contain elements that may have been seen in a prior registration
	 */
	@Test
	public void testSeenImagesIsPopulatedMikonGreyWithLoginMethodOne() {
		imageFiles.createRegistrationSet(1, listMikonsGrey);
		String getMikonGreyImage = returnListElement(listMikonsGrey);
		assertTrue("Seen images list should be greater than 0", imageFiles.getSeenImages().size() > 0);
		assertTrue("Seen images should not contain the alternative image", !imageFiles.getSeenImages().contains(getMikonGreyImage));
	}
	
	/**
	 * The following tests check that the list containing seen images during registration should not contain elements from list art that contain elements that may have been seen in a prior registration
	 */
	@Test
	public void testSeenImagesIsPopulatedMikonsColourWithLoginMethodOne() {
		imageFiles.createRegistrationSet(1, listMikonsColour);
		String getMikonsColourImage = returnListElement(listMikonsColour);
		assertTrue("Seen images list should be greater than 0", imageFiles.getSeenImages().size() > 0);
		assertTrue("Seen images should not contain the alternative image", !imageFiles.getSeenImages().contains(getMikonsColourImage));
	}
	
	/**
	 * The following tests check that the list containing seen images during registration should not contain elements from list art that contain elements that may have been seen in a prior registration
	 */
	@Test
	public void testUnseenImagesIsPopulatedArtWithLoginMethodOne() {
		imageFiles.createRegistrationSet(1, listArt);
		String getArtImage = returnListElement(listArt);
		assertTrue("Unseen images list should be greater than 0", imageFiles.getUnseenImages().size() > 0);
		assertTrue("Unseen images should contain the alternative image", !imageFiles.getUnseenImages().contains(getArtImage));
	}
	
	/**
	 * The following tests check that the list containing seen images during registration should not contain elements from list art that contain elements that may have been seen in a prior registration
	 */
	@Test
	public void testUnseenImagesIsPopulatedMikonGreyWithLoginMethodOne() {
		imageFiles.createRegistrationSet(1, listMikonsGrey);
		String getMikonGreyImage = returnListElement(listMikonsGrey);
		assertTrue("Unseen images list should be greater than 0", imageFiles.getUnseenImages().size() > 0);
		assertTrue("Unseen images should contain the alternative image", !imageFiles.getUnseenImages().contains(getMikonGreyImage));
	}
	
	/**
	 * The following tests check that the list containing seen images during registration should not contain elements from list art that contain elements that may have been seen in a prior registration
	 */
	@Test
	public void testUnseenImagesIsPopulatedMikonsColourWithLoginMethodOne() {
		imageFiles.createRegistrationSet(1, listMikonsColour);
		String getMikonsColourImage = returnListElement(listMikonsColour);
		assertTrue("Unseen images list should be greater than 0", imageFiles.getUnseenImages().size() > 0);
		assertTrue("Unseen images should contain the alternative image", !imageFiles.getUnseenImages().contains(getMikonsColourImage));
	}
	/**
	 * The following tests check that the list containing seen images during registration should not contain elements from list art that contain elements that may have been seen in a prior registration
	 */
	@Test
	public void testSeenImagesIsPopulatedArtWithLoginMethodTwo() {
		imageFiles.createRegistrationSet(2, listArt);
		String getArtImage = returnListElement(listArt);
		assertTrue("Seen images list should be greater than 0", imageFiles.getSeenImages().size() > 0);	
		assertTrue("Seen images should not contain the alternative image", !imageFiles.getSeenImages().contains(getArtImage));
	}
	
	/**
	 * The following tests check that the list containing seen images during registration should not contain elements from list art that contain elements that may have been seen in a prior registration
	 */
	@Test
	public void testSeenImagesIsPopulatedMikonGreyWithLoginMethodTwo() {
		imageFiles.createRegistrationSet(2, listMikonsGrey);
		String getMikonGreyImage = returnListElement(listMikonsGrey);
		assertTrue("Seen images list should be greater than 0", imageFiles.getSeenImages().size() > 0);
		assertTrue("Seen images should not contain the alternative image", !imageFiles.getSeenImages().contains(getMikonGreyImage));
	}
	
	/**
	 * The following tests check that the list containing seen images during registration should not contain elements from list art that contain elements that may have been seen in a prior registration
	 */
	@Test
	public void testSeenImagesIsPopulatedMikonsColourWithLoginMethodTwo() {
		imageFiles.createRegistrationSet(2, listMikonsColour);
		String getMikonsColourImage = returnListElement(listMikonsColour);
		assertTrue("Seen images list should be greater than 0", imageFiles.getSeenImages().size() > 0);
		assertTrue("Seen images should not contain the alternative image", !imageFiles.getSeenImages().contains(getMikonsColourImage));
	}
	
	/**
	 * The following tests check that the list containing seen images during registration should not contain elements from list art that contain elements that may have been seen in a prior registration
	 */
	@Test
	public void testUnseenImagesIsPopulatedArtWithLoginMethodTwo() {
		imageFiles.createRegistrationSet(2, listArt);
		String getArtImage = returnListElement(listArt);
		assertTrue("Unseen images list should be greater than 0", imageFiles.getUnseenImages().size() > 0);
		assertTrue("Unseen images should contain the alternative image", !imageFiles.getUnseenImages().contains(getArtImage));
	}
	
	/**
	 * The following tests check that the list containing seen images during registration should not contain elements from list art that contain elements that may have been seen in a prior registration
	 */
	@Test
	public void testUnseenImagesIsPopulatedMikonGreyWithLoginMethodTwo() {
		imageFiles.createRegistrationSet(2, listMikonsGrey);
		String getMikonGreyImage = returnListElement(listMikonsGrey);
		assertTrue("Unseen images list should be greater than 0", imageFiles.getUnseenImages().size() > 0);
		assertTrue("Unseen images should contain the alternative image", !imageFiles.getUnseenImages().contains(getMikonGreyImage));
	}
	
	/**
	 * The following tests check that the list containing seen images during registration should not contain elements from list art that contain elements that may have been seen in a prior registration
	 */
	@Test
	public void testUnseenImagesIsPopulatedMikonsColourWithLoginMethodTwo() {
		imageFiles.createRegistrationSet(2, listMikonsColour);
		String getMikonsColourImage = returnListElement(listMikonsColour);
		assertTrue("Unseen images list should be greater than 0", imageFiles.getUnseenImages().size() > 0);
		assertTrue("Unseen images should contain the alternative image", !imageFiles.getUnseenImages().contains(getMikonsColourImage));
	}
	/**
	 * Return the element from the field variable as sets do not provide the get method.
	 * @param list
	 * @return
	 */
	private String returnListElement(Set<String> list) {
		Iterator<String> listIterator = list.iterator();
		String image = listIterator.next();
		return image;
		
	}

	/**
	 * The following tests check that the list containing seen images shouldn't be populated for an incorrect login method selection
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testSeenImagesShouldntBePopulated() {
		imageFiles.createRegistrationSet(0, listArt);
		assertTrue("Seen images list should be 0", imageFiles.getSeenImages().size() == 0);
	}
	
	/**
	 * The following tests check that the list containing unseen images shouldn't be populated for an incorrect login method selection
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testUnseenImagesShouldntBePopulated() {
		imageFiles.createRegistrationSet(0, listArt);
		assertTrue("Unseen images list should be 0", imageFiles.getUnseenImages().size() == 0);
	}

	
}

