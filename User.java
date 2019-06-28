
public class User {
	private String PasswordOne, PasswordTwo, PasswordThree;
	private int userid, loginattempt;
	
	public User(int userid, int loginattempt, String passwordone, String passwordtwo, String passwordthree) {
		this.userid = userid;
		this.PasswordOne = passwordone;
		this.PasswordTwo = passwordtwo;
		this.PasswordThree = passwordthree;
		this.loginattempt = 1;
	}

	/**
	 * @return the passwordOne
	 */
	public String getPasswordOne() {
		return PasswordOne;
	}

	/**
	 * @param passwordOne the passwordOne to set
	 */
	public void setPasswordOne(String passwordOne) {
		PasswordOne = passwordOne;
	}

	/**
	 * @return the passwordTwo
	 */
	public String getPasswordTwo() {
		return PasswordTwo;
	}

	/**
	 * @param passwordTwo the passwordTwo to set
	 */
	public void setPasswordTwo(String passwordTwo) {
		PasswordTwo = passwordTwo;
	}

	/**
	 * @return the passwordThree
	 */
	public String getPasswordThree() {
		return PasswordThree;
	}

	/**
	 * @param passwordThree the passwordThree to set
	 */
	public void setPasswordThree(String passwordThree) {
		PasswordThree = passwordThree;
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
}
