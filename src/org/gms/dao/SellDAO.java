/**
 * 
 */
package org.gms.dao;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.gms.beans.Sell;

/**
 * @author issbr
 *
 */
public interface SellDAO {
	
	/**
	 * Add sell operation
	 * @param sel
	 * @param req
	 */
	public void addSellOperation(Sell sel, HttpServletRequest req); 
	
	/**
	 * Get history of All Sells operations
	 * @return
	 */
	public List<Sell> getHistOfSellOperations(Calendar dateFrom , Calendar dateTo);
}
