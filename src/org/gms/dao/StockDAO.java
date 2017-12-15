/**
 * 
 */
package org.gms.dao;

import javax.servlet.http.HttpServletRequest;

/**
 * @author issbr
 *
 */
public interface StockDAO {
	/**
	 * add element to stock by product
	 * @param prd produit to add
	 * @param stock information stock
	 * @param req
	 */
	public void addToStock(Object prd, HttpServletRequest req);
	/**
	 * remove element from stock by product
	 * @param prd produit to add
	 * @param stock information stock
	 * @param req
	 */
	public void getOutFromStock(Object prd, HttpServletRequest req);
}
