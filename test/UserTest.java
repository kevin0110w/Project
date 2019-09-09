package test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.User;
/**
 * This class will test all publicly available methods in the user class
 * @author 0808148w
 *
 */
public class UserTest {
	private User aUser;
	private String userid, passwordOne, passwordTwo, passwordThree;
	private int loginMethod, pictureSet;
	private double overallTimeTaken;
	private Set<String> images;
	private List<Double> timeTaken;

	/**
	 * Create a new user for each test case
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		userid = "1";
		passwordOne = "passwordone";
		passwordTwo = "passwordtwo";
		passwordThree = "passwordthree";
		pictureSet = 1;
		loginMethod = 1;
		overallTimeTaken = 0.0;
		this.aUser = new User(userid, passwordOne, passwordTwo, passwordThree, pictureSet, loginMethod, overallTimeTaken);
	}
	
	/**
	 * This method tests that a user's images are added to the images field immediately after a user object is created
	 * This method passes if it asserts true that the images list contains 3 elements reflecting each password
	 */
	@Test
	public void testImageSetInitiallyContainingThreePasswords() {
		assertTrue("3 password images added after initialising the list", aUser.getImages().size() == 3);
	}
	
	
	/**
	 * This methods tests that the user's images list initially contains password One
	 * This test passes if it asserts true that the images list initially contains password One
	 */
	@Test
	public void testImageSetInitiallyContainsPasswordOne() {
		assertTrue("The images list contains password One", aUser.getImages().contains(passwordOne));
	}
	
	/**
	 * This methods tests that the user's images list initially contains password two
	 * This test passes if it asserts true that the images list initially contains password two
	 */
	@Test
	public void testImageSetInitiallyContainsPasswordTwo() {
		assertTrue("The images list contains password Two", aUser.getImages().contains(passwordTwo));
	}
	
	/**
	 * This methods tests that the user's images list initially contains password three
	 * This test passes if it asserts true that the images list initially contains password three
	 */
	@Test
	public void testImageSetInitiallyContainsPasswordThree() {
		assertTrue("The images list contains password Three", aUser.getImages().contains(passwordThree));
	}
	@Test
	public void testTimeTakenListInitiallyZero() {
		assertEquals(0, aUser.getTimeTaken().size());
	}

	/**
	 * This method tests the add Image to image set method
	 * This test passes if it asserts true that the size of the image list has increased by one to 4.
	 */
	@Test
	public void testAddingPasswordToImageSet() {
		String passwordone = "a";
		this.aUser.addImageToImageSet(passwordone);
		assertTrue("Successfully add another fiction file path of an image", aUser.getImages().size() == 4);
	}
	
	/**
	 * This method tests the add Image to image set method if adding a dupllicate element
	 * This test passes if it asserts false that the size of the image list has increased by one to 4. Set cannot contain duplicate values
	 */
	@Test 
	public void testAddingDuplicatePasswordToImageSet()  {
		String passwordOne = "passwordone";
		assertTrue(aUser.getImages().size() == 3);
		this.aUser.addImageToImageSet(passwordOne);
		assertFalse("Cannot add a duplicate value", aUser.getImages().size() == 4);
	}
	
	/**
	 * This method tests that the list storing times taken is initially 0
	 * This test passes if it asserts true that the list contains 0 elements at the beginning
	 */
	@Test
	public void testTimeTakenInitialSize() {
		assertTrue(aUser.getTimeTaken().size() == 0);
	}
}
