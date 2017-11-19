package org.gms.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.gms.beans.Abonnement;
import org.gms.beans.Product;
import org.gms.beans.Users;
import org.gms.clr.ServiceUtils;
import org.gms.clr.ServletObjectGMS;
import org.gms.dao.DAOException;

/**
 * Servlet implementation class LogIn
 */
public class LogIn extends ServletObjectGMS {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		initContext();
		HttpSession session = request.getSession();
		if(session.getAttribute("_isSessionU") != null){
			System.out.println("where in login servlet their is a session");
			List<Product> prods = prodDAO.findProductByQuantite(request);
			//System.out.println(ServiceUtils.objectToJson(prods));
			List<Abonnement> abonnements = null;
			try {
				abonnements = abonnDAO.getAbonnements(request);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				throw new DAOException(e);
			}
			request.setAttribute("abonnements", abonnements);
			request.setAttribute("prods", prods);
			this.forwardLocal(URI_HOME_PAGE, request, response);
		}else{
			this.forwardLocal(URI_INDEX_PAGE, request, response);
			System.out.println("where in login servlet their is no fucking session");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		initContext();
		Users user = ServiceUtils.getLogInForm(request);
		HttpSession session = request.getSession();
		session.setAttribute("_isSessionU", user);
		List<Product> prods = prodDAO.findProductByQuantite(request);
		List<Abonnement> abonnements = null;
		try {
			abonnements = abonnDAO.getAbonnements(request);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e);
		}
		request.setAttribute("abonnements", abonnements);
		request.setAttribute("prods", prods);
		this.forwardLocal(URI_HOME_PAGE, request, response);
	}

}
