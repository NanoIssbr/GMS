/**
 * 
 */
package org.gms.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.gms.beans.Product;

/**
 * @author hero
 *
 */
public interface ProductDAO {
	/**
	 * @param id
	 * @param isID
	 * @return
	 */
	public Product findProduct(String id, boolean isID, HttpServletRequest req);
	
	/**
	 * @param id
	 * @param isID
	 * @param req
	 * @return
	 */
	public List<Product> findProducts(String id, boolean isID, HttpServletRequest req);
	/**
	 * @param libelle
	 * @param id
	 * @return
	 */
	public Product findProduct(String libelle, String id, HttpServletRequest req);
	/**
	 * @param produit
	 */
	public void addProduct(Product produit, HttpServletRequest req);
	/**
	 * @param produit
	 */
	public void updateProduct(Product produit,  HttpServletRequest req);
	
	/**
	 * @param req
	 * @return
	 */
	public List<Product> findProductByQuantite(HttpServletRequest req);
	
	/**
	 * @param req
	 * @return
	 */
	public List<Product> getAllProduct(HttpServletRequest req, Boolean withQnt);
	
}
