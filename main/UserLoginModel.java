package main;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JButton;

/**
 * This class is responsible for storing the data during the login phase and coordinating this with the DBConnect class for that data to be persistently stored
 */
public class UserLoginModel {
	private String UserID;
	private DBConnect db;
	private List<String> images, passwordPaths, enteredPasswords; // 20 images to be displayed to user
	private final int NOOFPASSWORDS = 3;
//	private int UserID, loginMethod, loginAttemptNo, pictureSetSelection;
	private int loginMethod, loginAttemptNo, pictureSetSelection;
	private List<Double> timeTaken; // a list of times takens to select an image
	private double totalTimeTaken;
	private LocalDateTime initialTime; // an initial time which is reset after every click
	private List<Integer> successOfPasswords; // a list of 1s and 0s to show whether a selection is correct
	private boolean lastLoginSuccesful, shuffled; // whether the most recent log in was successful

	public UserLoginModel() {
		this.db = new DBConnect();
		this.images = new ArrayList<String>();
		this.passwordPaths = new ArrayList<String>();
		this.enteredPasswords = new ArrayList<String>();
		this.timeTaken = new ArrayList<Double>();
		this.totalTimeTaken = 0;
		this.successOfPasswords = new ArrayList<Integer>();
	}

	/**
	 * @return the successOfPasswords
	 */
	public List<Integer> getSuccessOfPasswords() {
		return successOfPasswords;
	}

	/**
	 * @return the pictureSetSelection
	 */
	public int getPictureSetSelection() {
		return pictureSetSelection;
	}

	/**
	 * @param pictureSetSelection the pictureSetSelection to set
	 */
	public void setPictureSetSelection(int pictureSetSelection) {
		this.pictureSetSelection = pictureSetSelection;
	}

	/**
	 * @return the enteredPassword
	 */
	public List<String> getEnteredPassword() {
		return enteredPasswords;
	}

	/**
	 * @param enteredPassword the enteredPassword to set
	 */
	public void setEnteredPassword(List<String> enteredPassword) {
		this.enteredPasswords = enteredPassword;
	}

	/**
	 * @return the initialTime
	 */
	public LocalDateTime getInitialTime() {
		return initialTime;
	}

	/**
	 * @param initialTime the initialTime to set
	 */
	public void setInitialTime(LocalDateTime initialTime) {
		this.initialTime = initialTime;
	}

	/**
	 * @return the lastLoginSuccesful
	 */
	public boolean getLastLoginSuccesful() {
		return lastLoginSuccesful;
	}

	/**
	 * @param lastLoginSuccesful the lastLoginSuccesful to set
	 */
	public void setLastLoginSuccesful(boolean lastLoginSuccesful) {
		this.lastLoginSuccesful = lastLoginSuccesful;
	}

	/**
	 * @return the nOOFPASSWORDS
	 */
	public int getNOOFPASSWORDS() {
		return NOOFPASSWORDS;
	}
	
	public void setLoginAttemptNo(int number) {
		this.loginAttemptNo = number;
	}

	/**
	 * @return the loginAttemptNo
	 */
	public int getLoginAttemptNo() {
		return loginAttemptNo;
	}

	/**
	 * @param successOfPasswords the successOfPasswords to set
	 */
	public void setSuccessOfPasswords(List<Integer> successOfPasswords) {
		this.successOfPasswords = successOfPasswords;
	}

	/**
	 * Get list of enetered passwords
	 * @return
	 */
	public List<String> getEnteredPasswords() {
		return this.enteredPasswords;
	}

