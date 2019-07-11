import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class is responsible for storing all the data recorded during the registration phase and then passing all the data to the dbconnect class to be recorded
 * on the database
 *
 */
public class UserRegistrationModel {
	private String firstSelectedImageFilePath, secondSelectedImageFilePath, thirdSelectedImageFilePath;
	private FileNames2 fnt2;
//	private int userID, loginMethod, pictureSet;
	private int userID, pictureSet;
	private LocalDateTime initialTime;
	private List<Double> timeTaken;
	private User currentUser;
	private static final int TOTALNUMBEROFDECOYIMAGES = 17;
	private DBConnect db;
//	private static final int LOGINMETHODONE = 1;
//	private static final int LOGINMETHODTWO = 2;
	
	
	/**
	 * Create a new DBConnect object for database modifying purposes, FileNames2 object for generating string for images and decoys
	 * this.timeTaken is an indexed list to store the time taken to make an image selection
	 */
	public UserRegistrationModel() {
		this.db = new DBConnect();
		this.fnt2 = new FileNames2();
		this.timeTaken = new ArrayList<Double>();
	}
	
	/**
	 * @return the pictureSet
	 */
	public int getPictureSet() {
		return pictureSet;
	}

	/*
	 * Method to set the pictureset field variable
	 * @Param selection from the instruction panel drop down box
	 */
	public void setPictureSet(int picsSelection) {
		this.pictureSet = picsSelection;
	}
	
	/**
	 * @return the userID
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	/**
	 * @return the loginMethod
	 */
//	public int getLoginMethod() {
//		return loginMethod;
//	}

	/**
	 * @param loginMethod the loginMethod to set
	 */
//	public void setLoginMethod(int loginMethod) {
//		this.loginMethod = loginMethod;
//	}

	/**
	 * @return the fnt
	 */
	public FileNames2 getFnt() {
		return fnt2;
	}

	/**
	 * @param fnt the fnt to set
	 */
	public void setFnt(FileNames2 fnt) {
		this.fnt2 = fnt;
	}
	
	/**
	 * @return the firstSelectedImageFilePath
	 */
	public String getFirstSelectedImageFilePath() {
		return firstSelectedImageFilePath;
	}

	/**
	 * @param firstSelectedImageFilePath the firstSelectedImageFilePath to set
	 */
	public void setFirstSelectedImageFilePath(String firstSelectedImageFilePath) {
		this.firstSelectedImageFilePath = firstSelectedImageFilePath;
	}

	/**
	 * @return the secondSelectedImageFilePath
	 */
	public String getSecondSelectedImageFilePath() {
		return secondSelectedImageFilePath;
	}

	/**
	 * @param secondSelectedImageFilePath the secondSelectedImageFilePath to set
	 */
	public void setSecondSelectedImageFilePath(String secondSelectedImageFilePath) {
		this.secondSelectedImageFilePath = secondSelectedImageFilePath;
	}

	/**
	 * @return the thirdSelectedImageFilePath
	 */
	public String getThirdSelectedImageFilePath() {
		return thirdSelectedImageFilePath;
	}

	/**
	 * @param thirdSelectedImageFilePath the thirdSelectedImageFilePath to set
	 */
	public void setThirdSelectedImageFilePath(String thirdSelectedImageFilePath) {
		this.thirdSelectedImageFilePath = thirdSelectedImageFilePath;
	}
	
