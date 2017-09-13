/**
 * 
 */
package org.gms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.gms.beans.Abonnement;
import org.gms.beans.Product;
import org.gms.clr.ServiceUtils;

/**
 * @author hero
 *
 */
public class AbonnementDAOImp implements AbonnementDAO {
	private DAOFactory daoFactory;
	private static final String QUERY_GET_ALL_ABONNEMENTS = "select * from public.subscribe";
	private static final String ATTR_ABN_ID_ABN = "idsubscribe";
	private static final String ATTR_ABN_ID_CLIENT = "idclient";
	private static final String ATTR_ABN_DATE = "datesubscribe";

	/**
	 * 
	 */
	public AbonnementDAOImp() {
		// TODO Auto-generated constructor stub
	}

	public AbonnementDAOImp(DAOFactory d) {
		this.daoFactory = d;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.gms.dao.AbonnenemtDAO#getAbonnementTerminated(javax.servlet.http.
	 * HttpServletRequest)
	 */
	@Override
	public List<Abonnement> getAbonnements(HttpServletRequest req) throws ParseException {
		Connection cnx = null;
		PreparedStatement pState = null;
		ResultSet rSet = null;
		Product produit = null;
		List<Abonnement> listAbonnements = new ArrayList<Abonnement>();
		Abonnement abon = new Abonnement();
		try {
			cnx = daoFactory.getConnection();
			pState = cnx.prepareStatement(QUERY_GET_ALL_ABONNEMENTS);
			rSet = pState.executeQuery();
			if (rSet != null) {
				List<Abonnement> listeABNs = mapMultiAbonnement(rSet);
				if (!listeABNs.isEmpty()) {
					for (Abonnement abn : listeABNs) {
						Calendar cal = abn.getDateAbonnement();
						cal.add(Calendar.YEAR, 1);
						Calendar cal1 = Calendar.getInstance();
						cal1.setTime(new Date());
						if (cal.compareTo(cal1) <= 0) {
							abn.setEtat(true);
						} else {
							abn.setEtat(false);
						}
					}
				}
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			ServiceUtils.closeResources(pState, rSet, cnx);
		}
		return listAbonnements;
	}

	public static List<Abonnement> mapMultiAbonnement(ResultSet rSet) {
		List<Abonnement> listeAbonnements = new ArrayList<Abonnement>();
		if (rSet == null) {
			Abonnement abn = null;
			try {
				while (rSet.next()) {
					abn = new Abonnement();
					abn.setClient(rSet.getString(ATTR_ABN_ID_CLIENT));
					abn.setDateAbonnement(ServiceUtils.getDateFromString(rSet.getString(ATTR_ABN_DATE)));
					abn.setIdAbonnement(rSet.getString(ATTR_ABN_ID_ABN));
					listeAbonnements.add(abn);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listeAbonnements;
	}
}
