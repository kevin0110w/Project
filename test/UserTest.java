package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.User;

public class UserTest {
	private User aUser;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		int userid = 1;
		String passwordone = "passwordone";
		String passwordtwo = "passwordtwo";
		String passwordthree = "passwordthree";
		int pictureSet = 1;
		int loginMethod = 1;
		this.aUser = new User(userid, passwordone, passwordtwo, passwordthree, pictureSet, loginMethod);
	}
	
	@Test
	public void testImageSetInitiallyContainingThreePasswords() {
		assertTrue("3 password images added after initialising the list", aUser.getImages().size() == 3);
	}
	
	@Test
	public void testTimeTakenListInitiallyZero() {
		assertEquals(0, aUser.getTimeTaken().size());
	}

	@Test
	public void testAddingPasswordToImageSet()  {
		String passwordOne = "passwordone";
		assertTrue(aUser.getImages().size() == 3);
		this.aUser.addImageToImageSet(passwordOne);
		assertTrue("Should fail as the set already contains an element of the same value", aUser.getImages().size() == 4);
		String passwordone = "a";
		this.aUser.addImageToImageSet(passwordone);
		assertTrue("Should pass as there isn't one containing this element's value", aUser.getImages().size() == 4);
	}
	
	@Test
	public void testTimeTakenInitialSize() {
		assertTrue(aUser.getTimeTaken().size() == 0);
	}
}
