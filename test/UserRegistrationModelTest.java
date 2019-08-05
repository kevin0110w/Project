package test;

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

import main.User;
import main.UserRegistrationModel;

public class UserRegistrationModelTest {
	private User user;
	private UserRegistrationModel model;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.model = new UserRegistrationModel();
		this.user = createUserDetails();
		populateTheModel();
		deleteTestUserRegistration();
	}

	@After
	public void tearDown() throws Exception {
		deleteTestUserRegistration();
		model.clear();
	}
	
	@Test
	public void testTimeTakenInitiallyEmpty() {
		model.clear();
		assertTrue("List to record time taken initially set to zero", this.model.getTimeTaken().size() == 0); 
	}
	
	@Test
	public void testAddUser() {
		deleteTestUserRegistration();
		this.model.getImageFiles().createRegistrationSet(user.getLoginMethod());
		this.model.addUser();
		User newUser = returnLatestAddedUserToDB(user.getUserid(), user.getPictureSet(), user.getLoginMethod());
		assertEquals(this.user, newUser);
	}
	
	@Test
	public void testIsUserAlreadyRegistered() {
		boolean registered = model.isUserAlreadyRegistered();
		assertTrue("Should fail as user has not been registered to db yet", registered);
		this.model.createRegistrationSet();
		model.addUser();
		registered = model.isUserAlreadyRegistered();
		assertTrue("Should pass as user has now been registered to db", registered);
	}
	
	@Test
	public void testCreateDecoyImageSet() {
		User user = createUserDetails();
		model.setUser(user);
		assertEquals("User images set initially contains 3 password files", 3, model.getUser().getImageSetSize());
		this.model.createRegistrationSet();
		this.model.createDecoyImageSet();
		assertEquals("User image set now contains 20", 3, model.getUser().getImageSetSize());
	}
	
	@Test
	public void addTimeTaken() {
		model.getTimeTaken().clear();
		model.setInitialTime();
		assertEquals("List with time taken initially zero", 0, model.getTimeTaken().size());
		model.addTimeTaken();
		assertEquals("List should not have one element", 0, model.getTimeTaken().size());
	}
	
	@Test
	public void testCreateRegistrationSet() {
		assertEquals("List one should initially be 0", 0, model.getImageFiles().getListOne().size());
		assertEquals("List two should initially be 0", 0, model.getImageFiles().getListTwo().size());
		assertEquals("List three should initially be 0", 0, model.getImageFiles().getListThree().size());
		this.model.createRegistrationSet();
		assertEquals("List one should now be 20", 0, model.getImageFiles().getListOne().size());
		assertEquals("List two should now be 20", 0, model.getImageFiles().getListTwo().size());
		assertEquals("List three should now be 20", 0, model.getImageFiles().getListThree().size());
	}
	
	@Test
	public void testClear() {
		assertEquals("Password one set to 'a'", "a", model.getFirstSelectedImageFilePath());
		assertEquals("Password two set to 'b'", "b", model.getSecondSelectedImageFilePath());
		assertEquals("Password three set to 'c'", "c", model.getThirdSelectedImageFilePath());
		assertEquals("Time list contains three elements", 3, model.getTimeTaken().size());
		model.clear();
		assertEquals("Password one set to null", "a", model.getFirstSelectedImageFilePath());
		assertEquals("Password two set to null", "b", model.getSecondSelectedImageFilePath());
		assertEquals("Password three set to null", "c", model.getThirdSelectedImageFilePath());
		assertEquals("Time list contains 0 elements", 3, model.getTimeTaken().size());
	}
	
	public void populateTheModel() {
		model.setUserID(user.getUserid());
		model.setFirstSelectedImageFilePath(user.getPasswordOne());
		model.setSecondSelectedImageFilePath(user.getPasswordTwo());
		model.setThirdSelectedImageFilePath(user.getPasswordThree());
		model.setPictureSet(user.getPictureSet());
		model.setLoginMethod(user.getLoginMethod());
		model.setTimeTaken(user.getTimeTaken());
	}
	
	/**
	 * Helper method to creating a new user
	 * @return
	 */
	public User createUserDetails() {
		this.user = new User(999999, "a", "b", "c", 1, 1);
		List<Double> time = new ArrayList<Double>();
		time.add(0.0);
		time.add(0.0);
		time.add(0.0);
		user.setTimeTaken(time);
//		Set<String> images = new HashSet<String>();
//		int n = 17;
//		int counter = 1;
//		while (images.size() < n) {
//			images.add("password" + " " + counter);
//			user.addImageToImageSet("password" + " " + counter);
//			counter++;
//		}
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
			st.setInt(1, 999999);
			st.setInt(2, 1);
			st.setInt(3, 1);
			st.executeUpdate();
			st.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			
		}
	}
	
	public User returnLatestAddedUserToDB(int userID, int pictureSet, int loginMethod) {
		User user = null;
		model.getDb().connectToDatabase();
		String command = "Select * From UserRegistrations Where UserID = ? AND PictureSet = ? AND LoginMethod = ?";
		try {
			PreparedStatement st = model.getDb().getConnection().prepareStatement(command);
			st.setInt(1, userID);
			st.setInt(2, pictureSet);
			st.setInt(3, loginMethod);
			ResultSet result = st.executeQuery();
			while (result.next()) {
				int UserID = result.getInt("UserID");
				int pictureSet1 = result.getInt("PictureSet");
				String passwordOne = result.getString("PasswordOne");
				String passwordTwo = result.getString("PasswordTwo");
				String passwordThree = result.getString("PasswordThree");
				int loginMethod1 = result.getInt("LoginMethod");
				user = new User(UserID, passwordOne, passwordTwo, passwordThree, pictureSet1, loginMethod1);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
		}
		return user;
	}
}
