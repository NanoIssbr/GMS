/**
 * 
 */
package org.gms.beans;


/**
 * @author hero
 *
 */
public class Product {

	private String idProduct ;
	private String libelleProduct;
	private String dateCreation;
	private Integer quantiteMin;
	private Double prize;
	private String reference;
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
	 * @return the dateCreation
	 */
	public String getDateCreation() {
		return dateCreation;
	}
	/**
	 * @param dateCreation the dateCreation to set
	 */
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}
	/**
	 * @return the quantiteMin
	 */
	public Integer getQuantiteMin() {
		return quantiteMin;
	}
	/**
	 * @param quantiteMin the quantiteMin to set
	 */
	public void setQuantiteMin(Integer quantiteMin) {
		this.quantiteMin = quantiteMin;
	}
	/**
	 * @return the prize
	 */
	public Double getPrize() {
		return prize;
	}
	/**
	 * @param prize the prize to set
	 */
	public void setPrize(Double prize) {
		this.prize = prize;
	}
	/**
	 * @return the references
	 */
	public String getReferences() {
		return reference;
	}
	/**
	 * @param references the references to set
	 */
	public void setReferences(String references) {
		this.reference = references;
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
	 * @param libelleProduct
	 * @param dateCreation
	 * @param quantiteMin
	 */
	public Product(String idProduct, String libelleProduct, String dateCreation, Integer quantiteMin, Double prix, String ref, Integer q) {
		super();
		this.idProduct = idProduct;
		this.libelleProduct = libelleProduct;
		this.dateCreation = dateCreation;
		this.quantiteMin = quantiteMin;
		this.prize = prix;
		this.reference = ref;
		this.qnt = q;
	}
	public Product() {
		
	}

}
