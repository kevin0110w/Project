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
/**
 * This test class will run junit tests on the public methods within the user registration model class
 * @author woohoo
 *
 */
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
	
	/**
	 * Test that the list containing all the times taken so far is initially empty.
	 * This unit test passes if it asserts True
	 */
	@Test
	public void testTimeTakenInitiallyEmpty() {
		model.clear();
		assertTrue("List to record time taken initially set to zero", this.model.getTimeTaken().size() == 0); 
	}
	
	/**
	 * A test to check that the add user within the model actually calls the add user method in the db connect class
	 * The test passes if it asserts equal that user that is created and sent to the database is equal to the user that is brought back from the user registration table
	 */
	@Test
	public void testAddUser() {
		deleteTestUserRegistration();
		this.model.createRegistrationSet();
		this.model.addUser();
		User newUser = returnLatestAddedUserToDB(user.getUserid(), user.getPictureSet(), user.getLoginMethod());
		assertEquals(this.user, newUser);
	}
	
	/**
	 * Test to check the isUserAlreadyRegistered Method in the model class.
	 * This method checks whether a user's account is found in the user registrations table.
	 * 
	 */
	@Test
	public void testIsUserAlreadyRegistered() {
		boolean registered = model.isUserAlreadyRegistered();
		assertFalse("Should be false as user has not been registered to db yet", registered);
		this.model.createRegistrationSet();
		model.addUser();
		registered = model.isUserAlreadyRegistered();
		assertTrue("Should be true as user has now been registered to db", registered);
	}
	
	/**
	 * This test checks createDecoyImageSet() method within the model class
	 * Before it is called, the images field in the user class should only be 3.
	 * This test passes if the assert equals shows that the images size is 20 after the createDecoyImageSet method is called.
	 */
	@Test
	public void testCreateDecoyImageSet() {
		User user = createUserDetails();
		model.setUser(user);
		assertEquals("User images set initially contains 3 password files", 3, model.getUser().getImages().size());
		this.model.createRegistrationSet();
		this.model.createDecoyImageSet();
		assertEquals("User image set now contains 20", 20, model.getUser().getImages().size());
	}
	
	/**
	 * This test will check that the addTimeTaken method in the model class adds a time to the list of times.
	 * Initially the list of times should have 0 elements.. When the method is called, the size will increment by one
	 * This test passes if it asserts that the size of the list of times is 1, i.e it has been incremented, after the add time taken method is called once.
	 */
	@Test
	public void addTimeTaken() {
		model.getTimeTaken().clear();
		model.setInitialTime();
		assertEquals("List with time taken initially zero", 0, model.getTimeTaken().size());
		model.addTimeTaken();
		assertEquals("List should not have one element", 1, model.getTimeTaken().size());
	}
	
	/**
	 * This test will check that the createRegistrationSet method within the model class populates each list with 20 images
	 * This test passes if it asserts that each list has 20 elements.
	 */
	@Test
	public void testCreateRegistrationSet() {
		assertEquals("List one should initially be 0", 0, model.getImageFiles().getListOne().size());
		assertEquals("List two should initially be 0", 0, model.getImageFiles().getListTwo().size());
		assertEquals("List three should initially be 0", 0, model.getImageFiles().getListThree().size());
		this.model.createRegistrationSet();
		assertEquals("List one should now be 20", 20, model.getImageFiles().getListOne().size());
		assertEquals("List two should now be 20", 20, model.getImageFiles().getListTwo().size());
		assertEquals("List three should now be 20", 20, model.getImageFiles().getListThree().size());
	}
	
	/**
	 * This test checks the clear method
	 * The test passes if it asserts that the passwords are null or time taken list is 0.
	 */
	@Test
	public void testClear() {
		assertEquals("Password one set to 'a'", "a", model.getFirstSelectedImageFilePath());
		assertEquals("Password two set to 'b'", "b", model.getSecondSelectedImageFilePath());
		assertEquals("Password three set to 'c'", "c", model.getThirdSelectedImageFilePath());
		assertEquals("Time list contains three elements", 3, model.getTimeTaken().size());
		model.clear();
		assertEquals("Password one set to null", null, model.getFirstSelectedImageFilePath());
		assertEquals("Password two set to null", null, model.getSecondSelectedImageFilePath());
		assertEquals("Password three set to null", null, model.getThirdSelectedImageFilePath());
		assertEquals("Time list contains 0 elements", 0, model.getTimeTaken().size());
	}
	
	/**
	 * A helper method to populate the model with fake data for the user id, picture set, login method, file paths of selected images
	 */
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
	 * @return a User
	 */
	public User createUserDetails() {
		this.user = new User("999999", "a", "b", "c", 1, 1, 0.0);
		List<Double> time = new ArrayList<Double>();
		time.add(0.0);
		time.add(0.0);
		time.add(0.0);
		user.setTimeTaken(time);
		return user;
	}
	
	/**
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
	
	/**
	 * A helper method to return the latest add user from the database
	 * @param userID - the id for an account
	 * @param pictureSet - the picture set of an account
	 * @param loginMethod - the login method of an account
	 * @return a user
	 */
	public User returnLatestAddedUserToDB(String userID, int pictureSet, int loginMethod) {
		User user = null;
		model.getDb().connectToDatabase();
		String command = "Select * From UserRegistrations Where UserID = ? AND PictureSet = ? AND LoginMethod = ?";
		try {
			PreparedStatement st = model.getDb().getConnection().prepareStatement(command);
			st.setString(1, userID);
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
				double timetaken = result.getDouble("totalTimeTaken");
				user = new User(UserID, passwordOne, passwordTwo, passwordThree, pictureSet1, loginMethod1, timetaken);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
		}
		return user;
	}
}
