import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.Icon;
import javax.swing.JButton;

public class UserLoginModel {
	private DBConnect db;
//	private Set<String> filePaths;
	private List<String> images;
	private List<String> passwordPaths;
	private final int NOOFPASSWORDS = 3;
	private int UserID, loginMethod, loginAttemptNo, pictureSetSelection;
	private List<String> enteredPassword;
	private List<Double> timeTaken;
	private LocalDateTime initialTime;
	private List<Integer> successOfPasswords;
	private boolean lastLoginSuccesful;
	
	public UserLoginModel() {
		this.db = new DBConnect();
		this.images = new ArrayList<String>();
		this.passwordPaths = new ArrayList<String>();
		this.enteredPassword = new ArrayList<String>();
		this.timeTaken = new ArrayList<Double>();
		this.successOfPasswords = new ArrayList<Integer>();
	}
	
	public List<String> getEnteredPasswords() {
		return this.enteredPassword;
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
		switch(loginMethod) {
			case 1:
				this.images.addAll(db.getSeenDecoyImageSetFilePathsFromDatabase(UserID, pictureSetSelection));
				break;
			case 2:
				this.images.addAll(db.getUnseenDecoyImageSetFilePathsFromDatabase(UserID, pictureSetSelection));
				break;
//		this.filePaths.addAll(db.getRegistrationsFilePathsFromDatabase(UserID, loginMethod, pictureSetSelection));
//		Iterator<String> it = filePaths.iterator();
//		Testing to make sure getting the correct filepaths from the db
//		while (it.hasNext()) {
//			System.out.println(it.next());
//		}
		}
		formPasswordPaths();
//		this.db.closeConnection();
	}
		
	private void formPasswordPaths() {
//		int n = 0;
//		while (n < getNoofpasswords()) {
			this.passwordPaths.addAll(db.getUserPasswordFilePathsFromDatabase(UserID, pictureSetSelection));
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
	public List<String> getDecoyImages() {
		getUsersImages();
		shuffleDecoyImages();
		return images;
	}

	/**
	 * @param filePaths the filePaths to set
	 */
	public void setDecoyImages(List<String> filePaths) {
		this.images.addAll(filePaths);
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

	public void addImage(Object source) {
		Icon imageIcon = ((JButton) source).getIcon();
		this.enteredPassword.add(imageIcon.toString());
	}
	
	public boolean correctPassword() {
		return this.enteredPassword.equals(this.passwordPaths); 
	}

	public void addLoginAttempt() {
		DBConnect db = new DBConnect();
		addSuccessOfPasswords();
//		db.addLoginAttemptToDB(this.getUserID(), this.getLoginMethod(), this.enteredPassword, this.timeTaken, this.correctPassword(), this.successOfPasswords, this.loginAttemptNo, this.pictureSetSelection);
		db.addLoginAttemptToDatabase(this.getUserID(), this.getLoginMethod(), this.pictureSetSelection, this.enteredPassword, this.timeTaken, this.successOfPasswords, this.correctPassword(), this.loginAttemptNo);
		if (!this.correctPassword()) {
			db.updateUserFilePaths(this.UserID, this.loginMethod, this.pictureSetSelection, this.images);
		}
		db.closeConnection();
	}
	
	public void addTimeTakenPerPassword(Double time) {
		this.timeTaken.add(time);
	}

	public void setInitialTime() {
		initialTime = LocalDateTime.now();
	}

	public void addTime() {
		LocalDateTime intermediateTime = LocalDateTime.now();
		Duration timeBetween = Duration.between(initialTime, intermediateTime);
		double milliseconds = timeBetween.toMillis();
		double seconds = milliseconds / 1000;
		this.timeTaken.add(seconds);
		setInitialTime();
	}
	
	public void setLoginAttempt() {
		DBConnect db = new DBConnect();
		this.loginAttemptNo = db.getRecentLoginAttemptNo(UserID, loginMethod, pictureSetSelection);
		this.loginAttemptNo = (this.loginAttemptNo == 0) ? 1 : ++this.loginAttemptNo;
		System.out.println(this.loginAttemptNo + " in model");
		db.closeConnection();
	}
	
	public int getLoginAttempt() {
		return this.loginAttemptNo;
	}
	
	public int getMostRecentLoginSuccess() {
		DBConnect db = new DBConnect();
		int getLoginSuccessful = db.getRecentLoginSuccess(this.UserID, this.loginMethod, this.pictureSetSelection);
		return getLoginSuccessful;
	}
	
	public void returnMostRecentLoginSuccess() {
		int successful = getMostRecentLoginSuccess();
		this.lastLoginSuccesful = (successful > 0) ? true : false;
	}
	
	private void shuffleDecoyImages() {
		if (this.lastLoginSuccesful || this.loginAttemptNo == 1) {
			Collections.shuffle(this.images);
		}
	}

	private void addSuccessOfPasswords() {
		for (int i = 0; i < enteredPassword.size(); i++) {
			if (enteredPassword.get(i).equals(passwordPaths.get(i))) {
				this.successOfPasswords.add(1);
			} else {
				this.successOfPasswords.add(0);
			}
		}
	}

	public void setPictureSelection(int pictureSelection) {
		this.pictureSetSelection = pictureSelection;
	}

	public void clear() {
		images.clear();
		passwordPaths.clear();
		UserID = 0;
		loginMethod = 0;
		loginAttemptNo = 0;
		pictureSetSelection = 0;
		enteredPassword.clear();
		timeTaken.clear();
		successOfPasswords.clear();
		lastLoginSuccesful = false;
	}

	public boolean returnIsValid() {
		DBConnect db = new DBConnect();
		boolean isValid = db.returnIsRegistered(this.UserID, this.pictureSetSelection);
		return isValid;
	}
}
