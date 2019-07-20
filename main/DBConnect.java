package main;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
/**
 * This class is responsible for pushing and pulling data from the model and up to the database and vice versa.
 *
 */
public class DBConnect {
	private Connection connection;
	private Statement statement;
	private final static String IP = "jdbc:postgresql://localhost:5432/";
	private final static String USERNAME = "postgres";
	private final static String PASSWORD = "freckles";
	

	public DBConnect() {
		connection = null;
		statement = null;
	}

	/**
	 * Make a connection with the database
	 */
	public void connectToDatabase() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Could not find JDBC Driver");
			e.printStackTrace();
		}
		try {
			this.connection = DriverManager.getConnection(IP, USERNAME, PASSWORD);
		} catch (SQLException e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}
	}
	
	/**
	 * Close a connection with the database
	 */
	public void closeConnection() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Getter method to return the Connection
	 * @return
	 */
	public Connection getConnection() {
		return this.connection;
	}
	
	/**
	 * Getter method to return the Statement
	 * @return
	 */
	public Statement getStatement() {
		try {
			this.statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this.statement;

	}

	/*
	 * Add user registration details to the registration table
	 * Then add the images to the seenimageset and unseenimageset tables
	 */
	public void addUserToDatabase(User user) {
		this.connectToDatabase();
		Iterator<Double> timeTakenIterator = user.getTimeTaken().iterator();
//		String command = "INSERT INTO Registrations(UserID, LoginMethod, PasswordOne, PasswordTwo, PasswordThree, DecoyImageOne, DecoyImageTwo, DecoyImageThree, DecoyImageFour, DecoyImageFive, DecoyImageSix, DecoyImageSeven, DecoyImageEight, DecoyImageNine, DecoyImageTen, DecoyImageEleven, DecoyImageTwelve, DecoyImageThirteen, DecoyImageFourteen, DecoyImageFifteen, DecoyImageSixteen, DecoyImageSeventeen, PictureSet) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String command = "INSERT INTO USERREGISTRATIONs(UserID, PictureSet, TimeTakenToChoosePasswordOne, TimeTakenToChoosePasswordTwo, TimeTakenToChoosePasswordThree, PasswordOne, PasswordTwo, PasswordThree, LoginMethod) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
			st.setInt(9, user.getLoginMethod());
			st.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
//			if (user.getLoginMethod() == 1) {
//			this.addUserSeenDecoyImagesToDatabase(user); // call the method that'll add the user's decoy image set of seen images to the database
//			} else {
//			this.addUserUnseenDecoyImagesToDatabase(user); // call the method that'll add the user's decoy image set of unseen images to the database
//			}
			this.addUserImagesToDatabase(user);
				this.closeConnection();
		}
	}

	private void addUserImagesToDatabase(User user) {
		Iterator<String> iterator = user.getImages().iterator();
		int counter = 3;
		String command = "INSERT INTO SEENDECOYIMAGESET(UserID, PictureSet, DecoyImageOne, DecoyImageTwo, DecoyImageThree, DecoyImageFour, DecoyImageFive, DecoyImageSix, DecoyImageSeven, DecoyImageEight, DecoyImageNine, DecoyImageTen, DecoyImageEleven, DecoyImageTwelve, DecoyImageThirteen, DecoyImageFourteen, DecoyImageFifteen, DecoyImageSixteen, DecoyImageSeventeen, Image18, Image19, Image20, LoginMethod) Values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		if (user.getLoginMethod() == 2) {
			command = command = "INSERT INTO UNSEENDECOYIMAGESET(UserID, PictureSet, DecoyImageOne, DecoyImageTwo, DecoyImageThree, DecoyImageFour, DecoyImageFive, DecoyImageSix, DecoyImageSeven, DecoyImageEight, DecoyImageNine, DecoyImageTen, DecoyImageEleven, DecoyImageTwelve, DecoyImageThirteen, DecoyImageFourteen, DecoyImageFifteen, DecoyImageSixteen, DecoyImageSeventeen, PasswordImageOne, PasswordImageTwo, PasswordImageThree, loginmethod) Values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		}
		try {
			PreparedStatement st = getConnection().prepareStatement(command);
			st.setInt(1, user.getUserid());
			st.setInt(2, user.getPictureSet());
			while (iterator.hasNext()) {
				String file = iterator.next();
				st.setString(counter, file);
				counter++;
			}
//			st.setString(counter, user.getPasswordOne());
//			st.setString(++counter, user.getPasswordTwo());
//			st.setString(++counter, user.getPasswordThree());
			st.setInt(counter, user.getLoginMethod());
			st.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * This method will add a registered user's set of seen decoy images to the database
	 * @param currentUser - user going through registration
	 */
//	public void addUserSeenDecoyImagesToDatabase(User currentUser) {
//		Iterator<String> iterator = currentUser.getSeenDecoys().iterator();
//		int counter = 3;
//		String command = "INSERT INTO SEENDECOYIMAGESET(UserID, PictureSet, DecoyImageOne, DecoyImageTwo, DecoyImageThree, DecoyImageFour, DecoyImageFive, DecoyImageSix, DecoyImageSeven, DecoyImageEight, DecoyImageNine, DecoyImageTen, DecoyImageEleven, DecoyImageTwelve, DecoyImageThirteen, DecoyImageFourteen, DecoyImageFifteen, DecoyImageSixteen, DecoyImageSeventeen, Image18, Image19, Image20, LoginMethod) Values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//		try {
//			PreparedStatement st = getConnection().prepareStatement(command);
//			st.setInt(1, currentUser.getUserid());
//			st.setInt(2, currentUser.getPictureSet());
//			while (iterator.hasNext()) {
//				st.setString(counter, iterator.next());
//				counter++;
//			}
//			st.setString(counter, currentUser.getPasswordOne());
//			st.setString(++counter, currentUser.getPasswordTwo());
//			st.setString(++counter, currentUser.getPasswordThree());
//			st.setInt(++counter, currentUser.getLoginMethod());
//			st.executeUpdate();
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
////		finally {
////			try {
////				connection.close();
////			} catch (SQLException e) {
////				
////			}
////		}
//	}

	/**
	 * Method to add a user's decoy image set of unseen images to the database
	 * @param currentUser - user undergoing registration
	 */
//	public void addUserUnseenDecoyImagesToDatabase(User currentUser) {
//		Iterator<String> iterator = currentUser.getUnseenDecoys().iterator();
//		int counter = 3;
//		String command = "INSERT INTO UNSEENDECOYIMAGESET(UserID, PictureSet, DecoyImageOne, DecoyImageTwo, DecoyImageThree, DecoyImageFour, DecoyImageFive, DecoyImageSix, DecoyImageSeven, DecoyImageEight, DecoyImageNine, DecoyImageTen, DecoyImageEleven, DecoyImageTwelve, DecoyImageThirteen, DecoyImageFourteen, DecoyImageFifteen, DecoyImageSixteen, DecoyImageSeventeen, PasswordImageOne, PasswordImageTwo, PasswordImageThree, loginmethod) Values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//		try {
//			PreparedStatement st = getConnection().prepareStatement(command);
//			st.setInt(1, currentUser.getUserid());
//			st.setInt(2, currentUser.getPictureSet());
//			while (iterator.hasNext()) {
//				st.setString(counter, iterator.next());
//				counter++;
//			}
//			st.setString(counter, currentUser.getPasswordOne());
//			st.setString(++counter, currentUser.getPasswordTwo());
//			st.setString(++counter, currentUser.getPasswordThree());
//			st.setInt(++counter, currentUser.getLoginMethod());
//			st.executeUpdate();
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
////		finally {
////			try {
////				connection.close();
////			} catch (SQLException e) {
////				
////			}
////		}
//	}

//	public List<String> getRegistrationsPasswordsFromDatabase(int UserID, int loginAttempt, int pictureSet) {
//		connectToDatabase();
//		List<String> passwordPaths = new ArrayList<String>();
////		String commandTwo = "SELECT * FROM Registrations Where UserID = ? AND LoginMethod = ? AND PictureSet = ?";
//		String commandTwo = "SELECT * FROM Registrations Where UserID = ? AND PictureSet = ?";
//		String imagePath = "";
//		try {
//			PreparedStatement st = getConnection().prepareStatement(commandTwo);
//			st.setInt(1, UserID);
////			st.setInt(2, loginAttempt);
//			st.setInt(2, pictureSet);
//			ResultSet result = st.executeQuery();
//			int n = 3;
//			while (result.next()) {
//				while (n < 6) {
//					imagePath = result.getString(n);
//					passwordPaths.add(imagePath);
//					n++;
//				}
//			}
//		} catch (SQLException e) {
//			System.out.println("Connection failed");
//			e.printStackTrace();
//		}finally {
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				
//			}
//		}
//		return passwordPaths;
//	}
//	
//	public List<String> getRegistrationsFilePathsFromDatabase(int UserID, int loginMethod, int pictureSet) {
//		connectToDatabase();
//		List<String> imagePaths = new ArrayList<String>();
//		String commandTwo = "SELECT * FROM Registrations Where UserID = ? AND LoginMethod = ? AND PictureSet = ?";
//		String imagePath = "";
//		try {
//			PreparedStatement st = getConnection().prepareStatement(commandTwo);
//			st.setInt(1, UserID);
//			st.setInt(2, loginMethod);
//			st.setInt(3, pictureSet);
//			ResultSet result = st.executeQuery();
//			int n = 3;
//			while (result.next()) {
//			while (n <= result.getMetaData().getColumnCount()) {
//				imagePath = result.getString(n);
//				imagePaths.add(imagePath);
//				n++;
//			}
//			}
//		} catch (SQLException e) {
//			System.out.println("Connection failed");
//			e.printStackTrace();
//		}finally {
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				
//			}
//		}
//		return imagePaths;
//	}
	
	/**
	 * Get user passwords from the UserRegistrations Table
	 * @Param userid - the id of a user
	 * @Param pictureset - the choice of pictures
	 * @Return the three image password as a list of filepath strings 
	 */
	public List<String> getUserPasswordFilePathsFromDatabase(int UserID, int pictureSet, int loginMethod) {
		this.connectToDatabase();
		List<String> imagePaths = new ArrayList<String>(); // indexed list to stored the image passwords in order
		String commandTwo = "SELECT * FROM UserRegistrations Where UserID = ? AND PictureSet = ? AND LoginMethod = ?";
		try {
			PreparedStatement st = getConnection().prepareStatement(commandTwo);
			st.setInt(1, UserID);
			st.setInt(2, pictureSet);
			st.setInt(3, loginMethod);
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
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

			}
		}
		return imagePaths;
	}
	
	/**
	 * Get a list of the 20 images associated with a user 
	 * @param UserID - userid for the user
	 * @param pictureSet - picture set to be checked
	 * @return list of 20 image file paths
	 */
	public List<String> getSeenDecoyImageSetFilePathsFromDatabase(int UserID, int pictureSet) {
		this.connectToDatabase(); // connect to the database
		List<String> imagePaths = new ArrayList<String>();
		String commandTwo = "SELECT * FROM SEENDECOYIMAGESET Where UserID = ? AND PictureSet = ?";
		String imagePath = "";
		try {
			PreparedStatement st = getConnection().prepareStatement(commandTwo);
			st.setInt(1, UserID);
			st.setInt(2, pictureSet);
			ResultSet result = st.executeQuery();
			int n = 3; // counter to skip the first two columns return
			while (result.next()) {
				while (n <= result.getMetaData().getColumnCount()-1) { // get columns 3 to the end
					imagePath = result.getString(n); 
					imagePaths.add(imagePath); // add the filepath to the list
					n++; // increment the counter
				}
			}
		} catch (SQLException e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

			}
		}
		return imagePaths;
	}
	/**
	 * Get a list of the 20 images associated with a user (17 unseen decoy image and the 3 seen password images) 
	 * @param UserID - userid for the user
	 * @param pictureSet - picture set to be checked
	 * @return list of 20 image file paths
	 */
	public List<String> getUnseenDecoyImageSetFilePathsFromDatabase(int UserID, int pictureSet) {
		connectToDatabase();
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
				while (n <= result.getMetaData().getColumnCount()-1) {
					imagePath = result.getString(n);
					imagePaths.add(imagePath);
					n++;
				}
			}
		} catch (SQLException e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

			}
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
//		}finally {
//	try {
//		connection.close();
//	} catch (SQLException e) {
//		
//	}
//}
//		return UserID;
//	}
	
