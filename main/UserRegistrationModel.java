package main;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class is responsible for storing all the data recorded during the registration phase, the dbconnect object will send sql commands to the database
 *
 */
public class UserRegistrationModel {
	private String userID, firstSelectedImageFilePath, secondSelectedImageFilePath, thirdSelectedImageFilePath;
	private ImageFiles imageFiles;
	private int pictureSet, loginMethod, alternativeLogin;
	private double overallTimeTaken;
	private LocalDateTime initialTime;
	private List<Double> timeTaken;
	private User currentUser;
	private static final int TOTALNUMBEROFDECOYIMAGES = 20;
	private DBConnect db;
	private static final int LOGINMETHODONE = 1;
	private static final int LOGINMETHODTWO = 2;
	private List<String> alternativeRegistrationSeenImages; // an arraylist to store the images that a user may have seen from a previous registration
	
	
	/**
	 * Create a new DBConnect object for database modifying purposes
	 * ImageFiles object for creating a registration set of 60 images and the challenge set of 20 images
	 * timeTaken is an indexed list to store the time taken to select each image
	 * alternativeRegistrationSeenImages is an indexed list to store the images that a user may have seen had they registered before for the same picture set with a different login method
	 */
	public UserRegistrationModel() {
		this.db = new DBConnect();
		this.imageFiles = new ImageFiles();
		this.timeTaken = new ArrayList<Double>();
		this.alternativeRegistrationSeenImages = new ArrayList<String>();
	}
	
	/**
	 * @return the dbconnect object
	 */
	public DBConnect getDb() {
		return db;
	}

	/**
	 * @param set the dbconnect object
	 */
	public void setDb(DBConnect db) {
		this.db = db;
	}

	/**
	 * @return the timeTaken list
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
	 * @return the pictureSet
	 */
	public int getPictureSet() {
		return pictureSet;
	}

	/**
	 * Method to set the pictureset field variable
	 * @Param selection from the instruction panel drop down box
	 */
	public void setPictureSet(int picsSelection) {
		this.pictureSet = picsSelection;
	}
	
	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * @return the loginMethod
	 */
	public int getLoginMethod() {
		return loginMethod;
	}

	/**
	 * @param loginMethod the loginMethod to set
	 */
	public void setLoginMethod(int loginMethod) {
		this.loginMethod = loginMethod;
		setAlternativeLogin();
	}

	/**
	 * @return the imageFiles
	 */
	public ImageFiles getImageFiles() {
		return imageFiles;
	}

	/**
	 * @param imageFiles the imageFiles
	 */
	public void setImageFiles(ImageFiles imageFiles) {
		this.imageFiles = imageFiles;
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
	 * First create a user object, create the user's decoy set, set the user's time taken
	 * Add the user to the database
	 * Add the user's seen images to the database
	 * Clear the data associated with a user
	 */
	public void addUser() {
		this.currentUser = new User(this.getUserID(), this.getFirstSelectedImageFilePath(), this.getSecondSelectedImageFilePath(), this.getThirdSelectedImageFilePath(), this.pictureSet, this.loginMethod, this.overallTimeTaken);
		this.createDecoyImageSet();
		this.currentUser.setTimeTaken(timeTaken);
		this.db.addUserToDatabase(this.currentUser);
		this.addUserSeenImagestoDB();
		this.clear(); // clear all locally stored data from a recent registration
	}
	
	/**
	 * Store the list of images that a user has seen during the registration phase to the
	 * database 
	 */
	private void addUserSeenImagestoDB() {
		List<String> images = new ArrayList<String>();
		images.addAll(this.imageFiles.getListOne());
		images.addAll(this.imageFiles.getListTwo());
		images.addAll(this.imageFiles.getListThree());
		db.addUsersSeenImagestoDatabase(this.userID, this.pictureSet, this.loginMethod, images);
	}
	
	/**
	 * Creates the registration set of 60 images that'll be viewed by the user in the registration panels, accounting for any previously seen images in a previous registration using a different
	 * login method
	 */
	public void createRegistrationSet() {
		this.setAlternativeLoginImages();
		this.imageFiles.createRegistrationSet(this.pictureSet, this.alternativeRegistrationSeenImages);
	}
	
	/**
	 * Check whether the user has already registered using a different login method of 	the same picture set
	 * If such a user exists, this method will set a list variable containing all previously seen images so that we can create a new, completely different list of registration images.
	 */
	private void setAlternativeLoginImages() {
		this.alternativeRegistrationSeenImages.addAll(this.db.getUserSeenImages(this.userID, this.pictureSet, this.alternativeLogin));
	}

	/**
	 * Set the alternative login method variable which will be used to check if a user might have registered previously.
	 */
	private void setAlternativeLogin() {
		switch (loginMethod) {
		case 1:
			alternativeLogin = 2;
			break;
		case 2:
			alternativeLogin = 1;
			break;
		}
	}


	/**
	 * Create a decoy image set for a registered user
	 * In method one, only images from the seen registration set will be chosen, at random
	 * In method two, only images that is not seen in either registration attempts for a user is chosen
	 */
	public void createDecoyImageSet() {
		Random random = new Random();
		int n = 0;
		while (this.currentUser.getImages().size() < TOTALNUMBEROFDECOYIMAGES) {
				if (this.getLoginMethod() == LOGINMETHODONE) {
					n = random.nextInt(this.imageFiles.getSeenImages().size());
					String randomSeenImage = this.imageFiles.getSeenImages().get(n);
					this.currentUser.addImageToImageSet(randomSeenImage);
				} else if (this.getLoginMethod() == LOGINMETHODTWO) {
					n = random.nextInt(this.imageFiles.getUnseenImages().size());
					String randomUnseenImage = this.imageFiles.getUnseenImages().get(n);
					this.currentUser.addImageToImageSet(randomUnseenImage);
				}
		}
	}
	
	/**
	 * Set the initial time to start when the user first sees the images
	 */
	public void setInitialTime() {
		this.initialTime = LocalDateTime.now();
	}
	
	/**
	 * Find the difference between clicks and add it to the list of times Taken 
	 * Resets the localdatetime object
	 */
	public void addTimeTaken() {
		LocalDateTime intermediateTime = LocalDateTime.now();
		Duration between = Duration.between(initialTime, intermediateTime);
		double timeMilliseconds = between.toMillis();
		double seconds = timeMilliseconds/1000;
		this.timeTaken.add(seconds);
		this.overallTimeTaken += seconds;
		setInitialTime();
	}
	

	/**
	 * Clears any local data associated with any user who's clicked back after not fully completing registered
	 */
	public void clear() {
		this.firstSelectedImageFilePath = null;
		this.secondSelectedImageFilePath = null;
		this.thirdSelectedImageFilePath = null;
		this.alternativeRegistrationSeenImages.clear();
		this.timeTaken.clear();								
		this.imageFiles = new ImageFiles();
		this.overallTimeTaken = 0;
	}

	/**
	 * Returns whether a user associated a specific userid and pictureset has already been registered
	 */
	public boolean isUserAlreadyRegistered() {
		return db.returnIsRegistered(userID, pictureSet, loginMethod);
	}
	
	/**
	 * @return a current user
	 */
	public User getUser() {
		return this.currentUser;
	}

	/** 
	 * set a user
	 * @param user
	 */
	public void setUser(User user) {
		this.currentUser = user;
		
	}
}
