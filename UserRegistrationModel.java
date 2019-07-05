import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class UserRegistrationModel {
	private String firstSelectedImageFilePath, secondSelectedImageFilePath, thirdSelectedImageFilePath;
//	private FileNames fnt;
	private FileNames2 fnt2;
	private int userID, loginMethod;
	private User user;
	private LocalDateTime initialTime;
	private List<Float> timeTaken;
	
	public UserRegistrationModel() {
//		fnt = new FileNames();
		fnt2 = new FileNames2();
		this.timeTaken = new ArrayList<Float>();
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
	public int getLoginMethod() {
		return loginMethod;
	}

	/**
	 * @param loginMethod the loginMethod to set
	 */
	public void setLoginMethod(int loginMethod) {
		this.loginMethod = loginMethod;
	}

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
		this.user = new User(this.getUserID(), this.getLoginMethod(), this.getFirstSelectedImageFilePath(), this.getSecondSelectedImageFilePath(), this.getThirdSelectedImageFilePath());
		this.createDecoyImageSet();
		db.addUserToDatabase(this.user);
		db.addRegistrationTime(this.user, this.timeTaken);
	}
	
	public void createDecoyImageSet() {
		Random random = new Random();
		int n = 0;
//		Set<Integer> pickedNumbers = new HashSet<Integer>();
		while (this.user.decoySize() < 17) {
			n = random.nextInt(fnt2.getUnseenImages().size());
//			if (pickedNumbers.add(n)) {
				if (this.getLoginMethod() == 1) {
					if (n > fnt2.getSeenImages().size()) {
						n = fnt2.getUnseenImages().size() - fnt2.getSeenImages().size();
					}
//				if (user.addImageToDecoySet(fnt.getAllImages().get(n).getImagePath())) {
//					decoys.add(fnt.getAllImages().get(n).getImagePath());
//				}
//				this.user.addImageToDecoySet(this.fnt.getAllImages().get(n).getImagePath());
					this.user.addImageToDecoySet(this.fnt2.getSeenImages().get(n));
				} else if (this.getLoginMethod() == 2) {
					n = random.nextInt(85);
//				this.user.addImageToDecoySet(this.fnt.getHidden().get(n).getImagePath());
					this.user.addImageToDecoySet(this.fnt2.getUnseenImages().get(n));
//				decoys.add(fnt.getHidden().get(n).getImagePath());
				}
//			}
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
		float timeMilliseconds = between.toMillis();
		this.timeTaken.add(timeMilliseconds);
		setInitialTime();
	}


	public void setPictureSet(int picsSelection) {
		this.fnt2.createRegistrationSet(picsSelection);
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
}
