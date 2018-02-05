package org.gms.clr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.gms.beans.Client;
import org.gms.beans.ErrorObject;
import org.gms.beans.Product;
import org.gms.beans.Sell;
import org.gms.beans.Users;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//import javax.servlet.http.HttpServletRequest;

public class ServiceUtils {
	private static final String INPUT_MAIL = "userName";
	private static final String INPUT_LIBELLE_PRODUCT = "libelleProduct";
	private static final String INPUT_QUANTITE_MIN_PRODUCT = "qProduct";
	private static final String INPUT_PRIZE_PRODUCT = "pProduct";
	private static final String INPUT_REFERENCE_PRODUCT = "rProduct";
	private static final String INPUT_SRC_BY_LIBELLE_PRODUCT = "rPrd";
	private static final String INPUT_SRC_BY_LIBELLE_PRODUCT_MODAL = "rPrdModal";
	private static final String ID_FROM_URI_TO_SELL = "n";
	private static final String INPUT_CLIENT_FIRSTNAME = "clientFirstName";
	private static final String INPUT_CLIENT_LASTNAME = "clientLastName";

	public ServiceUtils() {

	}
	/**
	 * @param date
	 * @param separator
	 * @return
	 */
	public static String dateToString(Date date, String separator)
	  {
	    String formattedDate = null;
	    if (date != null) {
	      GregorianCalendar grCal = new GregorianCalendar();
	      grCal.setTime(date);
	      int year = grCal.get(1);
	      int month = grCal.get(2);
	      int day = grCal.get(5);
	      System.out.println(grCal.get(12));
	      month++;
	      formattedDate = day + separator + month + separator + year + " " + grCal.get(6) + ":" + grCal.get(12);
	    }
	    return formattedDate;
	  }
	/**
	 * @param data
	 * @return
	 */
	public static Calendar getDateFromString(String data) {
		Calendar cal = Calendar.getInstance();
		Integer y, m, d;
		String ch = null;
		ch = data.substring(0, 2);
		d = Integer.valueOf(ch);
		ch = data.substring(3, 5);
		m = Integer.valueOf(ch);
		ch = data.substring(6, 10);
		y = Integer.valueOf(ch);
		cal.set(y, m, d);
		return cal;
	}

	/**
	 * @param req
	 * @return Users
	 */
	public static Users getLogInForm(HttpServletRequest req) {
		Users user = new Users();
		user.setUserName(req.getParameter(INPUT_MAIL));
		return user;
	}

	/**
	 * @param req
	 * @return
	 */
	public static Product getProductForm(HttpServletRequest req) {
		Product produit = new Product();
		produit.setLibelleProduct(req.getParameter(INPUT_LIBELLE_PRODUCT));
		produit.setQnt(Integer.valueOf(req.getParameter(INPUT_QUANTITE_MIN_PRODUCT)));
		produit.setPrize(Double.valueOf(req.getParameter(INPUT_PRIZE_PRODUCT)));
		produit.setReferences(req.getParameter(INPUT_REFERENCE_PRODUCT));
		return produit;
	}
	
	public static Product getProductIDToSell(HttpServletRequest req) {
		Product produit = new Product();
		produit.setIdProduct(req.getParameter(ID_FROM_URI_TO_SELL));
		//produit.setPrize(Double.valueOf(req.getParameter("p")));
		return produit;
	}

	/**
	 * @param req
	 * @return
	 */
	public static Product getSearchPrdLibelleForm(HttpServletRequest req) {
		Product produit = new Product();
		if (req.getParameter(INPUT_SRC_BY_LIBELLE_PRODUCT) != null && !req.getParameter(INPUT_SRC_BY_LIBELLE_PRODUCT).isEmpty()) {
			produit.setLibelleProduct("%" + req.getParameter(INPUT_SRC_BY_LIBELLE_PRODUCT) + "%");
		}

		return produit;
	}
	
