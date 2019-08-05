package main;
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
	private String userID, firstSelectedImageFilePath, secondSelectedImageFilePath, thirdSelectedImageFilePath;
	private ImageFiles imageFiles;
//	private int userID, pictureSet, loginMethod;
	private int pictureSet, loginMethod, alternativeLogin;
	private double overallTimeTaken;
	private LocalDateTime initialTime;
	private List<Double> timeTaken;
	private User currentUser;
	private static final int TOTALNUMBEROFDECOYIMAGES = 20;
	private DBConnect db;
	private static final int LOGINMETHODONE = 1;
	private static final int LOGINMETHODTWO = 2;
	private int counter = 1;
	private List<String> alternativeRegistrationSeenImages; // an arraylist to store the images that a user may have seen from a previous registration
	/**
	 * Create a new DBConnect object for database modifying purposes, FileNames2 object for generating string for images and decoys
	 * this.timeTaken is an indexed list to store the time taken to make an image selection
	 */
	public UserRegistrationModel() {
		this.db = new DBConnect();
		this.imageFiles = new ImageFiles();
		this.timeTaken = new ArrayList<Double>();
		this.alternativeRegistrationSeenImages = new ArrayList<String>();
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
	 * @return the fnt
	 */
	public ImageFiles getImageFiles() {
		return imageFiles;
	}

	/**
	 * @param fnt the fnt to set
	 */
	public void setImageFiles(ImageFiles fnt) {
		this.imageFiles = fnt;
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
		this.currentUser = new User(this.getUserID(), this.getFirstSelectedImageFilePath(), this.getSecondSelectedImageFilePath(), this.getThirdSelectedImageFilePath(), this.pictureSet, this.loginMethod, this.overallTimeTaken);
		this.createDecoyImageSet();
		this.currentUser.setTimeTaken(timeTaken);
		this.db.addUserToDatabase(this.currentUser);
		this.addUserSeenImagestoDB();

//		db.addRegistrationTime(this.currentUser, this.timeTaken);
//		db.addUserSeenDecoyImagesToDatabase(this.currentUser);
//		db.addUserUnseenDecoyImagesToDatabase(this.currentUser);
// Check if there are duplicates.
		printCheckUp();
		this.clear(); // clear all locally stored data from a recent registration
	}
	
	private void printCheckUp() {
		List<String> images = new ArrayList<String>();
		images.addAll(this.currentUser.getImages());
		this.currentUser.getPrinter().setDecoyImages(images);
		this.currentUser.getPrinter().setSeenImages(this.imageFiles.getSeenImages());
		this.currentUser.getPrinter().setCounter(++counter);
		this.currentUser.getPrinter().printToFile();
	}

	private void setAlternativeLoginImages() {
		this.alternativeRegistrationSeenImages.addAll(this.db.getUserSeenImages(this.userID, this.pictureSet, this.alternativeLogin));
	}

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

	private void addUserSeenImagestoDB() {
		List<String> images = new ArrayList<String>();
		images.addAll(this.imageFiles.getListOne());
		images.addAll(this.imageFiles.getListTwo());
		images.addAll(this.imageFiles.getListThree());
		db.addUsersSeenImagestoDatabase(this.userID, this.pictureSet, this.loginMethod, images);
	}

	public void createDecoyImageSet() {
		Random random = new Random();
		int n = 0;
		while (this.currentUser.getImageSetSize() < TOTALNUMBEROFDECOYIMAGES) {
				if (this.getLoginMethod() == LOGINMETHODONE) {
					n = random.nextInt(this.imageFiles.getSeenImages().size());
					this.currentUser.addImageToImageSet(this.imageFiles.getSeenImages().get(n));
				} else if (this.getLoginMethod() == LOGINMETHODTWO) {
					n = random.nextInt(this.imageFiles.getUnseenImages().size());
					String image = this.imageFiles.getUnseenImages().get(n);
					if (!(alternativeRegistrationSeenImages.contains(image))) {
					this.currentUser.addImageToImageSet(image);
					}
				}
		}
//		p.setHiddenImages(decoys);
		
	}
	
	/**
	 * Creates the decoy image sets for a user
	 * The program will create both versions at the same time.
	 * Function will keep looping until both sets have the same 17 images.
	 * Images chosen randomly. Image strings are stored in a set in the User class to ensure there is no duplication
	 */
//	private void createDecoyImageSet() {
//		Random random = new Random();
//		int n = 0, m = 0;
//		while (this.currentUser.getSeenDecoys().size() < TOTALNUMBEROFDECOYIMAGES || this.currentUser.getUnseenDecoys().size() < TOTALNUMBEROFDECOYIMAGES  ) {
//			if (this.currentUser.getSeenDecoys().size() < TOTALNUMBEROFDECOYIMAGES) {
//				n = random.nextInt(imageFiles.getSeenImages().size());
//				this.currentUser.addImageToSeenDecoySet(this.imageFiles.getSeenImages().get(n));
//			} 
//			
//			if (this.currentUser.getUnseenDecoys().size() < TOTALNUMBEROFDECOYIMAGES) {
//				m = random.nextInt(imageFiles.getUnseenImages().size());
//				this.currentUser.addImageToUnseenDecoySet(this.imageFiles.getUnseenImages().get(m));
//			}
//		}
//		p.setHiddenImages(decoys);
//	}
	
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
		this.overallTimeTaken += seconds;
		setInitialTime();
	}
	
	/*
	 * Creates the registration set of 60 images that'll be viewed by the user in the registration panels
	 */
	public void createRegistrationSet() {
		setAlternativeLoginImages();
		this.imageFiles.createRegistrationSet(this.pictureSet, this.alternativeRegistrationSeenImages);
	}
	
	/*
	 * Clears any local data associated with any user who's clicked back after not fully completing registered
	 */
	public void clear() {
		this.firstSelectedImageFilePath = null;
		this.secondSelectedImageFilePath = null;
		this.thirdSelectedImageFilePath = null;
		this.timeTaken.clear();
		this.imageFiles = new ImageFiles();
		this.overallTimeTaken = 0;
	}

	/*
	 * Returns whether a user associated a specific userid and pictureset has already been registered
	 */
	public boolean isUserAlreadyRegistered() {
		return db.returnIsRegistered(userID, pictureSet, loginMethod);
	}

	public User getUser() {
		return this.currentUser;
	}

	public void setUser(User user) {
		this.currentUser = user;
		
	}
}
