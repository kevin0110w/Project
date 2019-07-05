import java.sql.*;
import java.util.ArrayList;
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
//			statement = connection.createStatement();
//			String command = "INSERT INTO USERS(UserID, UserPassword) VALUES(1, 'aaaaaaaa')";
//			statement.executeUpdate(command);
//			String commandThree = "Delete From Users";
//			statement.executeUpdate(commandThree);
//			String commandTwo = "SELECT * FROM Users";
//			ResultSet result = statement.executeQuery(commandTwo);
//			while (result.next()) {
//			System.out.println("Name: " + result.getInt("UserID") + " Password: " + result.getString("UserPassword"));
//			}
		} catch (SQLException e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}
		return connection;
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
	
	public List<String> getUsersPasswordsFromDatabase(int UserID, int loginAttempt) {
		List<String> imagePaths = new ArrayList<String>();
		String commandTwo = "SELECT * FROM Users Where UserID = ? AND LoginMethod = ?";
		String imagePath = "";
		try {
			PreparedStatement st = getConnection().prepareStatement(commandTwo);
			st.setInt(1, UserID);
			st.setInt(2, loginAttempt);
			ResultSet result = st.executeQuery();
//		ResultSet result = statement.executeQuery(commandTwo);
			int n = 3;
			while (result.next()) {
			while (n<6) {
//		System.out.println("Name: " + result.getInt("UserID") + " Password: " + result.getString("UserPassword"));
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
	
	public List<String> getUsersFilePathsFromDatabase(int UserID, int loginAttempt) {
//	public Set<String> getUsersFilePathsFromDatabase(int UserID, int loginAttempt) {
		List<String> imagePaths = new ArrayList<String>();
		String commandTwo = "SELECT * FROM Users Where UserID = ? AND LoginMethod = ?";
		String imagePath = "";
		try {
			PreparedStatement st = getConnection().prepareStatement(commandTwo);
			st.setInt(1, UserID);
			st.setInt(2, loginAttempt);
			ResultSet result = st.executeQuery();
//		ResultSet result = statement.executeQuery(commandTwo);
			int n = 3;
			while (result.next()) {
			while (n <= result.getMetaData().getColumnCount()) {
//		System.out.println("Name: " + result.getInt("UserID") + " Password: " + result.getString("UserPassword"));
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

	public void addUserToDatabase(User user) {
		Iterator<String> it = user.decoys.iterator();
		int n = 6;
		String command = "INSERT INTO USERS(UserID, LoginMethod, PasswordOne, PasswordTwo, PasswordThree, DecoyImageOne, DecoyImageTwo, DecoyImageThree, DecoyImageFour, DecoyImageFive, DecoyImageSix, DecoyImageSeven, DecoyImageEight, DecoyImageNine, DecoyImageTen, DecoyImageEleven, DecoyImageTwelve, DecoyImageThirteen, DecoyImageFourteen, DecoyImageFifteen, DecoyImageSixteen, DecoyImageSeventeen) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement st = getConnection().prepareStatement(command);
			st.setInt(1, user.getUserid());
			st.setInt(2, user.getLoginattempt());
			st.setString(3, user.getPasswordOne());
			st.setString(4, user.getPasswordTwo());
			st.setString(5, user.getPasswordThree());
			while (it.hasNext()) {
				st.setString(n, it.next());
				n++;
			}
			st.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public Connection getConnection() {
		return this.connection;
	}

	public String getPasswordFromDatabase(int userID) {
		String commandTwo = "SELECT * FROM Users Where UserID = ?";
		String password = "";
		try {
			PreparedStatement st = getConnection().prepareStatement(commandTwo);
			st.setInt(1, userID);
			ResultSet result = st.executeQuery();
//		ResultSet result = statement.executeQuery(commandTwo);
			while (result.next()) {
//		System.out.println("Name: " + result.getInt("UserID") + " Password: " + result.getString("UserPassword"));
				password = result.getString("UserPassword");
			}
		} catch (SQLException e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}
		return password;
	}
	
	public int returnLatestAddedUserID() {
		String commandTwo = "SELECT * FROM Users Order By UserID DESC LIMIT 1 ";
		int UserID = 0;
		ResultSet result;
		
		try {
//			String del = "DELETE FROM USERS";
//			s.executeUpdate(del);
			result = getStatement().executeQuery(commandTwo);
			while (result.next()) {
				UserID = result.getInt("UserID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return UserID;
	}
	
	
	/*
	public static void main(String[] args) {
		DBConnect connect = new DBConnect();
		Connection c = connect.connectToDatabase();
		Statement s = connect.getStatement();
//		int x = connect.returnLatestAddedUserID();
//		System.out.println(x*10);
//		String sql = "Select * from Users";
//		String sql = "Alter TABLE USERS ADD COLUMN PasswordTwo VarChar NOT NULL, ADD COLUMN PasswordThree VarChar NOT NULL";
//		String sql = "Alter Table Users Rename UserPassword to LoginAttempt";
		String sql = "ALTER TABLE users ALTER COLUMN LoginAttempt Type INT USING LoginAttempt::integer";
//		String sql = "Delete from Users";
//		ResultSet result;
		try {
////			String del = "DELETE FROM USERS";
////			s.executeUpdate(del);
//			result = s.executeQuery(sql);
			s.executeUpdate(sql);
//			while (result.next()) {
//				System.out
//						.println("Name: " + result.getInt("UserID") + " Password: " + result.getString("UserPassword"));
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/

	public void addLoginAttemptToDB(int userID, int loginMethod, List<String> enteredPassword, List<Float> timeTaken,
			boolean correctPassword, int loginAttemptNo) {
		Iterator<String> passwordIterator = enteredPassword.iterator();
		Iterator<Float> timeTakenIterator = timeTaken.iterator();
		int correctPasswordInt = (correctPassword) ? 1 : 0;
		String command = "INSERT INTO LOGINATTEMPTS(UserID, LoginMethod, SelectedImageOne, SelectedImageTwo, SelectedImageThree, TimeTakenToChooseOne, TimeTakenToChooseTwo, TimeTakenToChooseThree, Successful, AttemptNumber) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement st = getConnection().prepareStatement(command);
			st.setInt(1, userID);
			st.setInt(2, loginMethod);
			st.setString(3, passwordIterator.next());
			st.setString(4, passwordIterator.next());
			st.setString(5, passwordIterator.next());
			st.setFloat(6, timeTakenIterator.next());
			st.setFloat(7, timeTakenIterator.next());
			st.setFloat(8, timeTakenIterator.next());
			st.setInt(9, correctPasswordInt);
			st.setInt(10, loginAttemptNo);
			st.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public int getLoginAttemptNo(int userID, int loginMethod) {
		String command = "SELECT ATTEMPTNUMBER FROM LOGINATTEMPTS Where UserID = ? AND LoginMethod = ?";
		int loginAttemptNo = 0;
		try {
			PreparedStatement st = getConnection().prepareStatement(command);
			st.setInt(1, userID);
			st.setInt(2, loginMethod);
			ResultSet result = st.executeQuery();
			while (result.next()) {
				loginAttemptNo = result.getInt("AttemptNumber");
			}
		} catch (SQLException e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}
		return loginAttemptNo;
	}
	
	public void addRegistrationTime(User user, List<Float> timeTaken) {
		Iterator<Float> timeTakenIterator = timeTaken.iterator();
		String command = "Insert into RegistrationTimes(UserID, LoginMethod, TimeTakenToChoosePasswordOne, TimeTakenToChoosePasswordTwo, TimeTakenToChoosePasswordThree) Values (?, ?, ?, ?, ?)";
		try {
			PreparedStatement st = getConnection().prepareStatement(command);
			st.setInt(1, user.getUserid());
			st.setInt(2, user.getLoginattempt());
			st.setFloat(3, timeTakenIterator.next());
			st.setFloat(4, timeTakenIterator.next());
			st.setFloat(5, timeTakenIterator.next());
			st.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		DBConnect db = new DBConnect();
		System.out.println(db.getRecentLoginSuccess(200, 1));
	}

	public int getRecentLoginSuccess(int userID, int loginMethod) {
		String command = "Select Successful From LoginAttempts Where UserID = ? AND LoginMethod = ? ORDER BY ATTEMPTNUMBER DESC LIMIT 1";
		int successful = 0;
		try {
			PreparedStatement st = getConnection().prepareStatement(command);
			st.setInt(1, userID);
			st.setInt(2, loginMethod);
			ResultSet result = st.executeQuery();
			while (result.next()) {
				successful = result.getInt("Successful");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return successful;
	}
	
}