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

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testAddUserToDatabase() {
		try {
			setUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User user = createUserDetails();
		db.addUserToDatabase(user);
		user = returnLatestAddedUserToDB(1, 1, 1);
		assertEquals(1, user.getUserid());
	}
	
	public User createUserDetails() {
		User user = new User(1, "a", "b", "c", 1, 1);
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
			counter++;
		}
		user.setSeenDecoys(images);
		return user;
	}
	
	public User returnLatestAddedUserToDB(int userID, int pictureSet, int loginMethod) {
		User u = null;
		db.connectToDatabase();
		String command = "Select * From UserRegistrations Where UserID = ? AND PictureSet = ? AND LoginMethod = ?";
		try {
			PreparedStatement st = db.getConnection().prepareStatement(command);
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
				u = new User(UserID, passwordOne, passwordTwo, passwordThree, pictureSet1, loginMethod1);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
		}
		return u;
	}
}
