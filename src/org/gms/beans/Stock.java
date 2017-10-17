/**
 * 
 */
package org.gms.beans;

/**
 * @author issbr
 *
 */
public class Stock {
	private String idProduct;
	private Integer qnt;
	/**
	 * @return the idProduct
	 */
	public String getIdProduct() {
		return idProduct;
	}
	/**
	 * @param idProduct the idProduct to set
	 */
	public void setIdProduct(String idProduct) {
		this.idProduct = idProduct;
	}
	/**
	 * @return the qnt
	 */
	public Integer getQnt() {
		return qnt;
	}
	/**
	 * @param qnt the qnt to set
	 */
	public void setQnt(Integer qnt) {
		this.qnt = qnt;
	}
	/**
	 * @param idProduct
	 * @param qnt
	 */
	public Stock(String idProduct, Integer qnt) {
		super();
		this.idProduct = idProduct;
		this.qnt = qnt;
	}
	/**
	 * 
	 */
	public Stock() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
