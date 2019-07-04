import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.Icon;
import javax.swing.JButton;

public class UserLoginModel {
	private DBConnect db;
	private Set<String> filePaths;
	private List<String> passwordPaths;
	private final int NOOFPASSWORDS = 3;
	private int UserID, loginMethod;
	private List<String> enteredPassword;
	
	public UserLoginModel() {
		this.db = new DBConnect();
		this.filePaths = new HashSet<String>();
		this.passwordPaths = new ArrayList<String>();
		this.enteredPassword = new ArrayList<String>();
	}
	
	public void setUserID(int UserID) {
		this.UserID = UserID;
	}
	
	public void setLoginMethod(int loginMethod) {
		this.loginMethod = loginMethod;
	}
	
	public int getUserID() {
		return this.UserID;
	}
	
	public int getLoginMethod() {
		return this.loginMethod;
	}
	
	public void getUsersImages() {
		this.filePaths.addAll(db.getUsersFilePathsFromDatabase(UserID, loginMethod));
//		Iterator<String> it = filePaths.iterator();
//		Testing to make sure getting the correct filepaths from the db
//		while (it.hasNext()) {
//			System.out.println(it.next());
//		}
		formPasswordPaths();
//		this.db.closeConnection();
	}
		
	private void formPasswordPaths() {
//		int n = 0;
//		while (n < getNoofpasswords()) {
			this.passwordPaths.addAll(db.getUsersPasswordsFromDatabase(UserID, loginMethod));
//			n++;
//		}
	}

	/**
	 * @return the db
	 */
	public DBConnect getDb() {
		return db;
	}

	/**
	 * @param db the db to set
	 */
	public void setDb(DBConnect db) {
		this.db = db;
	}

	/**
	 * @return the filePaths
	 */
	public Set<String> getFilePaths() {
		getUsersImages();
		return filePaths;
	}

	/**
	 * @param filePaths the filePaths to set
	 */
	public void setFilePaths(List<String> filePaths) {
		this.filePaths.addAll(filePaths);
	}

	/**
	 * @return the passwordPaths
	 */
	public List<String> getPasswordPaths() {
		
		return passwordPaths;
	}

	/**
	 * @param passwordPaths the passwordPaths to set
	 */
	public void setPasswordPaths(List<String> passwordPaths) {
		this.passwordPaths = passwordPaths;
	}

	/**
	 * @return the noofpasswords
	 */
	public int getNoofpasswords() {
		return NOOFPASSWORDS;
	}
	
//	public static void main(String[] args) {
//		UserLoginModel m = new UserLoginModel();
//		m.getUsersImages(22222, 1);
//		for (String s : m.getPasswordPaths()) {
//			System.out.println(s);
//		}
//	}

	public void addImage(Object source) {
		Icon imageIcon = ((JButton) source).getIcon();
		this.enteredPassword.add(imageIcon.toString());
	}
	
	public boolean correctPassword() {
		return this.enteredPassword.equals(this.passwordPaths); 
	}

	public boolean getTrue() {
		return false;
	}
}
