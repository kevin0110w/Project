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
		assertTrue(aUser.getImages().size() == 3);
	}
	
//	@Test
//	public void testSeenDecoySetInitiallyZero() {
//		assertTrue(aUser.getSeenDecoys().size() == 0);
//	}
//	
//	@Test
//	public void testUnseenDecoySetInitiallyZero() {
//		assertTrue(aUser.getUnseenDecoys().size() == 0);
//	}
	
	@Test
	public void testAddingPasswordToImageSet()  {
		String passwordOne = "passwordone";
		assertTrue(aUser.getImages().size() == 3);
		this.aUser.addImageToImageSet(passwordOne);
		assertTrue(aUser.getImages().size() == 4);
		String passwordone = "a";
		this.aUser.addImageToImageSet(passwordone);
		assertTrue(aUser.getImages().size() == 4);
	}
	
//	@Test
//	public void addFileToSeenDecoySet()  {
//		String passwordOne = "passwordone";
//		this.aUser.addImageToSeenDecoySet(passwordOne);
//		assertTrue(aUser.getDecoySet().size() == 1);
//		String passwordone = "a";
//		this.aUser.addImageToSeenDecoySet(passwordone);
//		assertTrue(aUser.getDecoySet().size() == 1);
//	}
//	
//	@Test
//	public void addFileToUnseenDecoySet()  {
//		String passwordOne = "passwordone";
//		this.aUser.addImageToUnseenDecoySet(passwordOne);
//		assertTrue(aUser.getDecoySet().size() == 1);
//		String passwordone = "a";
//		this.aUser.addImageToUnseenDecoySet(passwordone);
//		assertTrue(aUser.getDecoySet().size() == 1);
//	}
	
	@Test
	public void testTimeTakenInitialSize() {
		assertTrue(aUser.getTimeTaken().size() == 0);
	}
}
