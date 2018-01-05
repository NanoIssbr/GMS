/**
 * 
 */
package org.gms.dao;

import javax.servlet.http.HttpServletRequest;

import org.gms.beans.Product;

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
	 * @param prd produit to Sell
	 * @param stock information stock
	 * @param qnt numbre of piece to Sell
	 * @param req
	 */
	public void sellFromStock(Product prd, Integer qnt,  HttpServletRequest req);
}
