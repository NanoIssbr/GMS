package org.gms.clr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.gms.beans.ErrorObject;
import org.gms.beans.Product;
import org.gms.beans.Users;

//import javax.servlet.http.HttpServletRequest;

public class ServiceUtils {
	private static final String INPUT_MAIL = "userName";
	private static final String INPUT_LIBELLE_PRODUCT = "libelleProduct";
	private static final String INPUT_QUANTITE_MIN_PRODUCT = "qProduct";
	private static final String INPUT_PRIZE_PRODUCT = "pProduct";
	private static final String INPUT_REFERENCE_PRODUCT = "rProduct";
	public ServiceUtils(){
		
	}
	public static Calendar getDateFromString(String data){
		Calendar cal = Calendar.getInstance();
		Integer y,m,d;
		String ch = null;
		ch = data.substring(0,2);
		d = Integer.valueOf(ch);
		ch = data.substring(3,5);
		m = Integer.valueOf(ch);
		ch = data.substring(6,10);
		y = Integer.valueOf(ch);
		cal.set(y, m, d);
		return cal;
	}
	/**
	 * @param req
	 * @return Users
	 */
	public static Users getLogInForm(HttpServletRequest req){
		Users user = new Users();
		user.setUserName(req.getParameter(INPUT_MAIL));
		return user;
	}
	
	public static Product getProductForm(HttpServletRequest req){
		Product produit = new Product();
		produit.setLibelleProduct(req.getParameter(INPUT_LIBELLE_PRODUCT));
		produit.setQuantiteMin(Integer.valueOf(req.getParameter(INPUT_QUANTITE_MIN_PRODUCT)));
		produit.setPrize(Double.valueOf(req.getParameter(INPUT_PRIZE_PRODUCT)));
		produit.setReferences(req.getParameter(INPUT_REFERENCE_PRODUCT));
		return produit;
	}
	/**
	 * @param list
	 * @return
	 */
	public static String getErreurs(List<ErrorObject> list){
		String message = "";
		for(ErrorObject error : list){
			if(!error.getMessages().equals("")){
				message += error.getMessages()+"\n";
			}
		}
		return message;
	}
	/**
	 * @param resources
	 */
	public static void closeResources(Object... resources) {
		if (resources != null) {
			try {
				for (Object resource : resources) {
					if (resource == null) {
						System.out.println("resource null");
						continue;
					}
					// Connection
					if (resource instanceof Connection) {
						((Connection) resource).close();
					}
					// PreparedStatement
					if (resource instanceof PreparedStatement) {
						((PreparedStatement) resource).close();
					}
					// ResultSet
					if (resource instanceof ResultSet) {
						((ResultSet) resource).close();
					}
				}
			} catch (SQLException e) {
				System.out.println("Can't close the ResultSet " + e);
			}
		}
	}
	

}
