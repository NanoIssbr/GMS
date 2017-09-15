
package org.gms.beans;
import java.util.Date;

/**
 * @author hero
 *
 */
public class Users {
	private String id;
	private Date dateLastCnx;
	private String userName;
	//public List<ErrorObject> erreurLists = new ArrayList<>();
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the dateLastCnx
	 */
	public Date getDateLastCnx() {
		return dateLastCnx;
	}

	/**
	 * @param dateLastCnx the dateLastCnx to set
	 */
	public void setDateLastCnx(Date dateLastCnx) {
		this.dateLastCnx = dateLastCnx;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @param id
	 * @param dateInsc
	 * @param m
	 * @param lastName
	 * @param firstName
	 * @param mail
	 * @param title
	 * @param department
	 * @param band
	 * @param password
	 */
	public Users(String id,Date dateLastCnx, String userName) {
		super();
		this.id = id;
		this.dateLastCnx = dateLastCnx;
		this.userName = userName;
	}

	public Users() {

	}
}
