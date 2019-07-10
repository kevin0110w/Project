import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class UserRegistrationModel {
	private String firstSelectedImageFilePath, secondSelectedImageFilePath, thirdSelectedImageFilePath;
	private FileNames2 fnt2;
//	private int userID, loginMethod, pictureSet;
	private int userID, pictureSet;
	private LocalDateTime initialTime;
	private List<Double> timeTaken;
	private User currentUser;
	private static final int TOTALNUMBEROFDECOYIMAGES = 17;
//	private static final int LOGINMETHODONE = 1;
//	private static final int LOGINMETHODTWO = 2;
	
	/**
	 * @return the pictureSet
	 */
	public int getPictureSet() {
		return pictureSet;
	}


	public UserRegistrationModel() {
		fnt2 = new FileNames2();
		this.timeTaken = new ArrayList<Double>();
	}
	

	public UserRegistrationModel(FileNames2 fnt2) {
		this.fnt2 = fnt2;
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

//	/**
//	 * @return the fnt
//	 */
//	public FileNames getFnt() {
//		return fnt;
//	}
//
//	/**
//	 * @param fnt the fnt to set
//	 */
//	public void setFnt(FileNames fnt) {
//		this.fnt = fnt;
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
	
	public void addUser() {
		DBConnect db = new DBConnect();
//		currentUser = new User(this.getUserID(), this.getLoginMethod(), this.getFirstSelectedImageFilePath(), this.getSecondSelectedImageFilePath(), this.getThirdSelectedImageFilePath(), this.pictureSet);
		currentUser = new User(this.getUserID(), this.getFirstSelectedImageFilePath(), this.getSecondSelectedImageFilePath(), this.getThirdSelectedImageFilePath(), this.pictureSet);
		this.createDecoyImageSet();
		currentUser.setTimeTaken(timeTaken);
		db.addUserToDatabase(this.currentUser);
//		db.addRegistrationTime(this.currentUser, this.timeTaken);
		db.addUserSeenDecoyImagesToDatabase(this.currentUser);
		db.addUserUnseenDecoyImagesToDatabase(this.currentUser);
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
	 * Finding the difference between clicks
	 */
	public void addTimeTaken() {
		LocalDateTime intermediateTime = LocalDateTime.now();
		Duration between = Duration.between(initialTime, intermediateTime);
		double timeMilliseconds = between.toMillis();
		double seconds = timeMilliseconds/1000;
		this.timeTaken.add(seconds);
		setInitialTime();
	}

	public void setPictureSet(int picsSelection) {
		this.pictureSet = picsSelection;
	}
	
	public void createRegistrationSet() {
		this.fnt2.createRegistrationSet(this.pictureSet);
	}
	/*
	public static void main(String[] args) {
		UserRegistrationModel model = new UserRegistrationModel();
//		model.setUserID(1);
//		model.setLoginMethod(1);
//		model.setFirstSelectedImageFilePath("HELLO");
//		model.setSecondSelectedImageFilePath("GOODBYE");
//		model.setThirdSelectedImageFilePath("BOOM");
//		model.addUser();
		for (Image i : model.getFnt().getListOne()) {
			System.out.println(i.getImagePath());
		}
	}
	*/


	public void clear() {
		this.firstSelectedImageFilePath = null;
		this.secondSelectedImageFilePath = null;
		this.thirdSelectedImageFilePath = null;
		this.fnt2 = new FileNames2();
	}
}
