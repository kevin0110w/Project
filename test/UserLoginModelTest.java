package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.UserLoginModel;

public class UserLoginModelTest {
	private UserLoginModel model;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.model = new UserLoginModel();
	}

	@After
	public void tearDown() throws Exception {
		this.model.clear();
	}

	@Test
	public void testAllListVariablesAreInitiallyEmpty() {
		assertTrue("imageFilePaths initially empty", model.getImageFilePaths().size() == 0);
		assertTrue("passwordPaths initially empty", model.getPasswordPaths().size() == 0);
		assertTrue("enteredPassword initially empty", model.getEnteredPasswords().size() == 0);
		assertTrue("timeTaken initially empty", model.getTimeTaken().size() == 0);
		assertTrue("successOfPasswords initially empty", model.getSuccessOfPasswords().size() == 0);
	}
	
	@Test
	public void testClearMethod() {
		populateUserLoginModel();
		assertTrue("imageFilePaths emptied", model.getImageFilePaths().size() == 0);
		assertTrue("passwordPaths emptied", model.getPasswordPaths().size() == 0);
		assertTrue("enteredPassword emptied", model.getEnteredPasswords().size() == 0);
		assertTrue("timeTaken emptied", model.getTimeTaken().size() == 0);
		assertTrue("successOfPasswords emptied", model.getSuccessOfPasswords().size() == 0);
		assertTrue("UserID cleared", model.getUserID() == 0);
		assertTrue("Login Method cleared", model.getLoginMethod() == 0);
		assertTrue("Login Attempt No cleared", model.getLoginAttempt() == 0);
		assertTrue("Picture Set Selection cleared", model.getPictureSetSelection() == 0);
		this.model.clear();
		assertTrue("imageFilePaths emptied", model.getImageFilePaths().size() == 0);
		assertTrue("passwordPaths emptied", model.getPasswordPaths().size() == 0);
		assertTrue("enteredPassword emptied", model.getEnteredPasswords().size() == 0);
		assertTrue("timeTaken emptied", model.getTimeTaken().size() == 0);
		assertTrue("successOfPasswords emptied", model.getSuccessOfPasswords().size() == 0);
		assertTrue("UserID cleared", model.getUserID() == 0);
		assertTrue("Login Method cleared", model.getLoginMethod() == 0);
		assertTrue("Login Attempt No cleared", model.getLoginAttempt() == 0);
		assertTrue("Picture Set Selection cleared", model.getPictureSetSelection() == 0);
	}
	
	@Test
	public void testGettingUserImagesSelection1() {
		this.model.setLoginMethod(1);
		this.model.setUserID(1);
		this.model.setPictureSelection(1);
		this.model.getUsersImages();
		assertTrue("Image files populated and equals 20", this.model.getImageFilePaths().size() == 20);
		assertTrue("Password files populated and equals 3", this.model.getPasswordPaths().size() == 3);
	}
	
	@Test
	public void testGettingUserImagesSelection2() {
		this.model.setLoginMethod(2);
		this.model.setUserID(1);
		this.model.setPictureSelection(1);
		this.model.getUsersImages();
		assertTrue("Image files populated and equals 20", this.model.getImageFilePaths().size() == 20);
		assertTrue("Password files populated and equals 3", this.model.getPasswordPaths().size() == 3);
	}
	
	@Test
	public void testCorrectPassword() {
		List<String> aList = new ArrayList<String>();
		aList.add("file");
		aList.add("file");
		aList.add("file");
		this.model.setEnteredPassword(aList);
		this.model.setPasswordPaths(aList);
		assertTrue(this.model.correctPassword());
	}
	
	@Test
	public void testIncorrectPassword() {
		List<String> aList = new ArrayList<String>();
		aList.add("file");
		aList.add("file");
		aList.add("file");
		this.model.setEnteredPassword(aList);
		List<String> aList2 = new ArrayList<String>();
		aList2.add("abc");
		aList2.add("abc");
		aList2.add("abc");
		this.model.setPasswordPaths(aList2);
		assertTrue(this.model.correctPassword());
	}
	
	@Test
	public void testShuffle() {
		List<String> list1 = new ArrayList<String>();
		list1.add("file");
		list1.add("elif");
		this.model.setImageFilePaths(list1);
		this.model.setUserID(10);
		this.model.setPictureSelection(2);
		this.model.setLoginMethod(2);
		this.model.setLoginAttempt();
		this.model.setLastLoginSuccessful(true);
		this.model.shuffleDecoyImages();
		assertTrue("images shuffled", this.model.isShuffled());
		this.model.setLastLoginSuccessful(false);
		this.model.setShuffled(false);
		this.model.shuffleDecoyImages();
		assertTrue("images shuffled", this.model.isShuffled());
	}
	
	public void populateUserLoginModel() {
		populateImagesList(generatePasswordsList());
		populatePasswordPathsList(generatePasswordsList());
		populateEnteredPasswords(generatePasswordsList());
		populateUserID();
		populateUserLoginMethod();
		populateUserLoginAttemptNo();
		populateUserPictureSetSelection();
		populateTimeTaken();
		populateSuccessOfPasswords();
		populateLastLoginSuccesful();
	}
	
	private void populateUserLoginMethod() {
		int loginMethod = 1;
		this.model.setLoginMethod(loginMethod);
		
	}

	private void populateUserPictureSetSelection() {
		int pictureSetSelection = 1;
		this.model.setPictureSelection(pictureSetSelection);
	}

	private void populateUserLoginAttemptNo() {
		int loginAttemptNo = 2;
		this.model.setLoginAttemptNo(loginAttemptNo);
		
	}

	private void populateLastLoginSuccesful() {
		this.model.setLastLoginSuccessful(true);
		
	}

	private void populateSuccessOfPasswords() {
		int num = 1;
		List<Integer> successOfPasswords = new ArrayList<Integer>();
		successOfPasswords.add(num);
		successOfPasswords.add(num);
		successOfPasswords.add(num);
		this.model.setSuccessOfPasswords(successOfPasswords);
	}

	private void populateTimeTaken() {
		double num = 0.1;
		List<Double> timeTaken = new ArrayList<Double>();
		timeTaken.add(num);
		timeTaken.add(num);
		timeTaken.add(num);
		this.model.setTimeTaken(timeTaken);
		
	}
	
	private List<String> generatePasswordsList() {
		String passwordOne = "passwordOne";
		String passwordTwo = "passwordTwo";
		String passwordThree = "passwordThree";
		List<String> list = new ArrayList<String>();
		list.add(passwordOne);
		list.add(passwordTwo);
		list.add(passwordThree);
		return list;
	}

	private void populateEnteredPasswords(List<String> list) {
		List<String> enteredPasswords = new ArrayList<String>();
		enteredPasswords.addAll(list);
		this.model.setEnteredPassword(enteredPasswords);	
	}

	private void populateUserID() {
		int userID = 1;
		this.model.setUserID(userID);
	}

	private void populatePasswordPathsList(List<String> list) {
		List<String> usersPasswords = new ArrayList<String>();
		usersPasswords.addAll(list);
		this.model.setPasswordPaths(usersPasswords);
		
	}

	private void populateImagesList(List<String> list) {
		List<String> imageFiles = new ArrayList<String>();
		int counter = 1;
		int numberOfImages = 20;
		imageFiles.addAll(list);
		while (imageFiles.size() <= numberOfImages) {
			imageFiles.add("UserImage " + counter);
			counter++;
		}
		this.model.setImageFilePaths(imageFiles);
	}
}
