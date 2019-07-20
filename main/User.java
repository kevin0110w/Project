package main;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {
	private String passwordOne, passwordTwo, passwordThree;
	private int userid, loginMethod, pictureSet;
	private Set<String> decoys, seenDecoys, unseenDecoys, images;
	private List<Double> timeTaken;
	
//	public User(int userid, int loginattempt, String passwordone, String passwordtwo, String passwordthree, int pictureSet) {
	public User(int userid, String passwordone, String passwordtwo, String passwordthree, int pictureSet, int loginMethod) {
		this.userid = userid;
		this.passwordOne = passwordone;
		this.passwordTwo = passwordtwo;
		this.passwordThree = passwordthree;
		this.pictureSet = pictureSet;
//		this.decoys = new HashSet<String>();
//		this.seenDecoys = new HashSet<String>();
//		this.unseenDecoys = new HashSet<String>();
		this.images = new HashSet<String>();
		this.timeTaken = new ArrayList<Double>();
		this.loginMethod = loginMethod;
		addPasswordsToImageSet();
	}

	public void setPictureSet(int pictureSet) {
		this.pictureSet = pictureSet;
	}

	public int getPictureSet() {
		return this.pictureSet;
	}

	public List<Double> getTimeTaken() {
		return this.timeTaken;
	}

	/**
	 * @param timeTaken2 the timeTaken to set
	 */
	public void setTimeTaken(List<Double> timeTaken2) {
		this.timeTaken = timeTaken2;
	}

	/**
	 * @return the passwordOne
	 */
	public String getPasswordOne() {
		return passwordOne;
	}

	/**
	 * @param passwordOne the passwordOne to set
	 */
	public void setPasswordOne(String passwordOne) {
		this.passwordOne = passwordOne;
	}

	/**
	 * @return the passwordTwo
	 */
	public String getPasswordTwo() {
		return passwordTwo;
	}

	/**
	 * @param passwordTwo the passwordTwo to set
	 */
	public void setPasswordTwo(String passwordTwo) {
		this.passwordTwo = passwordTwo;
	}

	/**
	 * @return the passwordThree
	 */
	public String getPasswordThree() {
		return passwordThree;
	}

	/**
	 * @param passwordThree the passwordThree to set
	 */
	public void setPasswordThree(String passwordThree) {
		this.passwordThree = passwordThree;
	}

	/**
	 * @return the userid
	 */
	public int getUserid() {
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(int userid) {
		this.userid = userid;
	}

	/**
	 * @return the loginattempt
	 */
	public int getLoginMethod() {
		return this.loginMethod;
	}

	/**
	 * @param loginattempt the loginattempt to set
	 */
	public void setLoginMethod(int loginMethod) {
		this.loginMethod = loginMethod;
	}

	public void addImageToImageSet(String imageFile) {
		this.images.add(imageFile);
	}
	

	private void addPasswordsToImageSet() {
		addImageToImageSet(this.passwordOne);
		addImageToImageSet(this.passwordTwo);
		addImageToImageSet(this.passwordThree);
	}

	public int getImageSetSize() {
		return this.images.size();
	}

	/**
	 * @return the images
	 */
	public Set<String> getImages() {
		return images;
	}

	/**
	 * @param images the images to set
	 */
	public void setImages(Set<String> images) {
		this.images = images;
	}

//	public void addImageToDecoySet(String filePath) {
//	if (!(filePath.equals(passwordOne) || filePath.equals(passwordTwo) || filePath.equals(passwordThree))) {
//		this.getDecoySet().add(filePath);
//	}
//}
//
//public void addImageToUnseenDecoySet(String filePath) {
//	if (!(filePath.equals(passwordOne) || filePath.equals(passwordTwo) || filePath.equals(passwordThree))) {
//		this.getUnseenDecoys().add(filePath);
//	}
//}
//
//public void addImageToSeenDecoySet(String filePath) {
//	if (!(filePath.equals(passwordOne) || filePath.equals(passwordTwo) || filePath.equals(passwordThree))) {
//		this.getSeenDecoys().add(filePath);
//	}
//}
//
///**
// * @return the seenDecoys
// */
//public Set<String> getSeenDecoys() {
//	return seenDecoys;
//}
//
///**
// * @param seenDecoys the seenDecoys to set
// */
//public void setSeenDecoys(Set<String> seenDecoys) {
//	this.seenDecoys = seenDecoys;
//}
//
///**
// * @return the unseenDecoys
// */
//public Set<String> getUnseenDecoys() {
//	return unseenDecoys;
//}
//
///**
// * @param unseenDecoys the unseenDecoys to set
// */
//public void setUnseenDecoys(Set<String> unseenDecoys) {
//	this.unseenDecoys = unseenDecoys;
//}
//
//
//public Set<String> getDecoySet() {
//	return this.decoys;
//}
	

}