//	public void addLoginAttemptToDB(int userID, int loginMethod, List<String> enteredPassword, List<Double> timeTaken,
//			boolean correctPassword, int loginAttemptNo, int pictureSet) {
//		connectToDatabase();
//		Iterator<String> passwordIterator = enteredPassword.iterator();
//		Iterator<Double> timeTakenIterator = timeTaken.iterator();
//		int correctPasswordInt = (correctPassword) ? 1 : 0;
//		String command = "INSERT INTO LOGINATTEMPTS(UserID, LoginMethod, SelectedImageOne, SelectedImageTwo, SelectedImageThree, TimeTakenToChooseOne, TimeTakenToChooseTwo, TimeTakenToChooseThree, Successful, AttemptNumber, PictureSet) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//		try {
//			PreparedStatement st = getConnection().prepareStatement(command);
//			st.setInt(1, userID);
//			st.setInt(2, loginMethod);
//			st.setString(3, passwordIterator.next());
//			st.setString(4, passwordIterator.next());
//			st.setString(5, passwordIterator.next());
//			st.setDouble(6, timeTakenIterator.next());
//			st.setDouble(7, timeTakenIterator.next());
//			st.setDouble(8, timeTakenIterator.next());
//			st.setInt(9, correctPasswordInt);
//			st.setInt(10, loginAttemptNo);
//			st.setInt(11, pictureSet);
//			st.executeUpdate();
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}finally {
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				
//			}
//		}
//	}

	/**
	 * Get the latest login attempt number
	 * @param userID - id of a user
	 * @param loginMethod - login method, either 1 or 2 
	 * @param  pictureSet - a particular picture set
	 * @return the latest attempt number
	 */
	public int getRecentLoginAttemptNo(int userID, int loginMethod, int pictureSet) {
		this.connectToDatabase();
//		String command = "SELECT ATTEMPTNUMBER FROM LOGINATTEMPTS Where UserID = ? AND LoginMethod = ? AND PictureSet = ?";
		String command = "SELECT ATTEMPTNUMBER FROM ALLLOGINATTEMPTS Where UserID = ? AND LoginMethod = ? AND PictureSet = ? ORDER BY ATTEMPTNUMBER DESC LIMIT 1";
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
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				
			}
		}
		return loginAttemptNo;
	}
	
