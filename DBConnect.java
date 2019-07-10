import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class DBConnect {
	private Connection connection;
	private Statement statement;
	private final static String IP = "jdbc:postgresql://localhost:5432/";
	private final static String USERNAME = "postgres";
	private final static String PASSWORD = "freckles";
	

	public DBConnect() {
		connection = null;
		statement = null;
		connectToDatabase();
	}

	public Connection connectToDatabase() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Could not find JDBC Driver");
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(IP, USERNAME, PASSWORD);
		} catch (SQLException e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}
		return connection;
	}
	

	public Connection getConnection() {
		return this.connection;
	}
	

	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Statement getStatement() {
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.statement;

	}

	public void addUserToDatabase(User user) {
//		Iterator<String> it = user.decoys.iterator();
		Iterator<Double> timeTakenIterator = user.getTimeTaken().iterator();
//		String command = "INSERT INTO Registrations(UserID, LoginMethod, PasswordOne, PasswordTwo, PasswordThree, DecoyImageOne, DecoyImageTwo, DecoyImageThree, DecoyImageFour, DecoyImageFive, DecoyImageSix, DecoyImageSeven, DecoyImageEight, DecoyImageNine, DecoyImageTen, DecoyImageEleven, DecoyImageTwelve, DecoyImageThirteen, DecoyImageFourteen, DecoyImageFifteen, DecoyImageSixteen, DecoyImageSeventeen, PictureSet) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String command = "INSERT INTO USERREGISTRATIONs(UserID, PictureSet, TimeTakenToChoosePasswordOne, TimeTakenToChoosePasswordTwo, TimeTakenToChoosePasswordThree, PasswordOne, PasswordTwo, PasswordThree) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";  
		try {
			PreparedStatement st = getConnection().prepareStatement(command);
			st.setInt(1, user.getUserid());
			st.setInt(2, user.getPictureSet());
			st.setDouble(3, timeTakenIterator.next());
			st.setDouble(4, timeTakenIterator.next());
			st.setDouble(5, timeTakenIterator.next());
			st.setString(6, user.getPasswordOne());
			st.setString(7, user.getPasswordTwo());
			st.setString(8, user.getPasswordThree());
			st.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void addUserSeenDecoyImagesToDatabase(User currentUser) {
		Iterator<String> iterator = currentUser.getSeenDecoys().iterator();
		int counter = 3;
		String command = "INSERT INTO SEENDECOYIMAGESET(UserID, PictureSet, DecoyImageOne, DecoyImageTwo, DecoyImageThree, DecoyImageFour, DecoyImageFive, DecoyImageSix, DecoyImageSeven, DecoyImageEight, DecoyImageNine, DecoyImageTen, DecoyImageEleven, DecoyImageTwelve, DecoyImageThirteen, DecoyImageFourteen, DecoyImageFifteen, DecoyImageSixteen, DecoyImageSeventeen, PasswordImageOne, PasswordImageTwo, PasswordImageThree) Values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement st = getConnection().prepareStatement(command);
			st.setInt(1, currentUser.getUserid());
			st.setInt(2, currentUser.getPictureSet());
			while (iterator.hasNext()) {
				st.setString(counter, iterator.next());
				counter++;
			}
			st.setString(counter, currentUser.getPasswordOne());
			st.setString(++counter, currentUser.getPasswordTwo());
			st.setString(++counter, currentUser.getPasswordThree());
			st.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void addUserUnseenDecoyImagesToDatabase(User currentUser) {
		Iterator<String> iterator = currentUser.getUnseenDecoys().iterator();
		int counter = 3;
		String command = "INSERT INTO UNSEENDECOYIMAGESET(UserID, PictureSet, DecoyImageOne, DecoyImageTwo, DecoyImageThree, DecoyImageFour, DecoyImageFive, DecoyImageSix, DecoyImageSeven, DecoyImageEight, DecoyImageNine, DecoyImageTen, DecoyImageEleven, DecoyImageTwelve, DecoyImageThirteen, DecoyImageFourteen, DecoyImageFifteen, DecoyImageSixteen, DecoyImageSeventeen, PasswordImageOne, PasswordImageTwo, PasswordImageThree) Values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement st = getConnection().prepareStatement(command);
			st.setInt(1, currentUser.getUserid());
			st.setInt(2, currentUser.getPictureSet());
			while (iterator.hasNext()) {
				st.setString(counter, iterator.next());
				counter++;
			}
			st.setString(counter, currentUser.getPasswordOne());
			st.setString(++counter, currentUser.getPasswordTwo());
			st.setString(++counter, currentUser.getPasswordThree());
			st.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public List<String> getRegistrationsPasswordsFromDatabase(int UserID, int loginAttempt, int pictureSet) {
		List<String> passwordPaths = new ArrayList<String>();
//		String commandTwo = "SELECT * FROM Registrations Where UserID = ? AND LoginMethod = ? AND PictureSet = ?";
		String commandTwo = "SELECT * FROM Registrations Where UserID = ? AND PictureSet = ?";
		String imagePath = "";
		try {
			PreparedStatement st = getConnection().prepareStatement(commandTwo);
			st.setInt(1, UserID);
//			st.setInt(2, loginAttempt);
			st.setInt(2, pictureSet);
			ResultSet result = st.executeQuery();
			int n = 3;
			while (result.next()) {
				while (n < 6) {
					imagePath = result.getString(n);
					passwordPaths.add(imagePath);
					n++;
				}
			}
		} catch (SQLException e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}
		return passwordPaths;
	}
	
	public List<String> getRegistrationsFilePathsFromDatabase(int UserID, int loginMethod, int pictureSet) {
		List<String> imagePaths = new ArrayList<String>();
		String commandTwo = "SELECT * FROM Registrations Where UserID = ? AND LoginMethod = ? AND PictureSet = ?";
		String imagePath = "";
		try {
			PreparedStatement st = getConnection().prepareStatement(commandTwo);
			st.setInt(1, UserID);
			st.setInt(2, loginMethod);
			st.setInt(3, pictureSet);
			ResultSet result = st.executeQuery();
			int n = 3;
			while (result.next()) {
			while (n <= result.getMetaData().getColumnCount()) {
				imagePath = result.getString(n);
				imagePaths.add(imagePath);
				n++;
			}
			}
		} catch (SQLException e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}
		return imagePaths;
	}
	
	/*
	 * Get user passwords from the UserRegistrations Table
	 */
	public List<String> getUserPasswordFilePathsFromDatabase(int UserID, int pictureSet) {
		List<String> imagePaths = new ArrayList<String>();
		String commandTwo = "SELECT * FROM UserRegistrations Where UserID = ? AND PictureSet = ?";
		try {
			PreparedStatement st = getConnection().prepareStatement(commandTwo);
			st.setInt(1, UserID);
			st.setInt(2, pictureSet);
			ResultSet result = st.executeQuery();
			while (result.next()) {
			String passwordOne = result.getString("PasswordOne");
			String passwordTwo = result.getString("PasswordTwo");
			String passwordThree = result.getString("PasswordThree");
			imagePaths.add(passwordOne);
			imagePaths.add(passwordTwo);
			imagePaths.add(passwordThree);
			}
		} catch (SQLException e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}
		return imagePaths;
	}
	
	public List<String> getSeenDecoyImageSetFilePathsFromDatabase(int UserID, int pictureSet) {
		List<String> imagePaths = new ArrayList<String>();
		String commandTwo = "SELECT * FROM SEENDECOYIMAGESET Where UserID = ? AND PictureSet = ?";
		String imagePath = "";
		try {
			PreparedStatement st = getConnection().prepareStatement(commandTwo);
			st.setInt(1, UserID);
			st.setInt(2, pictureSet);
			ResultSet result = st.executeQuery();
			int n = 3;
			while (result.next()) {
			while (n <= result.getMetaData().getColumnCount()) {
				imagePath = result.getString(n);
				imagePaths.add(imagePath);
				n++;
			}
			}
		} catch (SQLException e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}
		return imagePaths;
	}
	
	public List<String> getUnseenDecoyImageSetFilePathsFromDatabase(int UserID, int pictureSet) {
		List<String> imagePaths = new ArrayList<String>();
		String commandTwo = "SELECT * FROM UNSEENDECOYIMAGESET Where UserID = ? AND PictureSet = ?";
		String imagePath = "";
		try {
			PreparedStatement st = getConnection().prepareStatement(commandTwo);
			st.setInt(1, UserID);
			st.setInt(2, pictureSet);
			ResultSet result = st.executeQuery();
			int n = 3;
			while (result.next()) {
			while (n <= result.getMetaData().getColumnCount()) {
				imagePath = result.getString(n);
				imagePaths.add(imagePath);
				n++;
			}
			}
		} catch (SQLException e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}
		return imagePaths;
	}
	
//
//	public int returnLatestAddedUserID() {
//		String commandTwo = "SELECT * FROM Registrations Order By UserID DESC LIMIT 1 ";
//		int UserID = 0;
//		ResultSet result;	
//		try {
//			result = getStatement().executeQuery(commandTwo);
//			while (result.next()) {
//				UserID = result.getInt("UserID");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return UserID;
//	}

	public void addLoginAttemptToDB(int userID, int loginMethod, List<String> enteredPassword, List<Double> timeTaken,
			boolean correctPassword, int loginAttemptNo, int pictureSet) {
		Iterator<String> passwordIterator = enteredPassword.iterator();
		Iterator<Double> timeTakenIterator = timeTaken.iterator();
		int correctPasswordInt = (correctPassword) ? 1 : 0;
		String command = "INSERT INTO LOGINATTEMPTS(UserID, LoginMethod, SelectedImageOne, SelectedImageTwo, SelectedImageThree, TimeTakenToChooseOne, TimeTakenToChooseTwo, TimeTakenToChooseThree, Successful, AttemptNumber, PictureSet) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement st = getConnection().prepareStatement(command);
			st.setInt(1, userID);
			st.setInt(2, loginMethod);
			st.setString(3, passwordIterator.next());
			st.setString(4, passwordIterator.next());
			st.setString(5, passwordIterator.next());
			st.setDouble(6, timeTakenIterator.next());
			st.setDouble(7, timeTakenIterator.next());
			st.setDouble(8, timeTakenIterator.next());
			st.setInt(9, correctPasswordInt);
			st.setInt(10, loginAttemptNo);
			st.setInt(11, pictureSet);
			st.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public int getRecentLoginAttemptNo(int userID, int loginMethod, int pictureSet) {
//		String command = "SELECT ATTEMPTNUMBER FROM LOGINATTEMPTS Where UserID = ? AND LoginMethod = ? AND PictureSet = ?";
		String command = "SELECT ATTEMPTNUMBER FROM ALLLOGINATTEMPTS Where UserID = ? AND LoginMethod = ? AND PictureSet = ?";
		int loginAttemptNo = 0;
		try {
			PreparedStatement st = getConnection().prepareStatement(command);
			st.setInt(1, userID);
			st.setInt(2, loginMethod);
			st.setInt(3, pictureSet);
			ResultSet result = st.executeQuery();
			while (result.next()) {
				loginAttemptNo = result.getInt("AttemptNumber");
				
			}
		} catch (SQLException e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}
		System.out.println(loginAttemptNo + " from database");
		return loginAttemptNo;
	}
	
//	public void addRegistrationTime(User user, List<Double> timeTaken) {
//		Iterator<Double> timeTakenIterator = timeTaken.iterator();
//		String command = "Insert into RegistrationTimes(UserID, LoginMethod, TimeTakenToChoosePasswordOne, TimeTakenToChoosePasswordTwo, TimeTakenToChoosePasswordThree, PictureSet) Values (?, ?, ?, ?, ?, ?)";
//		try {
//			PreparedStatement st = getConnection().prepareStatement(command);
//			st.setInt(1, user.getUserid());
//			st.setInt(2, user.getLoginattempt());
//			st.setDouble(3, timeTakenIterator.next());
//			st.setDouble(4, timeTakenIterator.next());
//			st.setDouble(5, timeTakenIterator.next());
//			st.setInt(6, user.getPictureSet());
//			st.executeUpdate();
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
//	}
	
	public int getRecentLoginSuccess(int userID, int loginMethod, int pictureSet) {
//		String command = "Select Successful From  Where UserID = ? AND LoginMethod = ? And PictureSet = ? ORDER BY ATTEMPTNUMBER DESC LIMIT 1";
		String command = "Select OverallSuccessful From AllLoginAttempts Where UserID = ? AND LoginMethod = ? And PictureSet = ? ORDER BY ATTEMPTNUMBER DESC LIMIT 1";
		int successful = 0;
		try {
			PreparedStatement st = getConnection().prepareStatement(command);
			st.setInt(1, userID);
			st.setInt(2, loginMethod);
			st.setInt(3, pictureSet);
			ResultSet result = st.executeQuery();
			while (result.next()) {
				successful = result.getInt("OverallSuccessful");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return successful;
	}


	public void addLoginAttemptToDatabase(int userID, int loginMethod, int pictureSetSelection, List<String> enteredPassword, List<Double> timeTaken,
		List<Integer> successOfPasswords, boolean correctPassword, int loginAttemptNo) {
		Iterator<String> passwordIterator = enteredPassword.iterator();
		Iterator<Double> timeTakenIterator = timeTaken.iterator();
		Iterator<Integer> successIterator = successOfPasswords.iterator();
		int correctPasswordInt = (correctPassword) ? 1 : 0;
		String command = "INSERT INTO ALLLOGINATTEMPTS(UserID, LoginMethod, PictureSet, SelectedImageOne, SelectedImageTwo, SelectedImageThree, TimeTakenToChooseImageOne, TimeTakenToChooseImageTwo, TimeTakenToChooseImageThree, successfulImageOne, successfulImageTwo, successfulImageThree, OverallSuccessful, AttemptNumber) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement st = getConnection().prepareStatement(command);
			st.setInt(1, userID);
			st.setInt(2, loginMethod);
			st.setInt(3, pictureSetSelection);
			st.setString(4, passwordIterator.next());
			st.setString(5, passwordIterator.next());
			st.setString(6, passwordIterator.next());
			st.setDouble(7, timeTakenIterator.next());
			st.setDouble(8, timeTakenIterator.next());
			st.setDouble(9, timeTakenIterator.next());
			st.setInt(10, successIterator.next());
			st.setInt(11, successIterator.next());
			st.setInt(12, successIterator.next());
			st.setInt(13, correctPasswordInt);
			st.setInt(14, loginAttemptNo);
			st.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void updateUserFilePaths(int userID, int loginMethod, int pictureSetSelection, List<String> decoyImages) {
		
		Iterator<String> imagePaths = decoyImages.iterator();
		String command = "Update SEENDECOYIMAGESET SET DECOYIMAGEONE = ?, DECOYIMAGETWO = ?, DECOYIMAGETHREE = ?, DECOYIMAGEFOUR = ?, DECOYIMAGEFIVE = ?, DECOYIMAGESIX = ?, DECOYIMAGESEVEN = ?, DECOYIMAGEEIGHT = ?, DECOYIMAGENINE = ?, DECOYIMAGETEN = ?, DECOYIMAGEELEVEN = ?, DECOYIMAGETWELVE = ?, DECOYIMAGETHIRTEEN = ?, DECOYIMAGEFOURTEEN = ?, DECOYIMAGEFIFTEEN = ?, DECOYIMAGESIXTEEN = ?, DECOYIMAGESEVENTEEN = ?, PasswordImageOne = ?, PasswordImageTwo = ?, PasswordImageThree = ? WHERE USERID = ? AND PICTURESET = ?";
		if (loginMethod == 2) {
		command = "Update UNSEENDECOYIMAGESET SET DECOYIMAGEONE = ?, DECOYIMAGETWO = ?, DECOYIMAGETHREE = ?, DECOYIMAGEFOUR = ?, DECOYIMAGEFIVE = ?, DECOYIMAGESIX = ?, DECOYIMAGESEVEN = ?, DECOYIMAGEEIGHT = ?, DECOYIMAGENINE = ?, DECOYIMAGETEN = ?, DECOYIMAGEELEVEN = ?, DECOYIMAGETWELVE = ?, DECOYIMAGETHIRTEEN = ?, DECOYIMAGEFOURTEEN = ?, DECOYIMAGEFIFTEEN = ?, DECOYIMAGESIXTEEN = ?, DECOYIMAGESEVENTEEN = ?, PasswordImageOne = ?, PasswordImageTwo = ?, PasswordImageThree = ? WHERE USERID = ? AND PICTURESET = ?";
		}
		try {
			PreparedStatement st = getConnection().prepareStatement(command);
			st.setString(1, imagePaths.next());
			st.setString(2, imagePaths.next());
			st.setString(3, imagePaths.next());
			st.setString(4, imagePaths.next());
			st.setString(5, imagePaths.next());
			st.setString(6, imagePaths.next());
			st.setString(7, imagePaths.next());
			st.setString(8, imagePaths.next());
			st.setString(9, imagePaths.next());
			st.setString(10, imagePaths.next());
			st.setString(11, imagePaths.next());
			st.setString(12, imagePaths.next());
			st.setString(13, imagePaths.next());
			st.setString(14, imagePaths.next());
			st.setString(15, imagePaths.next());
			st.setString(16, imagePaths.next());
			st.setString(17, imagePaths.next());
			st.setString(18, imagePaths.next());
			st.setString(19, imagePaths.next());
			st.setString(20, imagePaths.next());
			st.setInt(21, userID);
			st.setInt(22, pictureSetSelection);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

	public boolean returnIsRegistered(int userID, int pictureSetSelection) {
		int count = 0;
		String command = "Select Count(*) From UserRegistrations where UserID = ? AND PictureSet = ?";
		try {
			PreparedStatement st = getConnection().prepareStatement(command);
			st.setInt(1, userID);
			st.setInt(2, pictureSetSelection);
			ResultSet result = st.executeQuery();
			while (result.next()) {
				count = result.getInt(1);
			}			
		} catch (SQLException e) {
			
		}
		return (count > 0);
	}
	
	public static void main(String[] args) {
		DBConnect db = new DBConnect();
		boolean registered = db.returnIsRegistered(10, 3);
		System.out.println(registered);
	}
	
}