/**
 * 
 */
package org.gms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.gms.beans.ErrorObject;
import org.gms.beans.Product;
import org.gms.beans.Stock;
import org.gms.clr.ServiceUtils;

/**
 * @author issbr
 *
 */
public class StockDAOImpl implements StockDAO {
	private static final String TABLE_NAME = "public.stock";
	private static final String ATTR_PRD_ID_SROCK = "idproductstock";
	private static final String ATTR_QNT_SROCK = "quantite";
	private static final String QUERY_ADD_PRODUCT_IN_STOCK = "insert into " + TABLE_NAME + " (" + ATTR_PRD_ID_SROCK + ", " + ATTR_QNT_SROCK + ") values (?,?)";
	private static final String QUERY_GET_OUT_FROM_STOCK = "update " + TABLE_NAME + " set "+ ATTR_QNT_SROCK +" = ? where "+ATTR_PRD_ID_SROCK+" = ?";
	private DAOFactory daoFactory;

	@Override
	public void addToStock(Object prd, HttpServletRequest req) {
		@SuppressWarnings("unchecked")
		List<ErrorObject> errorList = (List<ErrorObject>) req.getServletContext().getAttribute("erreurListe");
		Connection cnx = null;
		PreparedStatement pState = null;
		if (prd != null && prd instanceof Product) {
			try {
				cnx = daoFactory.getConnection();
				pState = ServiceUtils.initRequestPrepared(cnx, QUERY_ADD_PRODUCT_IN_STOCK, false, ((Product) prd).getIdProduct(), ((Product) prd).getQnt());
				int status = pState.executeUpdate();
				if (status == 0) {
					errorList.add(new ErrorObject("Erreur", "Can't add to stock !"));
					throw new DAOException("Can't add to stock!");
				}
			} catch (SQLException e) {
				throw new DAOException(e);
			} finally {
				ServiceUtils.closeResources(cnx, pState);
			}

		} else {
			errorList.add(new ErrorObject("Erreur", "Can't add to stock !"));
			System.out.println("ttttttttttttttt null values StockDAOImpl.addToStock");
			throw new DAOException("Can't add to stock !");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gms.dao.StockDAO#getOutFromStock(java.lang.Object,
	 * javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void getOutFromStock(Object prd, HttpServletRequest req) {
		Connection cnx = null;
		PreparedStatement pState = null;
		if (prd != null && prd instanceof Product) {
			try {
				cnx = daoFactory.getConnection();
				pState = ServiceUtils.initRequestPrepared(cnx, QUERY_GET_OUT_FROM_STOCK, false, ((Product) prd).getQnt(), ((Product) prd).getIdProduct());
				int status = pState.executeUpdate();
				if (status == 0) {
					throw new DAOException("Can't get out from stock!");
				}
			} catch (SQLException e) {
				throw new DAOException(e);
			} finally {
				ServiceUtils.closeResources(cnx, pState);
			}

		} else {
			System.out.println("ttttttttttttttt null values StockDAOImpl.addToStock");
			throw new DAOException("Can't add to stock !");
		}
	}

}
