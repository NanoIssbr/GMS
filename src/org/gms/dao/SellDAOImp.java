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

import org.gms.beans.Sell;
import org.gms.clr.ServiceUtils;

/**
 * @author issbr
 *
 */
public class SellDAOImp implements SellDAO {
	private static final String QUERY_ADD_SELL_OPERATION = "insert into public.sell (productid,quantite,datesell,prizesell) values (?,?,?,?);";
	private static final String QUERY_GET_ALL_SELLS_OPERATIONS = "select * from sell s, product p where p.idproduct = s.productid;";
	private DAOFactory daoFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gms.dao.SellDAO#addSellOperation(org.gms.beans.Sell,
	 * javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void addSellOperation(Sell sell, HttpServletRequest req) {
		Connection cnx = null;
		PreparedStatement pState = null;
		try {
			//System.out.println(sell);
			cnx = daoFactory.getConnection();

			pState = ServiceUtils.initRequestPrepared(cnx, QUERY_ADD_SELL_OPERATION, false, sell.getIdProduct(), sell.getQnt(), sell.getDateSell(), sell.getPrize());
			int status = pState.executeUpdate();
			if (status == 0) {
				throw new DAOException("Can't set sell operation");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			ServiceUtils.closeResources(cnx, pState);
		}
	}

	public SellDAOImp(DAOFactory df) {
		this.daoFactory = df;
	}

	/* (non-Javadoc)
	 * @see org.gms.dao.SellDAO#getHistOfSellOperation()
	 */
	@Override
	public List<Sell> getHistOfSellOperations(Calendar dateFrom, Calendar dateTo) {
		List<Sell> listOfAllSellOperation = new ArrayList<>();
		Connection cnx = null;
		PreparedStatement pState = null;
		ResultSet rSet = null;
		try {
			cnx = daoFactory.getConnection();
			pState = ServiceUtils.initRequestPrepared(cnx, QUERY_GET_ALL_SELLS_OPERATIONS, false);
			rSet = pState.executeQuery();
			if (rSet.next()) {
				listOfAllSellOperation = mapMultiSellsOperations(rSet);
			}
		} catch (SQLException e) {
			throw new DAOConfigurationException("Can't execute the query " + e);
		} finally {
			ServiceUtils.closeResources(cnx, pState, rSet);
		}
		return listOfAllSellOperation;
	}
	private List<Sell> mapMultiSellsOperations(ResultSet rSet) throws SQLException{
		List<Sell> listOfAllSellOperation = new ArrayList<>();
		Sell vente = null;
		if(rSet != null){
			while(rSet.next()){
				vente = new Sell();
				vente.setIdProduct(rSet.getString("productid"));
				vente.setDateSell(rSet.getString("datesell"));
				vente.setLibelleProduct(rSet.getString("libelleproduct"));
				vente.setPrize(rSet.getDouble("prizesell"));
				vente.setQnt(rSet.getInt("quantite"));
				listOfAllSellOperation.add(vente);
			}
		}
		return listOfAllSellOperation;
	}
}
