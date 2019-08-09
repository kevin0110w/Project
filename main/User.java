package main;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class is responsible for storing all the data that is associated with a user during the registration phase.
 * @author woohoo
 *
 */
public class User {
	private String userid, passwordOne, passwordTwo, passwordThree;
	private int loginMethod, pictureSet;
	private double overallTimeTaken;
	private Set<String> images;
	private List<Double> timeTaken;
	
	public User(String userid, String passwordone, String passwordtwo, String passwordthree, int pictureSet, int loginMethod, double overallTimeTaken) {
		this.userid = userid;
		this.passwordOne = passwordone;
		this.passwordTwo = passwordtwo;
		this.passwordThree = passwordthree;
		this.pictureSet = pictureSet;
		this.images = new HashSet<String>();
		this.timeTaken = new ArrayList<Double>();
		this.loginMethod = loginMethod;
		addPasswordsToImageSet();
		this.overallTimeTaken = overallTimeTaken;
	}

	/**
	 * @return the overallTimeTaken
	 */
	public double getOverallTimeTaken() {
		return overallTimeTaken;
	}

	/**
	 * @param overallTimeTaken the overallTimeTaken to set
	 */
	public void setOverallTimeTaken(double overallTimeTaken) {
		this.overallTimeTaken = overallTimeTaken;
	}

	/**
	 * Set the picture set chosen
	 * @param pictureSet
	 */
	public void setPictureSet(int pictureSet) {
		this.pictureSet = pictureSet;
	}

	/**
	 * Get the picture set number
	 * @return
	 */
	public int getPictureSet() {
		return this.pictureSet;
	}

	/**
	 * Get the list of times taken
	 * @return
	 */
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
	public String getUserid() {
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * @return the loginattempt
	 */
	public int getLoginMethod() {
		return this.loginMethod;
	}

	/**
	 * @param loginattempt - the loginattempt selected during registration to set
	 */
	public void setLoginMethod(int loginMethod) {
		this.loginMethod = loginMethod;
	}

	/**
	 * Add any image to the user's image set
	 * @param imageFile
	 */
	public void addImageToImageSet(String imageFile) {
		this.images.add(imageFile);
	}
	

	/**
	 * Add the 3 password images to a user's set of image that'll be stored in the 
	 * database for holding challenge sets.
	 */
	private void addPasswordsToImageSet() {
		addImageToImageSet(this.passwordOne);
		addImageToImageSet(this.passwordTwo);
		addImageToImageSet(this.passwordThree);
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
	
	/**
	 * A  method to compare that two registered users are equal
	 * for testing purposes.
	 */
	@Override
	public boolean equals(Object user) {
		User newUser = (User) user; 
		return newUser.getUserid().equals(this.userid) && newUser.getPictureSet() == this.getPictureSet() && newUser.getLoginMethod() == this.loginMethod;
	}
}