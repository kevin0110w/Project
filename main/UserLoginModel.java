
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class is responsible for storing the data during the login phase and coordinating this with the DBConnect class for that data to recorded on the database.
 */
public class UserLoginModel {
	private String UserID;
	private DBConnect db;
	private List<String> challengeSet, passwordPaths, enteredPasswords;
	private final int NOOFPASSWORDS = 3;
	private int loginMethod, loginAttemptNo, pictureSetSelection;
	private List<Double> timeTaken;
	private double totalTimeTaken;
	private LocalDateTime initialTime;
	private List<Integer> successOfPasswords;
	private boolean lastLoginSuccesful, shuffled;

	public UserLoginModel() {
		this.db = new DBConnect();
		this.challengeSet = new ArrayList<String>();
		this.passwordPaths = new ArrayList<String>();
		this.enteredPasswords = new ArrayList<String>();
		this.timeTaken = new ArrayList<Double>();
		this.totalTimeTaken = 0;
		this.successOfPasswords = new ArrayList<Integer>();
	}

	/**
	 * @return the pictureSetSelection
	 */
	public int getPictureSetSelection() {
		return pictureSetSelection;
	}

	/**
	 * @param pictureSetSelection - the pictureSetSelection chosen from the JCombo box in the user login instructions panel.
	 */
	public void setPictureSetSelection(int pictureSetSelection) {
		this.pictureSetSelection = pictureSetSelection;
	}

	/**
	 * @return the enteredPassword.
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
	 * Get list of enetered passwords
	 * @return
	 */
	public List<String> getEnteredPasswords() {
		return this.enteredPasswords;
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
	 * @return the successOfPasswords
	 * if an image is correct, an index's element will be 1, if an image is correct but in the wrong order, the index's element will display 2. If the image is not in the password, the
	 * index's element will display 0;
	 */
	public List<Integer> getSuccessOfPasswords() {
		return successOfPasswords;
	}

	/**
	 * Set a userID
	 * @param UserID
	 */
	public void setUserID(String UserID) {
		this.UserID = UserID;
	}

	/**
	 * get a User Id
	 * @return
	 */
	public String getUserID() {
		return this.UserID;
	}

	/**
	 * Set login method
	 * @param loginMethod chosen from the login instruction view.
	 */
	public void setLoginMethod(int loginMethod) {
		this.loginMethod = loginMethod;
	}

	/**
	 * Get a login method
	 * @return
	 */
	public int getLoginMethod() {
		return this.loginMethod;
	}

	/**
	 * @return the images
	 */
	public List<String> getChallengeSet() {
		return challengeSet;
	}

	/**
	 * @param images the images to set
	 */
	public void setChallengeSet(List<String> images) {
		this.challengeSet = images;
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
	 * Set whether the image set has been shuffled or not.
	 * @param b
	 */
	public void setShuffled(boolean b) {
		this.shuffled = b;

	}

	/**
	 * Return whether a challenge set has been shuffled
	 * @return
	 */
	public boolean getShuffled() {
		return this.shuffled;
	}



	/**
	 * First get a user's challenge set them from the database
	 * Next shuffle them if the most recent login was successful
	 * @return the challenge set
	 */
	public List<String> getUsersImages() {
		getUsersChallengeSet();
		formPasswordPaths();
		shuffleDecoyImages();
		return challengeSet;
	}

	/**
	 * Get a user's challenge set
	 * A particular set will be retrieved from the database based on the userid, pictureset, login method combination chosen the in login instruction view.
	 *
	 */
	private void getUsersChallengeSet() {
		setChallengeSet(db.getUserChallengeSetFromDB(UserID, pictureSetSelection, loginMethod));
	}

	/**
	 * Form an indexed list of a user's password image file paths
	 */
	private void formPasswordPaths() {
		setPasswordPaths(db.getUserPasswordFilePathsFromDatabase(UserID, pictureSetSelection, loginMethod));
	}

	/**
	 * If the most recent log in was successful or if this is the first attempt, then make sure the images are shuffled
	 */
	private void shuffleDecoyImages() {
		if (this.lastLoginSuccesful && !this.getShuffled() || this.loginAttemptNo == 1) {
			Collections.shuffle(this.challengeSet);
			this.setShuffled(true);
		}
	}

	/**
	 * Store a selected image's filepath to the list of entered passwords
	 * @param source
	 */
	public void addImage(String filePath) {
		this.enteredPasswords.add(filePath);
	}

	/**
	 * Check whether the list of entered passwords is the same as the actual user's passwords
	 * @return
	 */
	public boolean correctPassword() {
		return this.enteredPasswords.equals(this.passwordPaths);
	}

	/**
	 * Add a user's login attempt by passing the data to the DBConnect class which will execute the appropriate SQL update query
	 * If this login attempt wasn't successful, also pass data for the images to be stored in it's current order via the DB Connect class.
	 */
	public void addLoginAttempt() {
		addSuccessOfPasswords();
		db.addLoginAttemptToDatabase(this.getUserID(), this.getLoginMethod(), this.pictureSetSelection, this.enteredPasswords, this.timeTaken, this.totalTimeTaken, this.successOfPasswords, this.correctPassword(), this.loginAttemptNo);
		if (!this.correctPassword()) {
			db.updateUserFilePaths(this.UserID, this.loginMethod, this.pictureSetSelection, this.challengeSet);
		}
	}

	/**
	 * Add time taken for an image to be clicked to the indexed list
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
		setInitialTime();
	}

	/**
	 * Get the recent login attempt number from the database and increment it and set it to the field variable, so that when this is stored
	 * in the database, it is up to date. If there is prior login attempts, set this login attempt number variable to 1.
	 */
	public void setLoginAttemptNoFromDB() {
		this.loginAttemptNo = db.getRecentLoginAttemptNo(UserID, loginMethod, pictureSetSelection);
		this.loginAttemptNo = (this.loginAttemptNo == 0) ? 1 : ++this.loginAttemptNo;
	}

	/**
	 * Get the integer associated with the recent login success rate
	 * @return 1 = successful or 0 = unsuccessful
	 */
	public int getMostRecentLoginSuccess() {
		int getLoginSuccessful = db.getRecentLoginSuccess(this.UserID, this.loginMethod, this.pictureSetSelection);
		return getLoginSuccessful;
	}

	/**
	 * Convert the recent login success rate to a boolean. If the number is greater than 0, then they were successful.
	 */
	public void returnMostRecentLoginSuccess() {
		int successful = getMostRecentLoginSuccess();
		this.lastLoginSuccesful = (successful > 0) ? true : false;
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
	 * Checks whether the userid, picture set choice and login method choice is already associated with a registered user - the dbconnect object will check the database.
	 * @return true if already registered
	 */
	public boolean returnIsRegisteredUser() {
		boolean isRegisteredUser = db.returnIsRegistered(this.UserID, this.pictureSetSelection, this.loginMethod);
		return isRegisteredUser;
	}


	/**
	 * A reset method to clear all locally stored data and ensure there is no carry over with any future logins.
	 */
	public void clear() {
		challengeSet.clear();
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
}
