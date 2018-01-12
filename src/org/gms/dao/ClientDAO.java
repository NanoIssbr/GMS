/**
 * 
 */
package org.gms.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.gms.beans.Client;

/**
 * @author issbr
 *
 */
public interface ClientDAO {
	
	
	/** 
	 * add new Client
	 * @param client
	 * @param req
	 */
	public void addClient(Client client, HttpServletRequest req);
	
	
	/**
	 * get client by FirstName & LastName
	 * @param client
	 * @param req
	 * @return
	 */
	public Client getClient(Client client, HttpServletRequest req);
	
	
	/**
	 * Get All Clients
	 * @param client
	 * @param req
	 * @return
	 */
	public List<Client> getAllClients(HttpServletRequest req);
}
