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
/**
 * This class will test all publicly accessible methods in the user login model class
 *
 */
public class UserLoginModelTest {
	
	private UserLoginModel model;
	private User user;
	private List<String> enteredPassword;
	private List<Double> timeTaken;
	private List<Integer> successOfPasswords;
	private int loginAttemptNo;
	private boolean correctPassword = true;
	private String userID = "999999", passwordOne =  "a", passwordTwo = "b", passwordThree = "c";
	private int loginMethod = 1, pictureSet = 1;
	private double overallTime = 0.0;
	private List<String> images;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.model = new UserLoginModel();
		this.user = createUserDetails();
		this.model.getDb().addUserToDatabase(user);
	}

	@After
	public void tearDown() throws Exception {
		deleteTestUserRegistration();
		deleteTestUserLoginAttempt();
		this.model.clear();
	}

	/**
	 * This test checks that all field variables of type list all initially have 0 elements
	 * The test passes if it asserts true that all list are 0 to begin with
	 */
	@Test
	public void testAllListVariablesAreInitiallyEmpty() {
		assertTrue("imageFilePaths initially empty", model.getChallengeSet().size() == 0);
		assertTrue("passwordPaths initially empty", model.getPasswordPaths().size() == 0);
		assertTrue("enteredPassword initially empty", model.getEnteredPasswords().size() == 0);
		assertTrue("timeTaken initially empty", model.getTimeTaken().size() == 0);
		assertTrue("successOfPasswords initially empty", model.getSuccessOfPasswords().size() == 0);
	}
	
	/**
	 * This test checks that the clear method is working
	 * Once the model is populated, the lists shouldn't be empty. After the clear method is called, the list should all have 0 elements again.
	 * The test passes if it asserts that the lists have 0 elements.
	 */
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
	
	/**
	 * This method checks the getUsersChallengeSet method within the getUsersImages method
	 * The test checks that the challenge set returned from the database is equal to the list of image paths that was used to populate the user in the database
	 * The test should pass to reflect that the two are equal and contains 20 elements
	 */
	@Test
	public void testGettingUserImagesSelection() {
		setUserDetailsInModel();
		Set<String> imagesFromModel = new HashSet<String>();
		Set<String> imagesFromUser = new HashSet<String>();
		imagesFromModel.addAll(model.getUsersImages());
		imagesFromUser.addAll(this.user.getImages());
		assertTrue("Image files populated and equals 20", this.model.getChallengeSet().size() == 20);
		assertEquals("Local Image Files should equal challengeSet", imagesFromUser, imagesFromModel);
	}
	
	/**
	 * This method checks the formPasswordPaths method within the getUsersImages method
	 * The test checks that the challenge set returned from the database is equal to the list of image paths that was used to populate the user in the database
	 * The test should pass to reflect that the two are equal and contains 20 elements
	 */
	@Test
	public void testFormPasswordPaths() {
		setUserDetailsInModel();
		List<String> c = model.getUsersImages();
		List<String> passwords = model.getPasswordPaths();
		List<String> fakePasswords = createEnteredPasswords();
		assertEquals("Password Paths should equal 3", 3, passwords.size());
		assertEquals("Local Password Paths should equal the 3 retrieved from db", fakePasswords, passwords);
	}
	
	/**
	 * Helper method to set userid, picture set and login method that were used to add a fake
	 * user to the database
	 */
	private void setUserDetailsInModel() {
		this.model.setUserID(userID);
		this.model.setPictureSetSelection(pictureSet);
		this.model.setLoginMethod(loginMethod);
	}

	/**
	 * This method tests the correctPassword() method in the user login model class
	 * This method checks whether the selected passwords is the same as the pass images
	 * This test will populate the selected password and pass images with the same data
	 * so if it asserts true, it passes to show the method works as intended.
	 */
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
	
	/**
	 * This method tests the correctPassword() that it checks for different values for
	 * entered passwords and actual passwords from the db.
	 * If it assertsFalse, it passes as it shows that it can see an incorrect password
	 */
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
	
	/**
	 * This method checks the set initial time method in the login model.
	 * It passes because the initial time is set as the same as localdatetime.now();
	 */
	@Test
	public void testSetInitialTime() {
		assertEquals("Should fail as null value", null, this.model.getInitialTime());
		this.model.setInitialTime();
		assertEquals(LocalDateTime.now(), this.model.getInitialTime());
	}
	
	/**
	 * The method add time will add a timestamp when it is called, i.e. when an image is selected.
	 * This test checks that the timestamp is added based on the number of elements that are in the list that holds the times
	 * This test passes if it asserts true that the number of elements inside the element is increased.
	 */
	@Test
	public void testAddTime() {
		this.model.clear();
		this.model.setInitialTime();
		assertTrue(this.model.getTimeTaken().size() == 0);
		this.model.addTime();
		assertTrue(this.model.getTimeTaken().size() == 1);
	}
	
	/**
	 * This test checks the shuffle method which should shuffle the images 
	 * if the last login was successful and it hasn't been shuffled before
	 * This test passes if it asserts that the boolean variable, shuffled, has been set to true
	 */
	@Test
	public void testShuffleIfLastLoginSuccessful() {
		this.model.setLastLoginSuccesful(true);
		this.model.setShuffled(false);
		setUserDetailsInModel();
		List<String> challengeSet = this.model.getUsersImages();
		assertTrue("Assert that images have been shuffled", this.model.getShuffled());
	}
	
	/**
	 * This test checks the shuffle method which should not shuffle the images 
	 * if the last login was unsuccessful and it hasn't been shuffled before
	 * This test passes if it asserts that the boolean variable, shuffled, has been set to false
	 */
	@Test
	public void testShuffleIfLastLoginUnsuccessful() {
		this.model.setLastLoginSuccesful(false);
		this.model.setShuffled(false);
		this.model.setLoginAttemptNo(2);
		setUserDetailsInModel();
		List<String> challengeSet = this.model.getUsersImages();
		assertFalse("Assert that images have not been shuffled", this.model.getShuffled());
	}
	
	/**
	 * This test checks the shuffle method which should shuffle the images 
	 * if the last login was unsuccessful and it hasn't been shuffled before but this is the first time for
	 * this account to be attempted to log in
	 * This test passes if it asserts that the boolean variable, shuffled, has been set to true
	 */
	@Test
	public void testShuffleIfLoginAttemptNumber1() {
		this.model.setLastLoginSuccesful(false);
		this.model.setShuffled(false);
		setUserDetailsInModel();
		this.model.setLoginAttemptNo(1);
		List<String> challengeSet = this.model.getUsersImages();
		assertTrue("Assert that images are shuffled", this.model.getShuffled());
	}
	
	/**
	 * This test checks the shuffle method which should shuffle the images 
	 * if the last login was unsuccessful and it hasn't been shuffled before but there has been a previous login
	 * attempt, then the images should not be shuffled
	 * This test passes if it asserts that the boolean variable, shuffled, has been set to false
	 */
	@Test
	public void testShuffleIfLoginAttemptNumber2() {
		this.model.setLastLoginSuccesful(false);
		this.model.setShuffled(false);
		setUserDetailsInModel();
		this.model.setLoginAttemptNo(2);
		List<String> challengeSet = this.model.getUsersImages();
		assertFalse("Assert that images are not shuffled", this.model.getShuffled());
	}
	
	/**
	 * This tests checks the return is registered user method in the model class
	 * If a user is already registered, it's value should be retrievable from the database
	 * which sets a boolean to true. Otherwise, it sets it to false.
	 * This test passes if it asserts true, that the test user has been added during the set up
	 */
	@Test
	public void testReturnIsRegisteredUser() {
		model.setUserID(user.getUserid());
		model.setPictureSetSelection(user.getPictureSet());
		model.setLoginMethod(user.getLoginMethod());
		boolean isValid = model.returnIsRegisteredUser();
		assertTrue(isValid);
	}
	
	/**
	 * This method checks that 
	 */
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
	
	/**
	 * This method checks the getMostRecentLoginSuccess from the model.
	 * The method returns either a 1 or a 0 from the database.
	 * A successful login attempt was added to the database and should return 1 when
	 * the select query is executed.
	 * This test passes if it asserts is equal to the number that was pushed to the database
	 */
	@Test
	public void testGetMostRecentLoginSuccess() {
		model.setUserID(user.getUserid());
		model.setPictureSetSelection(user.getPictureSet());
		model.setLoginMethod(user.getLoginMethod());
		addLoginAttemptToDatabase();
		int successful = model.getMostRecentLoginSuccess();
		assertTrue(successful == 1);
	}
	
	/**
	 * This method checks the getMostRecentLoginSuccess from the model.
	 * The method returns either a 1 or a 0 from the database.
	 * An unsuccessful login attempt was added to the database by setting the correctPassword variable to false and should return 0 when
	 * the select query is executed.
	 * This test passes if it asserts is equal to the number that was pushed to the database
	 */
	@Test
	public void testGetMostRecentLoginFailure() {
		model.setUserID(user.getUserid());
		model.setPictureSetSelection(user.getPictureSet());
		model.setLoginMethod(user.getLoginMethod());
		this.correctPassword = false;
		addLoginAttemptToDatabase();
		int successful = model.getMostRecentLoginSuccess();
		assertTrue(successful == 0);
	}
	
	/**
	 * This method checks the returnMostRecentLoginSuccess method from the model.
	 * The method returns either a 1 or a 0 from the database which is converted to a boolean, true and false respectively.
	 * A successful login attempt was added to the database by setting the correctPassword variable to true 
	 * and should therefore return true.
	 * This test passes if it asserts that it is true
	 */
	@Test
	public void testReturnMostRecentLoginSuccess() {
		model.setUserID(user.getUserid());
		model.setPictureSetSelection(user.getPictureSet());
		model.setLoginMethod(user.getLoginMethod());
		addLoginAttemptToDatabase();
		model.returnMostRecentLoginSuccess();
		assertTrue(model.getLastLoginSuccesful());
	}
	/**
	 * This method checks the returnMostRecentLoginSuccess method from the model.
	 * The method returns either a 1 or a 0 from the database which is converted to a boolean, true and false respectively.
	 * An unsuccessful login attempt was added to the database by setting the correctPassword variable to false 
	 * and should therefore return false.
	 * This test passes if it asserts that it is false
	 */
	@Test
	public void testReturnMostRecentLoginFailure() {
		model.setUserID(user.getUserid());
		model.setPictureSetSelection(user.getPictureSet());
		model.setLoginMethod(user.getLoginMethod());
		this.correctPassword = false;
		addLoginAttemptToDatabase();
		model.returnMostRecentLoginSuccess();
		assertFalse(model.getLastLoginSuccesful());
	}
	
	/**
	 * This tests checks the setLoginAttemptNoFromDB() in the userloginmodel class.
	 * If there hasn't been a login attempt recorded, it should set the loginattemptno
	 * variable in the model class to 1. Otherwise, it should retrieve the loginattemptno from
	 * the database and set the value to the variable, loginattemptno.
	 * This test passes if it asserts that loginAttemptNo is 0.
	 */
	@Test
	public void testSetLoginAttemptNoFromDBWithoutPriorLoginAttempt() {
		this.setUserDetailsInModel();
		model.setLoginAttemptNoFromDB();
		assertEquals("As there hasn't been a previous login attempt, loginAttemptNo should be 0", 1, this.model.getLoginAttemptNo());
	}
	
	/**
	 * This tests checks the setLoginAttemptNoFromDB() in the userloginmodel class.
	 * If there hasn't been a login attempt recorded, it should set the loginattemptno
	 * variable in the model class to 1. Otherwise, it should retrieve the loginattemptno from
	 * the database and set the value to the variable, loginattemptno.
	 * This test passes if it asserts that loginAttemptNo is incremented and set to 2.
	 */
	@Test
	public void testSetLoginAttemptNoFromDBWithPriorLoginAttempt() {
		this.setUserDetailsInModel();
		this.addLoginAttemptToDatabase();
		model.setLoginAttemptNoFromDB();
		assertEquals("As there has been 1 previous login attempt, loginAttemptNo should be 2", 2, this.model.getLoginAttemptNo());
	}
	/**
	 * A method to populate the model class with fake data.
	 */
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
	
	/**
	 * A method to set the user login method in the model
	 */
	private void populateUserLoginMethod() {
		this.model.setLoginMethod(loginMethod);
	}

	/**
	 * A method to set the picture set selection in the model
	 */
	private void populateUserPictureSetSelection() {
		this.model.setPictureSetSelection(pictureSet);
	}
	/**
	 * A helper method to set the login attempt no in model
	 */
	private void populateUserLoginAttemptNo() {
		int loginAttemptNo = 2;
		this.model.setLoginAttemptNo(loginAttemptNo);
		
	}
	/**
	 * A helper method to set the model that the last login was successful
	 */
	private void populateLastLoginSuccesful() {
		this.model.setLastLoginSuccesful(true);
		
	}
	
	/**
	 * A helper method to populate the success of passwords in the model
	 */
	private void populateSuccessOfPasswords() {
		int num = 1;
		List<Integer> successOfPasswords = new ArrayList<Integer>();
		successOfPasswords.add(num);
		successOfPasswords.add(num);
		successOfPasswords.add(num);
		this.model.setSuccessOfPasswords(successOfPasswords);
	}

	/**
	 * A helper method to setting the list of time taken in the model
	 */
	private void populateTimeTaken() {
		double num = 0.1;
		List<Double> timeTaken = new ArrayList<Double>();
		timeTaken.add(num);
		timeTaken.add(num);
		timeTaken.add(num);
		this.model.setTimeTaken(timeTaken);
		
	}
	
	/**
	 * A helper method to creating a list of strings of fake data
	 * @return
	 */
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
	/**
	 * A helper method to set the entered passwords in the model
	 */
	private void populateEnteredPasswords(List<String> list) {
		List<String> enteredPasswords = new ArrayList<String>();
		enteredPasswords.addAll(list);
		this.model.setEnteredPassword(enteredPasswords);	
	}

	/**
	 * A helper method to set the user id in the model
	 */
	private void populateUserID() {
		this.model.setUserID(userID);
	}

	private void populatePasswordPathsList(List<String> list) {
		List<String> usersPasswords = new ArrayList<String>();
		usersPasswords.addAll(list);
		this.model.setPasswordPaths(usersPasswords);
	}
	
	/**
	 * A helper method to create a fake challenge set
	 * @param list
	 */
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
	 * Helper method to create a new user
	 * @return
	 */
	public User createUserDetails() {
		this.user = new User(userID, passwordOne, passwordTwo, passwordThree, pictureSet, loginMethod, overallTime);
		List<Double> time = new ArrayList<Double>();
		time.add(0.0);
		time.add(0.0);
		time.add(0.0);
		user.setTimeTaken(time);
		this.images = new ArrayList<String>();
		int n = 17;
		int counter = 1;
		while (images.size() < n) {
			images.add("password" + " " + counter);
			user.addImageToImageSet("password" + " " + counter);
			counter++;
		}
		return user;
	}
	
	/**
	 * Helper method to delete a user registration from a database and avoid any primary key exceptions
	 */
	public void deleteTestUserRegistration() {
		model.getDb().connectToDatabase();
		String command = "Delete From UserRegistrations Where UserID = ? AND PictureSet = ? AND LoginMethod = ?";
		try {
			PreparedStatement st = model.getDb().getConnection().prepareStatement(command);
			st.setString(1, userID);
			st.setInt(2, pictureSet);
			st.setInt(3, loginMethod);
			st.executeUpdate();
			st.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			
		}
	}
	/**
	 * A helper method to add a user's login attempt to the database
	 */
	public void addLoginAttemptToDatabase() {
		this.enteredPassword = new ArrayList<String>();
		this.enteredPassword = createEnteredPasswords();
		this.timeTaken = new ArrayList<Double>();
		this.timeTaken = createTimeTaken();
		this.successOfPasswords = new ArrayList<Integer>();
		this.successOfPasswords = createSuccess();
		this.loginAttemptNo = 1;
		model.getDb().addLoginAttemptToDatabase(user.getUserid(), user.getLoginMethod(), user.getPictureSet(), enteredPassword, timeTaken, user.getOverallTimeTaken(), successOfPasswords, correctPassword, loginAttemptNo);
	}
	
	/**
	 * A helper method to delete the test user's login attempt
	 */
	public void deleteTestUserLoginAttempt() {
		model.getDb().connectToDatabase();
		String command = "Delete From UserLoginAttempts Where UserID = ? AND PictureSet = ? AND LoginMethod = ?";
		try {
			PreparedStatement st = model.getDb().getConnection().prepareStatement(command);
			st.setString(1, userID);
			st.setInt(2, pictureSet);
			st.setInt(3, loginMethod);
			st.executeUpdate();
			st.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			model.getDb().closeConnection();
		}
	}
	/**
	 * A helper method to create a list of integers to represent 3 successful image selections
	 * @return
	 */
	private List<Integer> createSuccess() {
		List<Integer> successOfPasswords = new ArrayList<Integer>();
		successOfPasswords.add(1);
		successOfPasswords.add(1);
		successOfPasswords.add(1);
		return successOfPasswords;
	}

	/**
	 * A helper method to create a list of double to represent the time taken to register 3 image selections
	 * @return
	 */
	private List<Double> createTimeTaken() {
		List<Double> timeTaken = new ArrayList<Double>();
		timeTaken.add(0.05);
		timeTaken.add(0.05);
		timeTaken.add(0.05);
		return timeTaken;
	}
	/**
	 * A helper method to create a list of strings to represent 3 entered passwords
	 * @return
	 */
	private List<String> createEnteredPasswords() {
		List<String> enteredPasswords = new ArrayList<String>();
		enteredPasswords.add("a");
		enteredPasswords.add("b");
		enteredPasswords.add("c");
		return enteredPasswords;
	}

}
