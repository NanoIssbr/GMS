/**
 * 
 */
package org.gms.beans;

/**
 * @author issbr
 *
 */
public class Client {
	private String idClient;
	private String nomClient;
	private String prenomClient;
	/**
	 * @return the idClient
	 */
	public String getIdClient() {
		return idClient;
	}
	/**
	 * @param idClient the idClient to set
	 */
	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}
	/**
	 * @return the nomClient
	 */
	public String getNomClient() {
		return nomClient;
	}
	/**
	 * @param nomClient the nomClient to set
	 */
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
	/**
	 * @return the prenomClient
	 */
	public String getPrenomClient() {
		return prenomClient;
	}
	/**
	 * @param prenomClient the prenomClient to set
	 */
	public void setPrenomClient(String prenomClient) {
		this.prenomClient = prenomClient;
	}
	/**
	 * @param idClient
	 * @param nomClient
	 * @param prenomClient
	 */
	public Client(String idClient, String nomClient, String prenomClient) {
		super();
		this.idClient = idClient;
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
	}
	/**
	 * 
	 */
	public Client() {
		super();
	}
	
}
