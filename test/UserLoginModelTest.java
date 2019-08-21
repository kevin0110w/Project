package test;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.User;
import main.UserLoginModel;

public class UserLoginModelTest {
	private UserLoginModel model;
	private User user;
	private List<String> enteredPassword;
	private List<Double> timeTaken;
	private List<Integer> successOfPasswords;
	private int loginAttemptNo;
	private boolean correctPassword;
	
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
		assertTrue("imageFilePaths initially empty", model.getChallengeSet().size() == 0);
		assertTrue("passwordPaths initially empty", model.getPasswordPaths().size() == 0);
		assertTrue("enteredPassword initially empty", model.getEnteredPasswords().size() == 0);
		assertTrue("timeTaken initially empty", model.getTimeTaken().size() == 0);
		assertTrue("successOfPasswords initially empty", model.getSuccessOfPasswords().size() == 0);
	}
	
	@Test
	public void testClearMethod() {
		populateUserLoginModel();
		assertFalse("imageFilePaths emptied", model.getChallengeSet().size() == 0);
		assertFalse("passwordPaths emptied", model.getPasswordPaths().size() == 0);
		assertFalse("enteredPassword emptied", model.getEnteredPasswords().size() == 0);
		assertFalse("timeTaken emptied", model.getTimeTaken().size() == 0);
		assertFalse("successOfPasswords emptied", model.getSuccessOfPasswords().size() == 0);
		assertFalse("UserID cleared", model.getUserID() == null);
		assertFalse("Login Method cleared", model.getLoginMethod() == 0);
		assertFalse("Login Attempt No cleared", model.getLoginAttemptNo() == 0);
		assertFalse("Picture Set Selection cleared", model.getPictureSetSelection() == 0);
		this.model.clear();
		assertTrue("imageFilePaths emptied", model.getChallengeSet().size() == 0);
		assertTrue("passwordPaths emptied", model.getPasswordPaths().size() == 0);
		assertTrue("enteredPassword emptied", model.getEnteredPasswords().size() == 0);
		assertTrue("timeTaken emptied", model.getTimeTaken().size() == 0);
		assertTrue("successOfPasswords emptied", model.getSuccessOfPasswords().size() == 0);
		assertTrue("UserID cleared", model.getUserID() == null);
		assertTrue("Login Method cleared", model.getLoginMethod() == 0);
		assertTrue("Login Attempt No cleared", model.getLoginAttemptNo() == 0);
		assertTrue("Picture Set Selection cleared", model.getPictureSetSelection() == 0);
	}
	
	@Test
	public void testGettingUserImagesSelection1() {
		this.model.setLoginMethod(1);
		this.model.setUserID("1");
		this.model.setPictureSetSelection(1);
		this.model.getUsersChallengeSet();
		this.model.formPasswordPaths();
		assertTrue("Image files populated and equals 20", this.model.getChallengeSet().size() == 20);
		assertTrue("Password files populated and equals 3", this.model.getPasswordPaths().size() == 3);
	}
	
	@Test
	public void testGettingUserImagesSelection2() {
		this.model.setLoginMethod(2);
		this.model.setUserID("1");
		this.model.setPictureSetSelection(1);
		this.model.getUsersChallengeSet();
		this.model.formPasswordPaths();
		assertTrue("Image files populated and equals 20", this.model.getChallengeSet().size() == 20);
		assertTrue("Password files populated and equals 3", this.model.getPasswordPaths().size() == 3);
	}
	
	@Test
	public void testCorrectPassword() {
		List<String> aList = new ArrayList<String>();
		aList.add("file1");
		aList.add("file2");
		aList.add("file3");
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
		assertFalse(this.model.correctPassword());
	}
	
	@Test
	public void testSetInitialTime() {
		assertEquals("Should fail as null value", LocalDateTime.now(), this.model.getInitialTime());
		this.model.setInitialTime();
		assertEquals(LocalDateTime.now(), this.model.getInitialTime());
	}
	@Test
	public void testAddTime() {
		this.model.setInitialTime();
		assertTrue(this.model.getTimeTaken().size() == 0);
		this.model.addTime();
		assertTrue(this.model.getTimeTaken().size() == 1);
	}
	
	@Test
	public void testShuffle() {
		List<String> list1 = new ArrayList<String>();
		list1.add("file");
		list1.add("elif");
		this.model.setChallengeSet(list1);
		this.model.setUserID("1");
		this.model.setPictureSetSelection(2);
		this.model.setLoginMethod(2);
		this.model.setLoginAttemptNoFromDB();
		this.model.setLastLoginSuccesful(true);
		this.model.shuffleDecoyImages();
		assertTrue("images shuffled", this.model.getShuffled());
		this.model.setLastLoginSuccesful(false);
		this.model.setShuffled(false);
		this.model.shuffleDecoyImages();
		assertFalse("images shuffled", this.model.getShuffled());
	}
	
	@Test
	public void testIsValid() {
		deleteTestUserRegistration();
		User user = createUserDetails();
		model.setUserID(user.getUserid());
		model.setPictureSetSelection(user.getPictureSet());
		model.setLoginMethod(user.getLoginMethod());
		model.getDb().addUserToDatabase(user);
		boolean isValid = model.returnIsRegisteredUser();
		assertTrue(isValid);
		deleteTestUserRegistration();
	}
	
	@Test
	public void testAddSuccessOfPasswords() {
		populateUserLoginModel();
		this.model.getSuccessOfPasswords().clear();
		List<Integer> success = new ArrayList<Integer>();
		success.add(1);
		success.add(1);
		success.add(1);
		this.model.addSuccessOfPasswords();
		assertEquals(success, model.getSuccessOfPasswords());
		
	}
	
	@Test
	public void testGetMostRecentLoginSuccess() {
		this.user = createUserDetails();
		deleteTestUserRegistration();
		model.setUserID(user.getUserid());
		model.setPictureSetSelection(user.getPictureSet());
		model.setLoginMethod(user.getLoginMethod());
		deleteTestUserLoginAttempt();
		model.getDb().addUserToDatabase(user);
		addLoginAttemptToDatabase();
		int successful = model.getMostRecentLoginSuccess();
		assertTrue(successful == 1);
		deleteTestUserRegistration();
		deleteTestUserLoginAttempt();
	}
	
	@Test
	public void testReturnMostRecentLoginSuccess() {
		this.user = createUserDetails();
		deleteTestUserRegistration();
		model.setUserID(user.getUserid());
		model.setPictureSetSelection(user.getPictureSet());
		model.setLoginMethod(user.getLoginMethod());
		deleteTestUserLoginAttempt();
		model.getDb().addUserToDatabase(user);
		addLoginAttemptToDatabase();
		model.returnMostRecentLoginSuccess();
		assertTrue(model.getLastLoginSuccesful());
		deleteTestUserRegistration();
		deleteTestUserLoginAttempt();
	}
	
	@Test
	public void testSetLoginAttempt() {
		this.user = createUserDetails();
		deleteTestUserRegistration();
		model.setUserID(user.getUserid());
		model.setPictureSetSelection(user.getPictureSet());
		model.setLoginMethod(user.getLoginMethod());
		deleteTestUserLoginAttempt();
		model.getDb().addUserToDatabase(user);
		addLoginAttemptToDatabase();
		int loginAttemptNo = model.getDb().getRecentLoginAttemptNo(user.getUserid(), user.getLoginMethod(), user.getPictureSet());
		assertEquals(1, loginAttemptNo);
		model.setLoginAttemptNoFromDB();
		loginAttemptNo = model.getLoginAttemptNo();
		assertEquals("loginAttemptNo has been incremented via getLoginAttempt()", 1, loginAttemptNo);
		deleteTestUserRegistration();
		deleteTestUserLoginAttempt();
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
		this.model.setPictureSetSelection(pictureSetSelection);
	}

	private void populateUserLoginAttemptNo() {
		int loginAttemptNo = 2;
		this.model.setLoginAttemptNo(loginAttemptNo);
		
	}

	private void populateLastLoginSuccesful() {
		this.model.setLastLoginSuccesful(true);
		
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
		String userID = "1";
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
		this.model.setChallengeSet(imageFiles);
	}
	
	/**
	 * Helper method to creating a new user
	 * @return
	 */
	public User createUserDetails() {
		this.user = new User("999999", "a", "b", "c", 1, 1, 0.0);
		List<Double> time = new ArrayList<Double>();
		time.add(0.0);
		time.add(0.0);
		time.add(0.0);
		user.setTimeTaken(time);
		Set<String> images = new HashSet<String>();
		int n = 17;
		int counter = 1;
		while (images.size() < n) {
			images.add("password" + " " + counter);
			user.addImageToImageSet("password" + " " + counter);
			counter++;
		}
		return user;
	}
	/*
	 * Helper method to delete a user from a database and avoid any primary key exceptions
	 */
	public void deleteTestUserRegistration() {
		model.getDb().connectToDatabase();
		String command = "Delete From UserRegistrations Where UserID = ? AND PictureSet = ? AND LoginMethod = ?";
		try {
			PreparedStatement st = model.getDb().getConnection().prepareStatement(command);
			st.setString(1, "999999");
			st.setInt(2, 1);
			st.setInt(3, 1);
			st.executeUpdate();
			st.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			
		}
	}
	
	public void addLoginAttemptToDatabase() {
		this.enteredPassword = new ArrayList<String>();
		this.enteredPassword = createEnteredPasswords();
		this.timeTaken = new ArrayList<Double>();
		this.timeTaken = createTimeTaken();
		this.successOfPasswords = new ArrayList<Integer>();
		this.successOfPasswords = createSuccess();
		this.correctPassword = true;
		this.loginAttemptNo = 1;
		model.getDb().addLoginAttemptToDatabase(user.getUserid(), user.getLoginMethod(), user.getPictureSet(), enteredPassword, timeTaken, user.getOverallTimeTaken(), successOfPasswords, correctPassword, loginAttemptNo);
	}
	
	public void deleteTestUserLoginAttempt() {
		model.getDb().connectToDatabase();
		String command = "Delete From UserLoginAttempts Where UserID = ? AND PictureSet = ? AND LoginMethod = ?";
		try {
			PreparedStatement st = model.getDb().getConnection().prepareStatement(command);
			st.setString(1, "999999");
			st.setInt(2, 1);
			st.setInt(3, 1);
			st.executeUpdate();
			st.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			model.getDb().closeConnection();
		}
	}
	private List<Integer> createSuccess() {
		List<Integer> successOfPasswords = new ArrayList<Integer>();
		successOfPasswords.add(1);
		successOfPasswords.add(1);
		successOfPasswords.add(1);
		return successOfPasswords;
	}

	private List<Double> createTimeTaken() {
		List<Double> timeTaken = new ArrayList<Double>();
		timeTaken.add(0.05);
		timeTaken.add(0.05);
		timeTaken.add(0.05);
		return timeTaken;
	}

	private List<String> createEnteredPasswords() {
		List<String> enteredPasswords = new ArrayList<String>();
		enteredPasswords.add("a");
		enteredPasswords.add("b");
		enteredPasswords.add("c");
		return enteredPasswords;
	}

}
