import java.util.Random;

public class UserRegistrationModel {
	private String firstSelectedImageFilePath, secondSelectedImageFilePath, thirdSelectedImageFilePath;
	private FileNames fnt;
	private int UserID, loginMethod;
	private User user;
	
	public UserRegistrationModel() {
		fnt = new FileNames();
	}
	

	public UserRegistrationModel(FileNames fnt2) {
		this.fnt = fnt2;
	}


	/**
	 * @return the userID
	 */
	public int getUserID() {
		return UserID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(int userID) {
		UserID = userID;
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

	/**
	 * @return the fnt
	 */
	public FileNames getFnt() {
		return fnt;
	}

	/**
	 * @param fnt the fnt to set
	 */
	public void setFnt(FileNames fnt) {
		this.fnt = fnt;
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
		System.out.println(getFirstSelectedImageFilePath());
		System.out.println(getSecondSelectedImageFilePath());
		System.out.println(getThirdSelectedImageFilePath());
	}
	
	public void addUser() {
		DBConnect db = new DBConnect();
		this.user = new User(this.getUserID(), this.getLoginMethod(), this.getFirstSelectedImageFilePath(), this.getSecondSelectedImageFilePath(), this.getThirdSelectedImageFilePath());
		this.createDecoyImageSet();
		db.addUserToDatabase(this.user);
	}
	
	public void createDecoyImageSet() {
		Random random = new Random();
		int n = 0;
		while (this.user.decoySize() < 17) {
			if (this.getLoginMethod() == 1) {
				n = random.nextInt(60);
//				if (user.addImageToDecoySet(fnt.getAllImages().get(n).getImagePath())) {
//					decoys.add(fnt.getAllImages().get(n).getImagePath());
//				}
				this.user.addImageToDecoySet(this.fnt.getAllImages().get(n).getImagePath());
			} else if (this.getLoginMethod() == 2) {
				n = random.nextInt(85);
				user.addImageToDecoySet(this.fnt.getHidden().get(n).getImagePath());
//				decoys.add(fnt.getHidden().get(n).getImagePath());
			}

		}
		user.printPaths();
//		p.setHiddenImages(decoys);
	}
	
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
}
