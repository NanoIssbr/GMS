/**
 * 
 */
package org.gms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.gms.beans.Client;
import org.gms.beans.ErrorObject;
import org.gms.beans.Product;
import org.gms.clr.ServiceUtils;

/**
 * @author issbr
 *
 */
public class ClientDAOImpl implements ClientDAO {
	private static final String QUERY_GET_ALL_CLIENTS = "select * from client;";
	private static final String QUERY_GET_CLIENTS_BY_NAMES = "select * from client where nomclient = ? and prenomclient = ?;";
	private static final String QUERY_ADD_NEW_CLIENT = "insert into client (idclient,nomclient,prenomclient) values(?,?,?);";
	private static final String CLIENT_ATTR_ID = "idclient";
	private static final String CLIENT_ATTR_LASTNAME = "nomclient";
	private static final String CLIENT_ATTR_FIRSTNAME = "prenomclient";
	private DAOFactory _daoFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gms.dao.ClientDAO#addClient(org.gms.beans.Client,
	 * javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void addClient(Client client, HttpServletRequest req) {
		String message = null;
		Connection cnx = null;
		PreparedStatement pState = null;
		Client clr = getClient(client, req);
		if (clr == null) {
			Calendar cal = Calendar.getInstance();
			String idClient = "client-" + cal.get(Calendar.HOUR) + "-" + cal.get(Calendar.MINUTE) + "-" + cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.SECOND);
			client.setIdClient(idClient);
			// client.setDateCreation(cal.get(Calendar.DAY_OF_WEEK) + "-" +
			// cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.YEAR));
			try {
				cnx = _daoFactory.getConnection();
				pState = ServiceUtils.initRequestPrepared(cnx, QUERY_ADD_NEW_CLIENT, false, client.getIdClient(), client.getNomClient(), client.getPrenomClient());
				int status = pState.executeUpdate();
				if (status == 0) {
					message = "Impossible d'ajouter client";
					throw new DAOException("Can't create the product !");
				}else{
					message = "Client ajouter avec succes";
				}
			} catch (SQLException e) {
				message = "Impossible d'ajouter client";
				throw new DAOException(e);
			} finally {
				ServiceUtils.closeResources(cnx, pState);
			}
		}else{
			message = "client existe !";
		}
		req.setAttribute("message", message);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gms.dao.ClientDAO#getClient(org.gms.beans.Client,
	 * javax.servlet.http.HttpServletRequest)
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
			if (rSet.next()) {
				clr = mapClient(rSet);
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			ServiceUtils.closeResources(cnx, rSet, pState);
		}
		return clr;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gms.dao.ClientDAO#getAllClients(org.gms.beans.Client,
	 * javax.servlet.http.HttpServletRequest)
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

	private List<Client> mapMultiClients(ResultSet rSet) throws SQLException {
		List<Client> listClients = new ArrayList<>();
		Client client = null;
		if (rSet != null) {
			while (rSet.next()) {
				client = new Client();
				client.setIdClient(rSet.getString(CLIENT_ATTR_ID));
				client.setPrenomClient(rSet.getString(CLIENT_ATTR_FIRSTNAME));
				client.setNomClient(rSet.getString(CLIENT_ATTR_LASTNAME));
				listClients.add(client);
			}
		}
		return listClients;
	}

	private Client mapClient(ResultSet rSet) {
		Client client = null;
		if (rSet != null) {
			client = new Client();
			client.setIdClient(CLIENT_ATTR_ID);
			client.setNomClient(CLIENT_ATTR_LASTNAME);
			client.setPrenomClient(CLIENT_ATTR_FIRSTNAME);
		}
		return client;
	}

	public ClientDAOImpl(DAOFactory daoFactory) {
		this._daoFactory = daoFactory;
	}

}
