/**
 * 
 */
package org.gms.dao;
import org.gms.beans.Users;
/**
 * @author hero
 *
 */
public interface UserDAO {

	/**
	 * @param mail
	 * @param pwd
	 * @return {@link org.itas.beans.Users}
	 * @throws DAOException
	 */
	public Users find(String mail , String pwd) throws DAOException;
	/**
	 * @param mail
	 * @param pwd
	 * @throws DAOException
	 */
	public void update (String mail, String pwd) throws DAOException;
	
}
