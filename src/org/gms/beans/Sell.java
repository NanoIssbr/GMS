/**
 * 
 */
package org.gms.beans;

import java.util.Calendar;

/**
 * @author issbr
 *
 */
public class Sell {
	private String idProduct;
	private String dateSell;
	private Integer qnt;
	private Double prizeSell;
	private String libelleProduct;
	public String getIdProduct() {
		return idProduct;
	}
	/**
	 * @param idProduct
	 */
	public void setIdProduct(String idProduct) {
		this.idProduct = idProduct;
	}
	/**
	 * @return
	 */
	public String getDateSell() {
		return dateSell;
	}
	/**
	 * @param dateSell
	 */
	public void setDateSell(String dateSell) {
		this.dateSell = dateSell;
	}
	/**
	 * @return
	 */
	public Integer getQnt() {
		return qnt;
	}
	/**
	 * @param qnt
	 */
	public void setQnt(Integer qnt) {
		this.qnt = qnt;
	}
	public Double getPrize() {
		return prizeSell;
	}
	public void setPrize(Double prize) {
		this.prizeSell = prize;
	}
	/**
	 * @return the libelleProduct
	 */
	public String getLibelleProduct() {
		return libelleProduct;
	}
	/**
	 * @param libelleProduct the libelleProduct to set
	 */
	public void setLibelleProduct(String libelleProduct) {
		this.libelleProduct = libelleProduct;
	}
	/**
	 * @param idProduct
	 * @param dateSell
	 * @param qnt
	 */
	public Sell(String idProduct, String dateSell, Integer qnt, Double prize) {
		super();
		this.idProduct = idProduct;
		this.dateSell = dateSell;
		this.qnt = qnt;
		this.prizeSell = prize;
	}
	/**
	 * 
	 */
	public Sell() {
		super();
		// TODO Auto-generated constructor stub
	}
}