	/**
	 * @param req
	 * @return
	 */
	public static Client getClientForm(HttpServletRequest req) {
		Client client = new Client();
		if (req.getParameter(INPUT_CLIENT_FIRSTNAME) != null && !req.getParameter(INPUT_CLIENT_FIRSTNAME).isEmpty() && req.getParameter(INPUT_CLIENT_LASTNAME) != null && !req.getParameter(INPUT_CLIENT_LASTNAME).isEmpty()) {
			client.setNomClient(req.getParameter(INPUT_CLIENT_LASTNAME));
			client.setPrenomClient(req.getParameter(INPUT_CLIENT_FIRSTNAME));
		}

		return client;
	}

	public static Product getSearchPrdLibelleFormModal(HttpServletRequest req) {
		Product produit = new Product();
		if (req.getParameter(INPUT_SRC_BY_LIBELLE_PRODUCT_MODAL) != null && !req.getParameter(INPUT_SRC_BY_LIBELLE_PRODUCT_MODAL).isEmpty()) {
			produit.setLibelleProduct("%" + req.getParameter(INPUT_SRC_BY_LIBELLE_PRODUCT_MODAL) + "%");
		}

		return produit;
	}

	/**
	 * @param list
	 * @return
	 */
	public static String getErreurs(List<ErrorObject> list) {
		String message = "";
		for (ErrorObject error : list) {
			if (!error.getMessages().equals("")) {
				message += error.getMessages() + "\n";
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

	/**
	 * @param connexion
	 * @param sql
	 * @param returnGeneratedKeys
	 * @param objets
	 * @return
	 * @throws SQLException
	 */
	public static PreparedStatement initRequestPrepared(Connection connexion, String sql, boolean returnGeneratedKeys, Object... objets) throws SQLException {
		PreparedStatement preparedStatement = connexion.prepareStatement(sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
		int nbrParam = preparedStatement.getParameterMetaData().getParameterCount();
		for (int i = 0; i < nbrParam; i++) {
			System.out.println(i + " " + objets[i]);
			preparedStatement.setObject(i + 1, objets[i]);
			preparedStatement.setObject(i + 1, objets[i]);
		}
		return preparedStatement;
	}

	/**
	 * @param object
	 * @return
	 */
	public static String objectToJson(Object object) {
		Gson gson = new GsonBuilder().setDateFormat(0, 0).create();
		String context = gson.toJson(object);
		return context;
	}

	public static StringBuffer listToHTMLTable(List<HashMap<String, Object>> listObj) {
		StringBuffer tableWithData = new StringBuffer("<table class=\"table table-hover table-bordered\"style=\"border-collapse: collapse; color: white;\"><thead><tr><th>Reference</th><th>Prix</th><th>Quantite</th><th>Libelle</th></tr></thead>");
		for (HashMap<String, Object> map : listObj) {
			// tableWithData.append("<tr><td></td><td></td><td></td></tr>");
			tableWithData.append("<tr>");
			for (String key : map.keySet()) {
				tableWithData.append("<td>" + map.get(key) + "</td>");
			}
			tableWithData.append("</tr>");

		}
		tableWithData.append("</table>");
		return tableWithData;
	}

	/**
	 * @param listObj
	 * @return
	 */
	public static StringBuffer listToHTMLTableSell(List<HashMap<String, Object>> listObj, Boolean withQnt) {
		StringBuffer tableWithData = new StringBuffer("<table class=\"table table-hover table-bordered\"style=\"border-collapse: collapse; color: black;\"><thead><tr><th>Reference</th><th>Prix</th>");
		if(withQnt == Boolean.TRUE){
			tableWithData.append("<th>Quantite</th>");
		}
		tableWithData.append("<th>Libelle</th><th>Prix vente</th><th>Vendre</th></tr></thead>");
		for (HashMap<String, Object> map : listObj) {
			tableWithData.append("<tr>");
			for (String key : map.keySet()) {
				if(key.equals("n")){
					continue;
				}
				tableWithData.append("<td>" + map.get(key) + "</td>");
			}
			tableWithData.append("<td><input type=\"number\" name=\"qProduct\" class=\"form-control\" id=\""+map.get("n")+"\" min=\"0\"  /></td>");
			tableWithData.append("<td><a id=\"a"+map.get("n")+"\"onClick=\"\" href=\"vendre?n="+map.get("n")+"\">Vente</a></td>");
			tableWithData.append("</tr>");

		}
		tableWithData.append("</table>");
		return tableWithData;
	}
	
	
	
	
	/**
	 * @param listObj
	 * @return
	 */
	public static StringBuffer listToHTMLTableSellOperations(List<HashMap<String, Object>> listObj) {
		StringBuffer tableWithData = new StringBuffer("<table class=\"table table-hover table-bordered\"style=\"border-collapse: collapse; color: black;\"><thead><tr><th>Libelle</th><th>Date vente</th><th>Prix</th><th>Quantite</th>");
		for (HashMap<String, Object> map : listObj) {
			tableWithData.append("<tr>");
			for (String key : map.keySet()) {
				tableWithData.append("<td>" + map.get(key) + "</td>");
			}
			tableWithData.append("</tr>");

		}
		tableWithData.append("</table>");
		return tableWithData;
	}
	
	
	
	public static StringBuffer listToHTMLTableClients(List<HashMap<String, Object>> listObj) {
		StringBuffer tableWithData = new StringBuffer("<table class=\"table table-hover table-bordered\"style=\"border-collapse: collapse; color: black;\"><thead><tr><th>Nom</th><th>Prenom</th>");
		for (HashMap<String, Object> map : listObj) {
			tableWithData.append("<tr>");
			for (String key : map.keySet()) {
				tableWithData.append("<td>" + map.get(key) + "</td>");
			}
			tableWithData.append("</tr>");

		}
		tableWithData.append("</table>");
		return tableWithData;
	}

	/**
	 * @param listPrds
	 * @param withQnt
	 * @param withId
	 * @return
	 */
	public static List<HashMap<String, Object>> getListOfMapByProduct(List<Product> listPrds, Boolean withQnt, Boolean withId) {
		List<HashMap<String, Object>> listToConvertToHtmlTable = new ArrayList<>();
		Map<String , Object> map = null;
		for (Product prd : listPrds) {
			map = new HashMap<String, Object>();
			map.put("libelle", prd.getLibelleProduct());
			map.put("prix", prd.getPrize());
			map.put("ref", prd.getReferences());
			if(withId == Boolean.TRUE){
				map.put("n", prd.getIdProduct());
			}
			if(withQnt == Boolean.TRUE){
				map.put("qnt", prd.getQnt());
			}
			listToConvertToHtmlTable.add((HashMap<String, Object>) map);
		}
		return listToConvertToHtmlTable;
	}
	
	
	
	public static List<HashMap<String, Object>> getListOfMapByClient(List<Client> listClients) {
		List<HashMap<String, Object>> listToConvertToHtmlTable = new ArrayList<>();
		Map<String , Object> map = null;
		for (Client client : listClients) {
			map = new HashMap<String, Object>();
			map.put("nom", client.getNomClient());
			map.put("prenom", client.getPrenomClient());
			listToConvertToHtmlTable.add((HashMap<String, Object>) map);
		}
		return listToConvertToHtmlTable;
	}
	
	
	/**
	 * @param listPrds
	 * @param withQnt
	 * @param withId
	 * @return
	 */
	public static List<HashMap<String, Object>> getListOfMapBySell(List<Sell> listSellsOperations) {
		List<HashMap<String, Object>> listToConvertToHtmlTable = new ArrayList<>();
		Map<String , Object> map = null;
		for (Sell vente : listSellsOperations) {
			map = new HashMap<String, Object>();
			map.put("libelle", vente.getQnt());
			map.put("prix", vente.getDateSell());
			map.put("qnt", vente.getPrize());
			map.put("date", vente.getLibelleProduct());
			listToConvertToHtmlTable.add((HashMap<String, Object>) map);
		}
		return listToConvertToHtmlTable;
	}

}
