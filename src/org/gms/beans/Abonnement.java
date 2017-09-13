/**
 * 
 */
package org.gms.beans;

import java.util.Calendar;

/**
 * @author hero
 *
 */
public class Abonnement {
	private String idAbonnement;
	
	private String client;
	private Calendar dateAbonnement;
	private boolean etat;
	/**
	 * @return the client
	 */
	public String getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(String client) {
		this.client = client;
	}

	/**
	 * @return the dateAbonnement
	 */
	public Calendar getDateAbonnement() {
		return dateAbonnement;
	}

	/**
	 * @param dateAbonnement the dateAbonnement to set
	 */
	public void setDateAbonnement(Calendar dateAbonnement) {
		this.dateAbonnement = dateAbonnement;
	}
	
	
	/**
	 * @return the idAbonnement
	 */
	public String getIdAbonnement() {
		return idAbonnement;
	}

	/**
	 * @param idAbonnement the idAbonnement to set
	 */
	public void setIdAbonnement(String idAbonnement) {
		this.idAbonnement = idAbonnement;
	}

	/**
	 * @return the etat
	 */
	public boolean isEtat() {
		return etat;
	}

	/**
	 * @param etat the etat to set
	 */
	public void setEtat(boolean etat) {
		this.etat = etat;
	}

	public Abonnement() {
		
	}
	
	public Abonnement(String id, String client , Calendar date, boolean etat){
		this.idAbonnement = id;
		this.client = client;
		this.dateAbonnement = date;
		this.etat = etat;
	}

}
