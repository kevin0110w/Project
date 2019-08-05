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
		String command = "INSERT INTO USERREGISTRATIONS(UserID, PictureSet, LoginMethod, PasswordOne, PasswordTwo, PasswordThree, TimeTakenToChoosePasswordOne, TimeTakenToChoosePasswordTwo, TimeTakenToChoosePasswordThree, totalTimeTaken) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement st = getConnection().prepareStatement(command);
			st.setString(1, user.getUserid());
			st.setInt(2, user.getPictureSet());
			st.setInt(3, user.getLoginMethod());
			st.setString(4, user.getPasswordOne());
			st.setString(5, user.getPasswordTwo());
			st.setString(6, user.getPasswordThree());
			st.setDouble(7, timeTakenIterator.next());
			st.setDouble(8, timeTakenIterator.next());
			st.setDouble(9, timeTakenIterator.next());
			st.setDouble(10, user.getOverallTimeTaken());
			st.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
//			if (user.getLoginMethod() == 1) {
//			this.addUserSeenDecoyImagesToDatabase(user); // call the method that'll add the user's decoy image set of seen images to the database
//			} else {
//			this.addUserUnseenDecoyImagesToDatabase(user); // call the method that'll add the user's decoy image set of unseen images to the database
//			}
			this.addUserDecoyImageSetToDatabase(user);
				this.closeConnection();
		}
	}
	
	/**
	 * Add the challenge set associated with a user (userid, picture set and login method)
	 * @param user
	 */
	private void addUserDecoyImageSetToDatabase(User user) {
		Iterator<String> iterator = user.getImages().iterator();
		int counter = 4;
		String command = "INSERT INTO DecoyImageSets(UserID, PictureSet, LoginMethod, ImageOne, ImageTwo, ImageThree, ImageFour, ImageFive, ImageSix, ImageSeven, ImageEight, ImageNine, ImageTen, ImageEleven, ImageTwelve, ImageThirteen, ImageFourteen, ImageFifteen, ImageSixteen, ImageSeventeen, ImageEighteen, ImageNineteen, ImageTwenty) Values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement st = getConnection().prepareStatement(command);
			st.setString(1, user.getUserid());
			st.setInt(2, user.getPictureSet());
			st.setInt(3, user.getLoginMethod());
			while (iterator.hasNext()) {
				String file = iterator.next();
				st.setString(counter, file);
				counter++;
			}
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
	public List<String> getUserPasswordFilePathsFromDatabase(String UserID, int pictureSet, int loginMethod) {
		this.connectToDatabase();
		List<String> imagePaths = new ArrayList<String>(); // indexed list to stored the image passwords in order
		String commandTwo = "SELECT * FROM UserRegistrations Where UserID = ? AND PictureSet = ? AND LoginMethod = ?";
		try {
			PreparedStatement st = getConnection().prepareStatement(commandTwo);
			st.setString(1, UserID);
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
	public List<String> getDecoyImageSetFromDB(String UserID, int pictureSet, int loginMethod) {
		this.connectToDatabase(); // connect to the database
		List<String> imagePaths = new ArrayList<String>();
		String commandTwo = "SELECT * FROM DecoyImageSets Where UserID = ? AND PictureSet = ? And LoginMethod = ?";
		String imagePath = "";
		try {
			PreparedStatement st = getConnection().prepareStatement(commandTwo);
			st.setString(1, UserID);
			st.setInt(2, pictureSet);
			st.setInt(3, loginMethod);
			ResultSet result = st.executeQuery();
			int n = 4; // counter to skip the first two columns return
			while (result.next()) {
				while (n <= result.getMetaData().getColumnCount()) { // get columns 3 to the end
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
	
	public List<String> getUnseenDecoyImageSetFilePathsFromDatabase(String UserID, int pictureSet) {
		connectToDatabase();
		List<String> imagePaths = new ArrayList<String>();
		String commandTwo = "SELECT * FROM UNSEENDECOYIMAGESET Where UserID = ? AND PictureSet = ?";
		String imagePath = "";
		try {
			PreparedStatement st = getConnection().prepareStatement(commandTwo);
			st.setString(1, UserID);
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
	} */
	
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
	public int getRecentLoginAttemptNo(String userID, int loginMethod, int pictureSet) {
		this.connectToDatabase();
//		String command = "SELECT ATTEMPTNUMBER FROM LOGINATTEMPTS Where UserID = ? AND LoginMethod = ? AND PictureSet = ?";
		String command = "SELECT ATTEMPTNUMBER FROM AllLOGINATTEMPTS Where UserID = ? AND LoginMethod = ? AND PictureSet = ? ORDER BY ATTEMPTNUMBER DESC LIMIT 1";
		int loginAttemptNo = 0;
		try {
			PreparedStatement st = getConnection().prepareStatement(command);
			st.setString(1, userID);
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
	
	/**
	 * Get whether a userid were recently successful in loggin in
	 * @param userID a usersid
	 * @param loginMethod - a particular log in method 
	 * @param pictureSet - a particular picture set
	 * @return a number which will be transformed to a boolean in the model
	 */
	public int getRecentLoginSuccess(String userID, int loginMethod, int pictureSet) {
		this.connectToDatabase();
//		String command = "Select Successful From  Where UserID = ? AND LoginMethod = ? And PictureSet = ? ORDER BY ATTEMPTNUMBER DESC LIMIT 1";
		String command = "Select OverallSuccessful From AllLoginAttempts Where UserID = ? And PictureSet = ? AND LoginMethod = ? ORDER BY ATTEMPTNUMBER DESC LIMIT 1";
		int successful = 0;
		try {
			PreparedStatement st = getConnection().prepareStatement(command);
			st.setString(1, userID);
			st.setInt(2, pictureSet);
			st.setInt(3, loginMethod);
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
	public void addLoginAttemptToDatabase(String userID, int loginMethod, int pictureSetSelection, List<String> enteredPassword, List<Double> timeTaken, double overallTimeTaken,
		List<Integer> successOfPasswords, boolean correctPassword, int loginAttemptNo) {
		connectToDatabase();
		Iterator<String> passwordIterator = enteredPassword.iterator();
		Iterator<Double> timeTakenIterator = timeTaken.iterator();
		Iterator<Integer> successIterator = successOfPasswords.iterator();
		int correctPasswordInt = (correctPassword) ? 1 : 0; // convert true to 1 and false to 0
		String command = "INSERT INTO ALLLOGINATTEMPTS(UserID, PictureSet, LoginMethod,  SelectedImageOne, SelectedImageTwo, SelectedImageThree, TimeTakenToChooseImageOne, TimeTakenToChooseImageTwo, TimeTakenToChooseImageThree, OverallTime, successfulImageOne, successfulImageTwo, successfulImageThree, OverallSuccessful, AttemptNumber) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement st = getConnection().prepareStatement(command);
			st.setString(1, userID);
			st.setInt(2, pictureSetSelection);
			st.setInt(3, loginMethod);
			st.setString(4, passwordIterator.next());
			st.setString(5, passwordIterator.next());
			st.setString(6, passwordIterator.next());
			st.setDouble(7, timeTakenIterator.next());
			st.setDouble(8, timeTakenIterator.next());
			st.setDouble(9, timeTakenIterator.next());
			st.setDouble(10, overallTimeTaken);
			st.setInt(11, successIterator.next());
			st.setInt(12, successIterator.next());
			st.setInt(13, successIterator.next());
			st.setInt(14, correctPasswordInt);
			st.setInt(15, loginAttemptNo);
			st.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * This method will update the order of images stored on a database for a user
	 * It will be called if a user unsuccessfully tries to log in, to ensure the shuffled order of images is retained for the next log in attempt
	 * @Param userID, loginMethod, PictureSetSelection and images associated with a particular user
	 */
	public void updateUserFilePaths(String userID, int loginMethod, int pictureSetSelection, List<String> decoyImages) {
		this.connectToDatabase();
		Iterator<String> imagePaths = decoyImages.iterator();
		int n = 1;
		String command = "Update DecoyImageSets Set ImageOne = ?, ImageTwo = ?, ImageThree = ?, ImageFour = ?, ImageFive = ?, ImageSix = ?, ImageSeven = ?, ImageEight = ?, ImageNine = ?, ImageTen = ?, ImageEleven = ?, ImageTwelve = ?, ImageThirteen = ?, ImageFourteen = ?, ImageFifteen = ?, ImageSixteen = ?, ImageSeventeen = ?, ImageEighteen = ?, ImageNineteen = ?, ImageTwenty = ? Where UserID = ? AND PictureSet = ? AND LoginMethod = ?";
		try {
			PreparedStatement st = getConnection().prepareStatement(command);
			while (imagePaths.hasNext()) {
				st.setString(n, imagePaths.next());
				n++;
			}
			st.setString(n, userID);
			n++;
			st.setInt(n, pictureSetSelection);
			n++;
			st.setInt(n, loginMethod);
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
	 * @Param - loginmethod chosen in the registration instruction panel drop down box.
	 * @Return - true is a user is already registered with the details provided.
	 */
	public boolean returnIsRegistered(String userID, int pictureSetSelection, int loginMethod) {
		this.connectToDatabase();
		int count = 0;
		String command = "Select Count(*) From Userregistrations Where UserID = ? AND PictureSet = ? AND LoginMethod = ?";
		try {
			PreparedStatement st = getConnection().prepareStatement(command);
			st.setString(1, userID);
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
	
	/**
	 * Add all of the seen images during the registration phase associated with a registered user
	 * @param userID
	 * @param pictureSet
	 * @param loginMethod
	 * @param images
	 */
	public void addUsersSeenImagestoDatabase(String userID, int pictureSet, int loginMethod, List<String> images) {
		int counter = 4;
		connectToDatabase();
		String command = "Insert into SeenImages(UserID, PictureSet, LoginMethod, ImageOne, ImageTwo, ImageThree, ImageFour, ImageFive, ImageSix, ImageSeven, ImageEight, ImageNine, ImageTen, ImageEleven, ImageTwelve, ImageThirteen, ImageFourteen, ImageFifteen, ImageSixteen, ImageSeventeen, ImageEighteen, ImageNineteen, ImageTwenty, ImageTwentyOne, ImageTwentyTwo, ImageTwentyThree, ImageTwentyFour, ImageTwentyFive, ImageTwentySix, ImageTwentySeven, ImageTwentyEight, ImageTwentyNine, ImageThirty, ImageThirtyOne, ImageThirtyTwo, ImageThirtyThree, ImageThirtyFour, ImageThirtyFive, ImageThirtySix, ImageThirtySeven, ImageThirtyEight, ImageThirtyNine, ImageFourty, ImageFourtyOne, ImageFourtyTwo, ImageFourtyThree, ImageFourtyFour, ImageFourtyFive, ImageFourtySix, ImageFourtySeven, ImageFourtyEight, ImageFourtyNine, ImageFifty, ImageFiftyOne, ImageFiftyTwo, ImageFiftyThree, ImageFiftyFour, ImageFiftyFive, ImageFiftySix, ImageFiftySeven, ImageFiftyEight, ImageFiftyNine, ImageSixty) Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement st = getConnection().prepareStatement(command);
			st.setString(1, userID);
			st.setInt(2, pictureSet);
			st.setInt(3, loginMethod);
			for (String s : images) {
				st.setString(counter, s);
				counter++;
			}
			st.executeUpdate();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				
			}
		}
		
	}

	/**
	 * Method to return all images that were seen during the registration method
	 * @param userid
	 * @param pictureSet
	 * @param loginMethod
	 * @return
	 */
	public List<String> getUserSeenImages(String userid, int pictureSet, int loginMethod) {
		List<String> images = new ArrayList<String>();
		String command = "Select * from SeenImages where UserID = ? AND pictureSet = ? and loginmethod = ?";
		connectToDatabase();
		String imagePath = null;
		try {
			PreparedStatement st = getConnection().prepareStatement(command);
			st.setString(1, userid);
			st.setInt(2, pictureSet);
			st.setInt(3, loginMethod);
			ResultSet result = st.executeQuery();
			int n = 3;
			while (result.next()) {
				while (n <= result.getMetaData().getColumnCount()) {
					imagePath = result.getString(n);
					images.add(imagePath);
					n++;
				}
			}
			result.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return images;
	}
	
	public static void main(String[] args) {
		UserLoginModel m = new UserLoginModel();
		DBConnect db = new DBConnect();
		List<String> s = new ArrayList<String>();
		List<String> t = new ArrayList<String>();
		
		s.addAll(db.getUserSeenImages("1", 1, 1));
		t.addAll(db.getUserSeenImages("1", 1, 2));
//		

		List<String> enteredPassword = new ArrayList<String>();
		enteredPassword.add("C:\\Users\\woohoo\\eclipse-workspace\\project\\art\\image (419).jpg");
		enteredPassword.add("C:\\Users\\woohoo\\eclipse-workspace\\project\\art\\image (33).jpg");
		enteredPassword.add("C:\\Users\\woohoo\\eclipse-workspace\\project\\art\\image (308).jpg");
		List<Double> timeTaken = new ArrayList<Double>();
		timeTaken.add(0.5);
		timeTaken.add(0.5);
		timeTaken.add(0.5);
		double overallTimeTaken = 1.5;
		List<Integer> successOfPasswords = new ArrayList<Integer>();
		successOfPasswords.add(1);
		successOfPasswords.add(1);
		successOfPasswords.add(1);
		boolean correctPassword = true;
		int loginAttemptNo = 20;
		db.addLoginAttemptToDatabase("1", 1, 1, enteredPassword, timeTaken, overallTimeTaken, successOfPasswords, correctPassword, loginAttemptNo);
//		System.out.println("User 1, Picture Set 1, Login Method 1 - Seen Images");
//		for (String p : s) {
//			System.out.println(p);
//		}
//		System.out.println("User 1, Picture Set 1, Login Method 2 - Seen Images");
//		for (String p : t) {
//			System.out.println(p);
//		}
//		t.clear();
//		t.addAll(db.getDecoyImageSetFromDB("1", 1, 1));
//		System.out.println("User 1, Picture Set 1, Login Method 1 - Decoy Images");
//		for (String p : t) {
//			System.out.println(p);
//		}
//		t.clear();
//		t.addAll(db.getUserPasswordFilePathsFromDatabase("1", 1, 1));
//		System.out.println("User 1, Picture Set 1, Login Method 1 - Password");
//		for (String p : t) {
//			System.out.println(p);
//		}
//		t.clear();
//		t.addAll(db.getDecoyImageSetFromDB("1", 1, 2));
//		System.out.println("User 1, Picture Set 1, Login Method 2 - Decoy Images");
//		for (String p : t) {
//			System.out.println(p);
//		}
//		t.clear();
//		t.addAll(db.getUserPasswordFilePathsFromDatabase("1", 1, 2));
//		System.out.println("User 1, Picture Set 1, Login Method 2 - Password");
//		for (String p : t) {
//			System.out.println(p);
//		}
	}
}