	/**
	 * Clear all local stored data associated with this registration for another user to be able to register, once the data has been stored on the 
	 * database
	 */
	public void addUser() {
//		currentUser = new User(this.getUserID(), this.getLoginMethod(), this.getFirstSelectedImageFilePath(), this.getSecondSelectedImageFilePath(), this.getThirdSelectedImageFilePath(), this.pictureSet);
		this.currentUser = new User(this.getUserID(), this.getFirstSelectedImageFilePath(), this.getSecondSelectedImageFilePath(), this.getThirdSelectedImageFilePath(), this.pictureSet);
		this.createDecoyImageSet();
		this.currentUser.setTimeTaken(timeTaken);
		this.db.addUserToDatabase(this.currentUser);
//		db.addRegistrationTime(this.currentUser, this.timeTaken);
//		db.addUserSeenDecoyImagesToDatabase(this.currentUser);
//		db.addUserUnseenDecoyImagesToDatabase(this.currentUser);
		this.clear(); // clear all locally stored data from a recent registration
	}
	
//	private void createDecoyImageSet() {
//		Random random = new Random();
//		int n = 0;
//		while (this.currentUser.getDecoySetSize() < TOTALNUMBEROFDECOYIMAGES) {
//				if (this.getLoginMethod() == LOGINMETHODONE) {
//					n = random.nextInt(fnt2.getSeenImages().size());
//					this.currentUser.addImageToDecoySet(this.fnt2.getSeenImages().get(n));
//				} else if (this.getLoginMethod() == LOGINMETHODTWO) {
//					n = random.nextInt(fnt2.getUnseenImages().size());
//					this.currentUser.addImageToDecoySet(this.fnt2.getUnseenImages().get(n));
//				}
//		}
////		p.setHiddenImages(decoys);
//	}
	
	/**
	 * Creates the decoy image sets for a user
	 * The program will create both versions at the same time.
	 * Function will keep looping until both sets have the same 17 images.
	 * Images chosen randomly. Image strings are stored in a set in the User class to ensure there is no duplication
	 */
	private void createDecoyImageSet() {
		Random random = new Random();
		int n = 0, m = 0;
		while (this.currentUser.getSeenDecoys().size() < TOTALNUMBEROFDECOYIMAGES || this.currentUser.getUnseenDecoys().size() < TOTALNUMBEROFDECOYIMAGES  ) {
			if (this.currentUser.getSeenDecoys().size() < TOTALNUMBEROFDECOYIMAGES) {
				n = random.nextInt(fnt2.getSeenImages().size());
				this.currentUser.addImageToSeenDecoySet(this.fnt2.getSeenImages().get(n));
			} 
			
			if (this.currentUser.getUnseenDecoys().size() < TOTALNUMBEROFDECOYIMAGES) {
				m = random.nextInt(fnt2.getUnseenImages().size());
				this.currentUser.addImageToUnseenDecoySet(this.fnt2.getUnseenImages().get(m));
			}
		}
//		p.setHiddenImages(decoys);
	}
	
	/*
	 * Set the initial time to start when the user first sees the images
	 */
	public void setInitialTime() {
		this.initialTime = LocalDateTime.now();
	}
	
	/*
	 * Find the difference between clicks and add it to the arraylist of timeTaken 
	 * Resets the localdatetime object
	 */
	public void addTimeTaken() {
		LocalDateTime intermediateTime = LocalDateTime.now();
		Duration between = Duration.between(initialTime, intermediateTime);
		double timeMilliseconds = between.toMillis();
		double seconds = timeMilliseconds/1000;
		this.timeTaken.add(seconds);
		setInitialTime();
	}
	
	/*
	 * Creates the registration set of 60 images that'll be viewed by the user in the registration panels
	 */
	public void createRegistrationSet() {
		this.fnt2.createRegistrationSet(this.pictureSet);
	}
	
	/*
	 * Clears any local data associated with any user who's clicked back after not fully completing registered
	 */
	public void clear() {
		this.firstSelectedImageFilePath = null;
		this.secondSelectedImageFilePath = null;
		this.thirdSelectedImageFilePath = null;
		this.timeTaken.clear();
		this.fnt2 = new FileNames2();
	}

	/*
	 * Returns whether a user associated a specific userid and pictureset has already been registered
	 */
	public boolean isUserAlreadyRegistered() {
		return db.returnIsRegistered(userID, pictureSet);
	}
}
