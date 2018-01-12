/**
 * 
 */
package org.gms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.gms.beans.Client;
import org.gms.beans.Product;
import org.gms.clr.ServiceUtils;

/**
 * @author issbr
 *
 */
public class ClientDAOImpl implements ClientDAO {
	private static final String QUERY_GET_ALL_CLIENTS = "select * from client;";
	private static final String QUERY_GET_CLIENTS_BY_NAMES = "select * from client where nomclient = ? and prenomclient = ?;";
	private static final String CLIENT_ATTR_ID = "idclient";
	private static final String CLIENT_ATTR_LASTNAME = "nomclient";
	private static final String CLIENT_ATTR_FIRSTNAME = "prenomclient";
	private DAOFactory _daoFactory;
	/* (non-Javadoc)
	 * @see org.gms.dao.ClientDAO#addClient(org.gms.beans.Client, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void addClient(Client client, HttpServletRequest req) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.gms.dao.ClientDAO#getClient(org.gms.beans.Client, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Client getClient(Client client, HttpServletRequest req) {
		
		Client clr = null;
		Connection cnx = null;
		PreparedStatement pState = null;
		ResultSet rSet = null;
		try {
			cnx = _daoFactory.getConnection();
			pState = ServiceUtils.initRequestPrepared(cnx, QUERY_GET_CLIENTS_BY_NAMES, Boolean.FALSE, client.getNomClient(), client.getPrenomClient());
			rSet = pState.executeQuery();
			if (rSet != null) {
				clr = mapClient(rSet);
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			ServiceUtils.closeResources(cnx, rSet, pState);
		}
		return clr;
	}

	/* (non-Javadoc)
	 * @see org.gms.dao.ClientDAO#getAllClients(org.gms.beans.Client, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public List<Client> getAllClients(HttpServletRequest req) {
		List<Client> listClients = new ArrayList<>();
		Connection cnx = null;
		PreparedStatement pState = null;
		ResultSet rSet = null;
		try {
			cnx = _daoFactory.getConnection();
			pState = cnx.prepareStatement(QUERY_GET_ALL_CLIENTS);
			rSet = pState.executeQuery();
			if (rSet != null) {
				listClients = mapMultiClients(rSet);
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			ServiceUtils.closeResources(cnx, rSet, pState);
		}
		return listClients;
	}
	
	private List<Client> mapMultiClients(ResultSet rSet) throws SQLException{
		List<Client> listClients = new ArrayList<>();
		Client client = null;
		if(rSet != null){
			while(rSet.next()){
				client = new Client();
				client.setIdClient(rSet.getString(CLIENT_ATTR_ID));
				client.setPrenomClient(rSet.getString(CLIENT_ATTR_FIRSTNAME));
				client.setNomClient(rSet.getString(CLIENT_ATTR_LASTNAME));
				listClients.add(client);
			}
		}
		return listClients;
	}
	private Client mapClient(ResultSet rSet){
		Client client = null;
		if(rSet != null){
			client.setIdClient(CLIENT_ATTR_ID);
			client.setNomClient(CLIENT_ATTR_LASTNAME);
			client.setPrenomClient(CLIENT_ATTR_FIRSTNAME);
		}
		return client;
	}
	
	public ClientDAOImpl(DAOFactory daoFactory){
		this._daoFactory = daoFactory;
	}

}