	/**
	 * Set a userID
	 * @param UserID
	 */
	public void setUserID(String UserID) {
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
	public String getUserID() {
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
		this.images.addAll(db.getDecoyImageSetFromDB(UserID, pictureSetSelection, loginMethod));
		printImages();
	}

	private void printImages() {
		if (lastLoginSuccesful) {
			System.out.println("last login successful");
		} else {
			System.out.println("last login unsuccessful");
		}
		for (String s : this.images) {
			System.out.println(s);
		}
	}

	/**
	 * @return the images
	 */
	public List<String> getImages() {
		return images;
	}

	/**
	 * @param images the images to set
	 */
	public void setImages(List<String> images) {
		this.images = images;
	}

	/**
	 * @return the timeTaken
	 */
	public List<Double> getTimeTaken() {
		return timeTaken;
	}

	/**
	 * @param timeTaken the timeTaken to set
	 */
	public void setTimeTaken(List<Double> timeTaken) {
		this.timeTaken = timeTaken;
	}

	/**
	 * Form an indexed list of password image file paths
	 */
	public void formPasswordPaths() {
		this.passwordPaths.addAll(db.getUserPasswordFilePathsFromDatabase(UserID, pictureSetSelection, loginMethod));
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
	 * @param filePaths the filePaths to set
	 */
	public void setDecoyImages(List<String> filePaths) {
		this.images.addAll(filePaths);
	}
	
	/**
	 * Set the picture Selection
	 * @param pictureSelection
	 */
	public void setPictureSelection(int pictureSelection) {
		this.pictureSetSelection = pictureSelection;
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

	public void setShuffled(boolean b) {
		this.shuffled = b;
		
	}

	public boolean getShuffled() {
		return this.shuffled;
	}
	
	/**
	 * get the login attempt number
	 * @return
	 */
	public int getLoginAttempt() {
		return this.loginAttemptNo;
	}
	
	/**
	 * First get them from the database
	 * Next shuffle them if the most recent login was successful
	 * @return the filePaths
	 */
	public List<String> getDecoyImages() {
		getUsersImages();
		formPasswordPaths();
		shuffleDecoyImages();
		return images;
	}

	/**
	 * Store a selected image's filepath to the list of entered passwords
	 * @param source
	 */
	public void addImage(Object source) {
		Icon imageIcon = ((JButton) source).getIcon();
		this.enteredPasswords.add(imageIcon.toString());
	}

	/**
	 * Check whether the list of entered passwords is the same as the actual user's password
	 * @return
	 */
	public boolean correctPassword() {
		return this.enteredPasswords.equals(this.passwordPaths);
	}

	/**
	 * Pass the data to the DBConnect class to add an attmempt to the database
	 * If this login attempt wasn't successful, also pass data for the images to be stored in it's current order.
	 */
	public void addLoginAttempt() {
		addSuccessOfPasswords();
//		db.addLoginAttemptToDB(this.getUserID(), this.getLoginMethod(), this.enteredPassword, this.timeTaken, this.correctPassword(), this.successOfPasswords, this.loginAttemptNo, this.pictureSetSelection);
		db.addLoginAttemptToDatabase(this.getUserID(), this.getLoginMethod(), this.pictureSetSelection, this.enteredPasswords, this.timeTaken, this.totalTimeTaken, this.successOfPasswords, this.correctPassword(), this.loginAttemptNo);
		
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
		this.totalTimeTaken += seconds;
		System.out.println(totalTimeTaken);
		setInitialTime();
	}

	/**
	 * Get the recent login attempt from the database and increment it and set it to the field variable, so that when this is stored
	 * in the database, it is up to date
	 */
	public void setLoginAttempt() {
		this.loginAttemptNo = db.getRecentLoginAttemptNo(UserID, loginMethod, pictureSetSelection);
		this.loginAttemptNo = (this.loginAttemptNo == 0) ? 1 : ++this.loginAttemptNo;
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
	public void shuffleDecoyImages() {
		if (this.lastLoginSuccesful && !this.getShuffled() || this.loginAttemptNo == 1) {
			Collections.shuffle(this.images);
			this.setShuffled(true);
		}
	}


	/**
	 * Checks whether each element in the entered password is the same as the password list and add it to the list named successOfPasswords
	 * 1 = true, 0 = false; 2 = selected image is found in the password but was chosen in the incorrect order
	 */
	public void addSuccessOfPasswords() {
		for (int i = 0; i < enteredPasswords.size(); i++) {
			if (enteredPasswords.get(i).equals(passwordPaths.get(i))) {
				this.successOfPasswords.add(1);
			} else if (passwordPaths.contains(enteredPasswords.get(i))) {
				this.successOfPasswords.add(2);
			} else {
				this.successOfPasswords.add(0);
			}
		}
	}


	/**
	 * Return whether the data entered into the instruction panel already belongs to a registered user.
	 * @return true if already registered
	 */
	public boolean returnIsValid() {
//		boolean isValid = db.returnIsRegistered(this.UserID, this.pictureSetSelection);
		boolean isValid = db.returnIsRegistered(this.UserID, this.pictureSetSelection, this.loginMethod);
		return isValid;
	}
	

	/**
	 * A reset method to clear all locally stored data and ensure there is no carry over with future logins.
	 */
	public void clear() {
		images.clear();
		passwordPaths.clear();
		UserID = null;
		loginMethod = 0;
		loginAttemptNo = 0;
		pictureSetSelection = 0;
		enteredPasswords.clear();
		timeTaken.clear();
		successOfPasswords.clear();
		lastLoginSuccesful = false;
		shuffled = false;
		totalTimeTaken = 0.0;
	}

//	public boolean getSuccessfulAttempt() {
//		// TODO Auto-generated method stub
//		return this.successful;
//	}
}
