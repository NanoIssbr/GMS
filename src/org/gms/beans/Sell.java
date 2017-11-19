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
	private Calendar dateSell;
	private Integer qnt;
	private Double prizeSell;
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
	public Calendar getDateSell() {
		return dateSell;
	}
	/**
	 * @param dateSell
	 */
	public void setDateSell(Calendar dateSell) {
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
	 * @param idProduct
	 * @param dateSell
	 * @param qnt
	 */
	public Sell(String idProduct, Calendar dateSell, Integer qnt, Double prize) {
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
