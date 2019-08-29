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

public class UserTest {
	private User aUser;
	private String userid, passwordOne, passwordTwo, passwordThree;
	private int loginMethod, pictureSet;
	private double overallTimeTaken;
	private Set<String> images;
	private List<Double> timeTaken;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

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
		assertTrue("Cannot add a duplicate value", aUser.getImages().size() == 4);
		String passwordone = "a";
		this.aUser.addImageToImageSet(passwordone);
		assertTrue("Not adding a duplicate value", aUser.getImages().size() == 4);
	}
	
	@Test
	public void testTimeTakenInitialSize() {
		assertTrue(aUser.getTimeTaken().size() == 0);
	}
}
