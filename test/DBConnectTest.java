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

public class DBConnectTest {
	private DBConnect db;
	private User user;
	private List<String> enteredPassword;
	private List<Double> timeTaken;
	private List<Integer> successOfPasswords;
	private boolean correctPassword;
	private int loginAttemptNo;
	private boolean recentlySuccessful = true;
	private double overallTimeTaken;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		db = new DBConnect();
		deleteTestUserRegistration();
		deleteTestUserLoginAttempt();
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * Helper method to creating a new user
	 * @return
	 */
	public User createUserDetails() {
		this.user = new User("999999", "a", "b", "c", 1, 1, 0);
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
		db.connectToDatabase();
		String command = "Delete From UserRegistrations Where UserID = ? AND PictureSet = ? AND LoginMethod = ?";
		try {
			PreparedStatement st = db.getConnection().prepareStatement(command);
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
	
	public void deleteTestUserLoginAttempt() {
		db.connectToDatabase();
		String command = "Delete From UserLoginAttempts Where UserID = ? AND PictureSet = ? AND LoginMethod = ?";
		try {
			PreparedStatement st = db.getConnection().prepareStatement(command);
			st.setString(1, "999999");
			st.setInt(2, 1);
			st.setInt(3, 1);
			st.executeUpdate();
			st.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			db.closeConnection();
		}
	}

	@Test
	public void testConnection() {
		db.connectToDatabase();
		try {
			assertFalse(db.getConnection().isClosed());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testClosed() {
		db.connectToDatabase();
		db.closeConnection();
		try {
			assertTrue(db.getConnection().isClosed());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAddUserToDatabase() {
		User user = createUserDetails();
		db.addUserToDatabase(user);
		user = returnRegisteredUser("999999", 1, 1);
		assertEquals("999999", user.getUserid());
		assertEquals(1, user.getPictureSet());
		assertEquals(1, user.getLoginMethod());
	}
	
	@Test
	public void testPasswordFilesReturned() {
		User user = createUserDetails();
		db.addUserToDatabase(user);
		List<String> passwordsFromDB = db.getUserPasswordFilePathsFromDatabase(user.getUserid(), user.getPictureSet(), user.getLoginMethod());
		List<String> passwordsLocal = new ArrayList<String>();
		passwordsLocal.add("a");
		passwordsLocal.add("b");
		passwordsLocal.add("c");
		assertEquals(passwordsLocal, passwordsFromDB);
	}
	
	@Test
	public void returnUserDecoyImageSetFromDatabase() {
		User user = createUserDetails();
		db.addUserToDatabase(user);
		Set<String> set = new HashSet<String>();
		List<String> list = db.getDecoyImageSetFromDB(user.getUserid(), user.getPictureSet(), user.getLoginMethod());
		set.addAll(list);
		assertEquals(user.getImages(), set);
	}
	
	
	@Test
	public void testUpdateFilePaths() {
		User user = createUserDetails();
		db.addUserToDatabase(user);
		List<String> decoyImagesBeforeUpdate = db.getDecoyImageSetFromDB(user.getUserid(), user.getPictureSet(), user.getLoginMethod());
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
		List<String> decoyImagesImagesAfterUpdate = db.getDecoyImageSetFromDB(user.getUserid(), user.getPictureSet(), user.getLoginMethod());
		assertNotEquals(decoyImagesBeforeUpdate, decoyImagesImagesAfterUpdate);
	}
	
	public User returnRegisteredUser(String userID, int pictureSet, int loginMethod) {
		User user = null;
		db.connectToDatabase();
		String command = "Select * From UserRegistrations Where UserID = ? AND PictureSet = ? AND LoginMethod = ?";
		try {
			PreparedStatement st = db.getConnection().prepareStatement(command);
			st.setString(1, "999999");
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
	
	public void addLoginAttemptToDatabase() {
		this.enteredPassword = new ArrayList<String>();
		this.enteredPassword = createEnteredPasswords();
		this.timeTaken = new ArrayList<Double>();
		this.timeTaken = createTimeTaken();
		this.successOfPasswords = new ArrayList<Integer>();
		this.successOfPasswords = createSuccess();
		this.correctPassword = true;
		this.loginAttemptNo = 1;
		this.overallTimeTaken = 1.0;
		db.addLoginAttemptToDatabase(user.getUserid(), user.getLoginMethod(), user.getPictureSet(), enteredPassword, timeTaken, overallTimeTaken, successOfPasswords, correctPassword, loginAttemptNo);
	}
	
	@Test
	public void testAddLoginAttemptToDatabase() {
		String selectedImageOne = null, selectedImageTwo = null, selectedImageThree = null, userid = null;
		double timeTakenToChooseImageOne = 0.0, timeTakenToChooseImageTwo = 0.0, timeTakenToChooseImageThree = 0.0, overallTimeTaken = 0.0;
		int pictureset = 0, loginmethod = 0, successfulImageOne = 0, successfulImageTwo = 0, successfulImageThree = 0, attemptNumber = 0;
		boolean overallSuccessful = false;
		
		User user = createUserDetails();
		db.addUserToDatabase(user);
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
	
	@Test
	public void testReturnIsRegistered() {
		User user = createUserDetails();
		db.addUserToDatabase(user);
		boolean isRegistered = db.returnIsRegistered(user.getUserid(), user.getPictureSet(), user.getLoginMethod());
		assertTrue(isRegistered);
	}
	
	@Test
	public void testGetRecentLoginAttemptNumber() {
		User user = createUserDetails();
		db.addUserToDatabase(user);
		addLoginAttemptToDatabase();
		int loginAttempt = db.getRecentLoginAttemptNo(user.getUserid(), user.getLoginMethod(), user.getPictureSet());
		assertEquals(this.loginAttemptNo, loginAttempt);
	}
	
	@Test
	public void testGetRecentLoginSuccess() {
		User user = createUserDetails();
		db.addUserToDatabase(user);
		addLoginAttemptToDatabase();
		int recentlySuccessful = db.getRecentLoginSuccess(user.getUserid(), user.getLoginMethod(), user.getPictureSet());
		boolean rsuccessful = (recentlySuccessful == 1) ? true : false;
		assertEquals(this.recentlySuccessful, rsuccessful);
	}

	private List<Integer> createSuccessfulImages(int successfulImageOne, int successfulImageTwo,
			int successfulImageThree) {
		List<Integer> alist = new ArrayList<Integer>();
		alist.add(successfulImageOne);
		alist.add(successfulImageTwo);
		alist.add(successfulImageThree);
		return alist;
	}

	private List<Double> createTimeTaken(double timeTakenToChooseImageOne, double timeTakenToChooseImageTwo,
			double timeTakenToChooseImageThree) {
		List<Double> alist = new ArrayList<Double>();
		alist.add(timeTakenToChooseImageOne);
		alist.add(timeTakenToChooseImageTwo);
		alist.add(timeTakenToChooseImageThree);
		return alist;
	}

	private List<String> createSelectedImages(String selectedImageOne, String selectedImageTwo,
			String selectedImageThree) {
		List<String> alist = new ArrayList<String>();
		alist.add(selectedImageOne);
		alist.add(selectedImageTwo);
		alist.add(selectedImageThree);
		return alist;
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

