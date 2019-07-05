import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class User {
	private String passwordOne, passwordTwo, passwordThree;
	private int userid, loginattempt;
	 Set<String> decoys;
	
	public User(int userid, int loginattempt, String passwordone, String passwordtwo, String passwordthree) {
		this.userid = userid;
		this.passwordOne = passwordone;
		this.passwordTwo = passwordtwo;
		this.passwordThree = passwordthree;
		this.loginattempt = loginattempt;
		this.decoys = new HashSet<String>();
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
		passwordOne = passwordOne;
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
		passwordTwo = passwordTwo;
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
		passwordThree = passwordThree;
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
	public int getLoginattempt() {
		return loginattempt;
	}

	/**
	 * @param loginattempt the loginattempt to set
	 */
	public void setLoginattempt(int loginattempt) {
		this.loginattempt = loginattempt;
	}

	@Override
	public String toString() {
		return "Welcome User: " + this.getUserid();
	}
	
	public void addImageToDecoySet(String filePath) {
//		boolean success = false;
		if (!(filePath.equals(passwordOne) || filePath.equals(passwordTwo) || filePath.equals(passwordThree))) {
//			success = getSet().add(filePath);
			this.getSet().add(filePath);
//			System.out.println(filePath);
		}
//		return success;
	}

	private Set<String> getSet() {
		return this.decoys;
	}

	public int decoySize() {
		// TODO Auto-generated method stub
		return this.decoys.size();
	}

	public void printPaths() {
		Iterator<String> it = this.decoys.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

}
