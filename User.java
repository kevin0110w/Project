
public class User {
	private int ID = 11;
	private String UserPassword;
	private int UserID;
	
	public User(String password) {
		this.UserPassword = password;
		this.UserID = this.ID;
		this.ID++;
	}
	
	public int getUserID() {
		return this.UserID;
	}
	
	public String getPassword() {
		return this.UserPassword;
	}
	
	public String toString() {
		return "" + this.UserID;
	}
}
