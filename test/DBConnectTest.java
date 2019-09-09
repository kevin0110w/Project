package test;

import main.User;
import main.DBConnect;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This class contains a set of test methods to test publicly availables methods in the dbconnect class
 * @author 0808148w
 *
 */
public class DBConnectTest {
	private DBConnect db;
	private User user;
	private List<String> enteredPassword;
	private List<Double> timeTaken;
	private List<Integer> successOfPasswords;
	private boolean correctPassword;
	private int loginAttemptNo;
	private boolean recentLoginAttemptSuccessful = true;
	private double overallTimeTaken;
	private String userid = "999999", passwordOne = "a", passwordTwo = "b", passwordThree = "c";
	private int loginmethod = 1, pictureset = 1;
	private ArrayList<String> seenImages;
			
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		db = new DBConnect();
	}
	
	/**
	 * Delete a test user registration and login attempt
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		deleteTestUserRegistration();
		deleteTestUserLoginAttempt();
	}
	
	/**
	 * Helper method to create a new user
	 * @return a newly created user
	 */
	public User createUserDetails() {
		this.user = new User(userid, passwordOne, passwordTwo, passwordThree, pictureset, loginmethod, 0);
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

	/**
	 * Helper method to delete an user registrations from a database and avoid any primary key / foreign key exceptions
	 */
	public void deleteTestUserRegistration() {
		db.connectToDatabase();
		String command = "Delete From UserRegistrations Where UserID = ? AND PictureSet = ? AND LoginMethod = ?";
		try {
			PreparedStatement st = db.getConnection().prepareStatement(command);
			st.setString(1, userid);
			st.setInt(2, pictureset);
			st.setInt(3, loginmethod);
			st.executeUpdate();
			st.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			
		}
	}
	
	/**
	 * Helper method to delete an user login attempt from a database and avoid any primary key / foreign key exceptions
	 */
	public void deleteTestUserLoginAttempt() {
		db.connectToDatabase();
		String command = "Delete From UserLoginAttempts Where UserID = ? AND PictureSet = ? AND LoginMethod = ?";
		try {
			PreparedStatement st = db.getConnection().prepareStatement(command);
			st.setString(1, userid);
			st.setInt(2, pictureset);
			st.setInt(3, loginmethod);
			st.executeUpdate();
			st.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			db.closeConnection();
		}
	}

	/**
	 * Test the connection is valid
	 * It tests the connect to database method in the dbconnect class
	 * It passes if asserts false that the db connection is closed.
	 */
	@Test
	public void testConnectionToDatabase() {
		db.connectToDatabase();
		try {
			assertFalse(db.getConnection().isClosed());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test the connection is closed
	 * It tests the connect closeConnection method in the dbconnect class
	 * It passes if asserts true that the db connection is false.
	 */
	@Test
	public void testCloseConnection() {
		db.connectToDatabase();
		db.closeConnection();
		try {
			assertTrue(db.getConnection().isClosed());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test that the method to add a user account to the database is valid.
	 * Identify the correct user is returned by the helper method by comparing the userid, picture set choice and login method choice
	 */
	@Test
	public void testAddUserToDatabase() {
		User user = createUserDetails();
		db.addUserToDatabase(user);
		user = returnRegisteredUser(userid, pictureset, loginmethod);
		assertEquals(userid, user.getUserid());
		assertEquals(1, user.getPictureSet());
		assertEquals(1, user.getLoginMethod());
	}
	
	/**
	 * Test that the correct password files are returned from the database
	 * An indexed list containing the password strings returned from the database should match a local copy of the list containing password strings
	 * This test passes if it asserts true that the returned password files is equal to the local one that was sent to it in the first place.
	 */
	@Test
	public void testPasswordFilesReturned() {
		User user = createUserDetails();
		db.addUserToDatabase(user);
		List<String> passwordsFromDB = db.getUserPasswordFilePathsFromDatabase(user.getUserid(), user.getPictureSet(), user.getLoginMethod());
		List<String> passwordsLocal = new ArrayList<String>();
		passwordsLocal.add(user.getPasswordOne());
		passwordsLocal.add(user.getPasswordTwo());
		passwordsLocal.add(user.getPasswordThree());
		assertEquals(passwordsLocal, passwordsFromDB);
	}
	
	/**
	 * A test to check that the correct challenge set is returned from the database
	 * Checks for equality between a set containing the password string from the db matches the a local set containing a users images from their challenge set
	 * This test passes if it asserts true that the returned challenge set from the database is equal to the local one that was sent to it.
	 */
	@Test
	public void testGetUserChallengeSetFromDB() {
		User user = createUserDetails();
		db.addUserToDatabase(user);
		Set<String> set = new HashSet<String>();
		List<String> list = db.getUserChallengeSetFromDB(user.getUserid(), user.getPictureSet(), user.getLoginMethod());
		set.addAll(list);
		assertEquals(user.getImages(), set);
	}
	
	
	/**
	 * Test the update file paths method in the db connect class
	 * If the update files method is working, the retrieved list of images from the challenge set from the database after the update method is called,
	 * shouldn't match the list before the update method was called.
	 * this test password if it asserts that the local and database versions of the file paths are not equal
	 */
	@Test
	public void testUpdateFilePaths() {
		User user = createUserDetails();
		db.addUserToDatabase(user);
		List<String> decoyImagesBeforeUpdate = db.getUserChallengeSetFromDB(user.getUserid(), user.getPictureSet(), user.getLoginMethod());
		decoyImagesBeforeUpdate.add(user.getPasswordOne());
		decoyImagesBeforeUpdate.add(user.getPasswordTwo());
		decoyImagesBeforeUpdate.add(user.getPasswordThree());
		List<String> updatedImages = new ArrayList<String>();
		int maxImages = 17;
		int counter = 0;
		while (updatedImages.size() < maxImages) {
			updatedImages.add("image " + counter);
			counter++;
		}
		updatedImages.add(user.getPasswordOne());
		updatedImages.add(user.getPasswordTwo());
		updatedImages.add(user.getPasswordThree());
		db.updateUserFilePaths(user.getUserid(), user.getLoginMethod(), user.getPictureSet(), updatedImages);
		List<String> decoyImagesImagesAfterUpdate = db.getUserChallengeSetFromDB(user.getUserid(), user.getPictureSet(), user.getLoginMethod());
		assertNotEquals("Challenge Set before and after update should not match", decoyImagesBeforeUpdate, decoyImagesImagesAfterUpdate);
	}
	
	/**
	 * Helper method to return a user from teh database
	 * @param userID - userid for a user registration
	 * @param pictureSet - picture set chosen for a registration
	 * @param loginMethod - login method choice for a registration
	 * @return a user
	 */
	public User returnRegisteredUser(String userID, int pictureSet, int loginMethod) {
		User user = null;
		db.connectToDatabase();
		String command = "Select * From UserRegistrations Where UserID = ? AND PictureSet = ? AND LoginMethod = ?";
		try {
			PreparedStatement st = db.getConnection().prepareStatement(command);
			st.setString(1, userid);
			st.setInt(2, pictureSet);
			st.setInt(3, loginMethod);
			ResultSet result = st.executeQuery();
			while (result.next()) {
				String UserID = result.getString("UserID");
				int pictureSet1 = result.getInt("PictureSet");
				String passwordOne = result.getString("PasswordOne");
				String passwordTwo = result.getString("PasswordTwo");
				String passwordThree = result.getString("PasswordThree");
				int loginMethod1 = result.getInt("LoginMethod");
				double overallTimeTaken = result.getDouble("TotalTimeTaken");
				user = new User(UserID, passwordOne, passwordTwo, passwordThree, pictureSet1, loginMethod1, overallTimeTaken);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
		}
		return user;
	}
	
	/**
	 * Helper method to add a fake login attempt to the database
	 */
	public void addLoginAttemptToDatabase() {
		this.enteredPassword = new ArrayList<String>();
		this.enteredPassword = createEnteredPasswords();
		this.timeTaken = new ArrayList<Double>();
		this.timeTaken = createTimeTaken();
		this.successOfPasswords = new ArrayList<Integer>();
		this.successOfPasswords = createSuccess();
		this.loginAttemptNo = 1;
		this.overallTimeTaken = 1.0;
		db.addLoginAttemptToDatabase(user.getUserid(), user.getLoginMethod(), user.getPictureSet(), enteredPassword, timeTaken, overallTimeTaken, successOfPasswords, correctPassword, loginAttemptNo);
	}
	
	/**
	 * A method to test that a login attempt was successfully added to the database
	 * It tests for equality between the variables that are added to the database with the locally held ones of the same name
	 * This test passes if it asserts that the local variables is equal to those that are sent up to the database
	 */
	@Test
	public void testAddLoginAttemptToDatabase() {
		String selectedImageOne = null, selectedImageTwo = null, selectedImageThree = null, userid = null;
		double timeTakenToChooseImageOne = 0.0, timeTakenToChooseImageTwo = 0.0, timeTakenToChooseImageThree = 0.0, overallTimeTaken = 0.0;
		int pictureset = 0, loginmethod = 0, successfulImageOne = 0, successfulImageTwo = 0, successfulImageThree = 0, attemptNumber = 0;
		boolean overallSuccessful = false;
		
		User user = createUserDetails();
		db.addUserToDatabase(user);
		setCorrectPassword(true);
		addLoginAttemptToDatabase();
		db.connectToDatabase();
		
		String command = "Select * From Userloginattempts where UserID = ? and PictureSet = ? and LoginMethod = ?";
		try {
			PreparedStatement st = db.getConnection().prepareStatement(command);
			st.setString(1, user.getUserid());
			st.setInt(2, user.getPictureSet());
			st.setInt(3, user.getLoginMethod());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				userid = rs.getString("UserID");
				loginmethod = rs.getInt("Loginmethod");
				pictureset = rs.getInt("PictureSet");
				selectedImageOne = rs.getString("SelectedImageOne");
				selectedImageTwo = rs.getString("SelectedImageTwo");
				selectedImageThree = rs.getString("SelectedImageThree");
				timeTakenToChooseImageOne = rs.getDouble("timeTakenToChooseImageOne");
				timeTakenToChooseImageTwo = rs.getDouble("timeTakenToChooseImageTwo");
				timeTakenToChooseImageThree = rs.getDouble("timeTakenToChooseImageThree");
				overallTimeTaken = rs.getDouble("OverallTime");
				successfulImageOne = rs.getInt("successfulImageOne");
				successfulImageTwo = rs.getInt("successfulImageTwo");
				successfulImageThree = rs.getInt("successfulImageThree");
				overallSuccessful = rs.getInt("OverallSuccessful") == 1 ? true : false;
				attemptNumber = rs.getInt("attemptNumber");
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.closeConnection();
		}
		
		List<String> selectedImages = createSelectedImages(selectedImageOne, selectedImageTwo, selectedImageThree);
		List<Double> timeTakenList = createTimeTaken(timeTakenToChooseImageOne, timeTakenToChooseImageTwo, timeTakenToChooseImageThree);
		List<Integer> successfulImagesList = createSuccessfulImages(successfulImageOne, successfulImageTwo, successfulImageThree);
		assertEquals(user.getUserid(), userid);
		assertEquals(user.getLoginMethod(), loginmethod);
		assertEquals(user.getPictureSet(), pictureset);
		assertEquals(this.enteredPassword, selectedImages);
		assertEquals(this.timeTaken, timeTakenList);
		assertEquals(this.successOfPasswords, successfulImagesList);
		assertEquals(this.loginAttemptNo, attemptNumber);
		assertEquals(this.correctPassword, overallSuccessful);
	}
	
	/**
	 * Test that a user has been registered on the database
	 * If a user is registered, the boolean isRegistered will be set as true
	 * The test is valid if the test asserts True, that the user has been registered
	 */
	@Test
	public void testReturnIsRegistered() {
		User user = createUserDetails();
		db.addUserToDatabase(user);
		boolean isRegistered = db.returnIsRegistered(user.getUserid(), user.getPictureSet(), user.getLoginMethod());
		assertTrue(isRegistered);
	}
	
	/**
	 * Test that the method to get a recent login attempt number from the database is valid
	 * If a update query has executed correctly, the retrieved login attempt no should equal the local variable, loginAttemptNo.
	 * The test passes if the assertEquals passes showing that the login attempt number matches the local one.
	 */
	@Test
	public void testGetRecentLoginAttemptNumber() {
		User user = createUserDetails();
		db.addUserToDatabase(user);
		setCorrectPassword(true);
		addLoginAttemptToDatabase();
		int loginAttempt = db.getRecentLoginAttemptNo(user.getUserid(), user.getLoginMethod(), user.getPictureSet());
		assertEquals(this.loginAttemptNo, loginAttempt);
	}
	
	/**
	 * Helper method to set the correct password boolean field
	 * @param b = boolean
	 */
	private void setCorrectPassword(boolean b) {
		this.correctPassword = b;
		
	}

	/**
	 * Test that the method to retrieve whether the most recent login attempt for a user was successful
	 * a login attempt that was recently successful will be sent to the database. The database stores successful login as 1 and unsuccessful as 0
	 * This knowledge will be used to set a boolean variable which should be true.
	 * This test should assert True to show that the method retrieves the correct value from the database
	 */
	@Test
	public void testGetRecentLoginSuccessThatWasSuccessful() {
		User user = createUserDetails();
		db.addUserToDatabase(user);
		setCorrectPassword(true);
		addLoginAttemptToDatabase();
		int recentlySuccessful = db.getRecentLoginSuccess(user.getUserid(), user.getLoginMethod(), user.getPictureSet());
		boolean rsuccessful = (recentlySuccessful == 1) ? true : false;
		assertTrue(rsuccessful);
	}
	
	/**
	 * Test that the method to retrieve whether the most recent login attempt for a user was unsuccessful
	 * a login attempt that was recently unsuccessful will be sent to the database. The database stores successful login as 1 and unsuccessful as 0
	 * This knowledge will be used to set a boolean variable which should be set to false.
	 * This test passes if it asserts False to show that the method retrieves the correct value from the database
	 */
	@Test
	public void testGetRecentLoginSuccessThatWasUnsuccessful() {
		User user = createUserDetails();
		db.addUserToDatabase(user);
		setCorrectPassword(false);
		addLoginAttemptToDatabase();
		int recentlySuccessful = db.getRecentLoginSuccess(user.getUserid(), user.getLoginMethod(), user.getPictureSet());
		boolean rsuccessful = (recentlySuccessful == 1) ? true : false;
		assertFalse(rsuccessful);
	}
	
	/**
	 * A helper method to create a list variable and add a list of integer to represent successful image selection
	 * @param successfulImageOne
	 * @param successfulImageTwo
	 * @param successfulImageThree
	 * @return
	 */
	private List<Integer> createSuccessfulImages(int successfulImageOne, int successfulImageTwo,
			int successfulImageThree) {
		List<Integer> alist = new ArrayList<Integer>();
		alist.add(successfulImageOne);
		alist.add(successfulImageTwo);
		alist.add(successfulImageThree);
		return alist;
	}

	/**
	 * A helper method to create a list variable to mimic time taken to select images.
	 * @param timeTakenToChooseImageOne
	 * @param timeTakenToChooseImageTwo
	 * @param timeTakenToChooseImageThree
	 * @return
	 */
	private List<Double> createTimeTaken(double timeTakenToChooseImageOne, double timeTakenToChooseImageTwo,
			double timeTakenToChooseImageThree) {
		List<Double> alist = new ArrayList<Double>();
		alist.add(timeTakenToChooseImageOne);
		alist.add(timeTakenToChooseImageTwo);
		alist.add(timeTakenToChooseImageThree);
		return alist;
	}

	/**
	 * A helper method to create a list variable to mimic selected images.
	 * @param selectedImageOne
	 * @param selectedImageTwo
	 * @param selectedImageThree
	 * @return
	 */
	private List<String> createSelectedImages(String selectedImageOne, String selectedImageTwo,
			String selectedImageThree) {
		List<String> alist = new ArrayList<String>();
		alist.add(selectedImageOne);
		alist.add(selectedImageTwo);
		alist.add(selectedImageThree);
		return alist;
	}

	/**
	 * A helper method to create a list of integers to mimic that images were selected correctly.
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
	 * A helper method to create a list of double to mimic the time to select each of the 3 password images
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
	 * A helper method to create a list of string to mimic each password
	 * @return
	 */
	private List<String> createEnteredPasswords() {
		List<String> enteredPasswords = new ArrayList<String>();
		enteredPasswords.add(passwordOne);
		enteredPasswords.add(passwordTwo);
		enteredPasswords.add(passwordThree);
		return enteredPasswords;
	}
	
	/**
	 * A helper method to create a list containing 60 strings representing each image that is seen during registration
	 */
	private void createSeenImages() {
		this.seenImages = new ArrayList<String>();
		int n = 1;
		while (n<=60) {
			this.seenImages.add("image" + n);
			n++;
		}
	}
	
	/**
	 * This test checks both the addUserSeenImagesToDB and getUserSeenImages in the dbconnect class.
	 * First we add a user to a database, create an fiction list holding 60 fiction file paths and send it up to the db. we then return a list from the db.
	 * This test passes if it asserts that the lists sent to and from the db are equal.
	 */
	@Test
	public void testAddUserSeenImagesToDatabase() {
		createSeenImages();
		User user = createUserDetails();
		db.addUserToDatabase(user);
		db.addUsersSeenImagestoDatabase(userid, pictureset, loginmethod, seenImages);
		List<String> dbSeenImages = new ArrayList<String>();
		dbSeenImages.addAll(db.getUserSeenImages(userid, pictureset, loginmethod));
		assertEquals("If this passes, it asserts that the list sent to the db is the same one sent back from db", seenImages, dbSeenImages);
	}
}

