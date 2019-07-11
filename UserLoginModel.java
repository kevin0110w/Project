import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JButton;

/**
 * This class is responsible for storing the data during the login phase and coordinating this with the DBConnect class for that data to be persistantly stored
 */
public class UserLoginModel {
	private DBConnect db;
	private List<String> images; // 20 images to be displayed to user
	private List<String> passwordPaths; // a list of the 3 images - password
	private final int NOOFPASSWORDS = 3;
	private int UserID, loginMethod, loginAttemptNo, pictureSetSelection;
	private List<String> enteredPassword; // a list of the selected password filepaths
	private List<Double> timeTaken; // a list of times takens to select an image
	private LocalDateTime initialTime; // an initial time which is reset after every click
	private List<Integer> successOfPasswords; // a list of 1s and 0s to show whether a selection is correct
	private boolean lastLoginSuccesful; // whether the most recent log in was successful
	
	
	public UserLoginModel() {
		this.db = new DBConnect();
		this.images = new ArrayList<String>();
		this.passwordPaths = new ArrayList<String>();
		this.enteredPassword = new ArrayList<String>();
		this.timeTaken = new ArrayList<Double>();
		this.successOfPasswords = new ArrayList<Integer>();
	}
	
	/**
	 * Get list of enetered passwords
	 * @return
	 */
	public List<String> getEnteredPasswords() {
		return this.enteredPassword;
	}
	
	/**
	 * Set a userID
	 * @param UserID
	 */
	public void setUserID(int UserID) {
		this.UserID = UserID;
	}
	
	/**
	 * Set login method
	 * @param loginMethod
	 */
	public void setLoginMethod(int loginMethod) {
		this.loginMethod = loginMethod;
	}
	
	/**
	 * get a User Id
	 * @return
	 */
	public int getUserID() {
		return this.UserID;
	}
	
	/**
	 * Get a login method
	 * @return
	 */
	public int getLoginMethod() {
		return this.loginMethod;
	}
	
	/**
	 * Get a users images
	 * A particular set will be retrieved from the database because the user will have chosen in the drop down box in the instruction panel
	 * 
	 */
	public void getUsersImages() {
		switch(loginMethod) {
			case 1:
				this.images.addAll(db.getSeenDecoyImageSetFilePathsFromDatabase(UserID, pictureSetSelection));
				break;
			case 2:
				this.images.addAll(db.getUnseenDecoyImageSetFilePathsFromDatabase(UserID, pictureSetSelection));
				break;
		}
		formPasswordPaths(); 
	}
		
	/**
	 * Form an indexed list of password image file paths
	 */
	private void formPasswordPaths() {
		this.passwordPaths.addAll(db.getUserPasswordFilePathsFromDatabase(UserID, pictureSetSelection));
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
	 * First get them from the database
	 * Next shuffle them if the most recent login was successful
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

	/**
	 * Store a selected image's filepath to the list of entered passwords
	 * @param source
	 */
	public void addImage(Object source) {
		Icon imageIcon = ((JButton) source).getIcon();
		this.enteredPassword.add(imageIcon.toString());
	}
	
	/**
	 * Check whether the list of entered passwords is the same as the actual user's password
	 * @return
	 */
	public boolean correctPassword() {
		return this.enteredPassword.equals(this.passwordPaths); 
	}

	/**
	 * Pass the data to the DBConnect class to add an attmempt to the database
	 * If this login attempt wasn't successful, also pass data for the images to be stored in it's current order.
	 */
	public void addLoginAttempt() {
		addSuccessOfPasswords();
//		db.addLoginAttemptToDB(this.getUserID(), this.getLoginMethod(), this.enteredPassword, this.timeTaken, this.correctPassword(), this.successOfPasswords, this.loginAttemptNo, this.pictureSetSelection);
		db.addLoginAttemptToDatabase(this.getUserID(), this.getLoginMethod(), this.pictureSetSelection, this.enteredPassword, this.timeTaken, this.successOfPasswords, this.correctPassword(), this.loginAttemptNo);
		if (!this.correctPassword()) {
			db.updateUserFilePaths(this.UserID, this.loginMethod, this.pictureSetSelection, this.images);
		}
	}
	
	/**
	 * Add time taken for a click to the indexed list
	 * @param time
	 */
	public void addTimeTakenPerPassword(Double time) {
		this.timeTaken.add(time);
	}

	/**
	 * set the initial time
	 */
	public void setInitialTime() {
		initialTime = LocalDateTime.now();
	}

	/**
	 * Calculate the time difference between clicks and reset the clock
	 */
	public void addTime() {
		LocalDateTime intermediateTime = LocalDateTime.now();
		Duration timeBetween = Duration.between(initialTime, intermediateTime);
		double milliseconds = timeBetween.toMillis();
		double seconds = milliseconds / 1000;
		this.timeTaken.add(seconds);
		setInitialTime();
	}
	
	/**
	 * Get the recent login attempt from the database and increment it and set it to the field variable, so that when this is stored
	 * in the database, it is up to date
	 */
	public void setLoginAttempt() {
		this.loginAttemptNo = db.getRecentLoginAttemptNo(UserID, loginMethod, pictureSetSelection);
		this.loginAttemptNo = (this.loginAttemptNo == 0) ? 1 : ++this.loginAttemptNo;
		System.out.println(loginAttemptNo);
	}
	
	/**
	 * get the login attempt number
	 * @return
	 */
	public int getLoginAttempt() {
		return this.loginAttemptNo;
	}
	
	/**
	 * Get the integer associated with the recent login success rate
	 * @return
	 */
	public int getMostRecentLoginSuccess() {
		int getLoginSuccessful = db.getRecentLoginSuccess(this.UserID, this.loginMethod, this.pictureSetSelection);
		return getLoginSuccessful;
	}
	
	/**
	 * Convert the recent login success rate to a boolean
	 */
	public void returnMostRecentLoginSuccess() {
		int successful = getMostRecentLoginSuccess();
		this.lastLoginSuccesful = (successful > 0) ? true : false;
	}
	
	/**
	 * If the most recent log in was successful or if this is the first attempt, then make sure the images are shuffled
	 */
	private void shuffleDecoyImages() {
		if (this.lastLoginSuccesful || this.loginAttemptNo == 1) {
			Collections.shuffle(this.images);
		}
	}

	/**
	 * Checks whether each element in the entered password is the same as the password list and add it to the list named successOfPasswords
	 * 1 = true, 0 = false;
	 */
	private void addSuccessOfPasswords() {
		for (int i = 0; i < enteredPassword.size(); i++) {
			if (enteredPassword.get(i).equals(passwordPaths.get(i))) {
				this.successOfPasswords.add(1);
			} else {
				this.successOfPasswords.add(0);
			}
		}
	}
	
	/**
	 * Set the picture Selection
	 * @param pictureSelection
	 */
	public void setPictureSelection(int pictureSelection) {
		this.pictureSetSelection = pictureSelection;
	}

	/**
	 * Return whether the data entered into the instruction panel already belongs to a registered user. 
	 * @return true if already registered
	 */
	public boolean returnIsValid() {
		boolean isValid = db.returnIsRegistered(this.UserID, this.pictureSetSelection);
		return isValid;
	}

	/**
	 * A reset method to clear all locally stored data and ensure there is no carry over with future logins.
	 */
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
}
