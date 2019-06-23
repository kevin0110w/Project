import java.sql.*;


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
	public static void main(String[] args) {
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
			String command = "INSERT INTO USERS(UserID, UserPassword) VALUES(?, ?)";
			try {
				PreparedStatement st = getConnection().prepareStatement(command);
				st.setInt(1, user.getUserID());
				st.setString(2, user.getPassword());
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
			st.setInt(1,  userID);
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
}