//	public void addRegistrationTime(User user, List<Double> timeTaken) {
//	connectToDatabase();
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
//		}finally {
//	try {
//		connection.close();
//	} catch (SQLException e) {
//		
//	}
//}
//	}
	
	/**
	 * Get whether a userid were recently successful in loggin in
	 * @param userID a usersid
	 * @param loginMethod - a particular log in method 
	 * @param pictureSet - a particular picture set
	 * @return a number which will be transformed to a boolean in the model
	 */
	public int getRecentLoginSuccess(int userID, int loginMethod, int pictureSet) {
		this.connectToDatabase();
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
		}finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				
			}
		}
		return successful;
	}

	/**
	 * Add a login attempt to the particular table in the database
	 * @param userID - the user id of the user logging in
	 * @param loginMethod - the login method 
	 * @param pictureSetSelection - the number of the picture set
	 * @param enteredPassword - the list of selected passwords
	 * @param timeTaken - the list of times taken to select each image
	 * @param successOfPasswords - a list of boolean to judge if each selection is correct
	 * @param correctPassword - a boolean whether overall selection is correct
	 * @param loginAttemptNo - login attempt number
	 */
	public void addLoginAttemptToDatabase(int userID, int loginMethod, int pictureSetSelection, List<String> enteredPassword, List<Double> timeTaken,
		List<Integer> successOfPasswords, boolean correctPassword, int loginAttemptNo) {
		connectToDatabase();
		Iterator<String> passwordIterator = enteredPassword.iterator();
		Iterator<Double> timeTakenIterator = timeTaken.iterator();
		Iterator<Integer> successIterator = successOfPasswords.iterator();
		int correctPasswordInt = (correctPassword) ? 1 : 0; // convert true to 1 and false to 0
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
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				
			}
		}
	}

	/*
	 * This method will update the order of images stored on a database for a user
	 * It will be called if a user unsuccessfully tries to log in, to ensure the shuffled order of images is retained for the next log in attempt
	 * @Param userID, loginMethod, PictureSetSelection and images associated with a particular user
	 */
	public void updateUserFilePaths(int userID, int loginMethod, int pictureSetSelection, List<String> decoyImages) {
		this.connectToDatabase();
		Iterator<String> imagePaths = decoyImages.iterator();
		String command = "Update SEENDECOYIMAGESET SET DECOYIMAGEONE = ?, DECOYIMAGETWO = ?, DECOYIMAGETHREE = ?, DECOYIMAGEFOUR = ?, DECOYIMAGEFIVE = ?, DECOYIMAGESIX = ?, DECOYIMAGESEVEN = ?, DECOYIMAGEEIGHT = ?, DECOYIMAGENINE = ?, DECOYIMAGETEN = ?, DECOYIMAGEELEVEN = ?, DECOYIMAGETWELVE = ?, DECOYIMAGETHIRTEEN = ?, DECOYIMAGEFOURTEEN = ?, DECOYIMAGEFIFTEEN = ?, DECOYIMAGESIXTEEN = ?, DECOYIMAGESEVENTEEN = ?, Image18 = ?, Image19 = ?, Image20 = ?, LoginMethod = ? WHERE USERID = ? AND PICTURESET = ? AND LoginMethod = ?";
		if (loginMethod == 2) {
		command = "Update UNSEENDECOYIMAGESET SET DECOYIMAGEONE = ?, DECOYIMAGETWO = ?, DECOYIMAGETHREE = ?, DECOYIMAGEFOUR = ?, DECOYIMAGEFIVE = ?, DECOYIMAGESIX = ?, DECOYIMAGESEVEN = ?, DECOYIMAGEEIGHT = ?, DECOYIMAGENINE = ?, DECOYIMAGETEN = ?, DECOYIMAGEELEVEN = ?, DECOYIMAGETWELVE = ?, DECOYIMAGETHIRTEEN = ?, DECOYIMAGEFOURTEEN = ?, DECOYIMAGEFIFTEEN = ?, DECOYIMAGESIXTEEN = ?, DECOYIMAGESEVENTEEN = ?, passwordImageone = ?, passwordImageTwo = ?, passwordImageThree = ?, LoginMethod = ? WHERE USERID = ? AND PICTURESET = ? AND LoginMethod = ?";
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
			st.setInt(21, loginMethod);
			st.setInt(22, userID);
			st.setInt(23, pictureSetSelection);
			st.setInt(24, loginMethod);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * This method will check whether there is a registered user in the user registrations table with particular userid and picture set selection
	 * @Param - UserID entered in the registration instruction panel text input box
	 * @Param - Picture set selection chosen in the registration instruction panel drop-down
	 * @Return - true is a user is already registered with the details provided.
	 */
//	public boolean returnIsRegistered(int userID, int pictureSetSelection) {
//		this.connectToDatabase();
//		int count = 0;
//		String command = "Select Count(UserID) From public.userregistrations where UserID = ? AND PictureSet = ? AND LoginMethod = ?";
//		try {
//			PreparedStatement st = getConnection().prepareStatement(command);
//			st.setInt(1, userID);
//			st.setInt(2, pictureSetSelection);
//			ResultSet result = st.executeQuery();
//			while (result.next()) {
//				count = result.getInt(1);
//			}			
//			result.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return (count > 0);
//	}

	public boolean returnIsRegistered(int userID, int pictureSetSelection, int loginMethod) {
		this.connectToDatabase();
		int count = 0;
		String command = "Select Count(*) From Userregistrations Where UserID = ? AND PictureSet = ? AND LoginMethod = ?";
		try {
			PreparedStatement st = getConnection().prepareStatement(command);
			st.setInt(1, userID);
			st.setInt(2, pictureSetSelection);
			st.setInt(3, loginMethod);
			ResultSet result = st.executeQuery();
			while (result.next()) {
				count = result.getInt(1);
			}			
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				
			}
		}
		return (count > 0);
	}
}