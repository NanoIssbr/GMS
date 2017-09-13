/**
 * 
 */
package org.gms.dao;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.gms.beans.Abonnement;

/**
 * @author hero
 *
 */
public interface AbonnementDAO {
	/**
	 * @param req
	 * @return
	 * @throws ParseException 
	 */
	public List<Abonnement> getAbonnements(HttpServletRequest req) throws ParseException;
}
