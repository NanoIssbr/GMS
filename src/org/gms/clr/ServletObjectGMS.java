package org.gms.clr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gms.beans.ErrorObject;
import org.gms.dao.AbonnementDAO;
import org.gms.dao.DAOFactory;
import org.gms.dao.ProductDAO;
import org.gms.dao.StockDAO;

public class ServletObjectGMS extends HttpServlet {
	public static final String URI_INDEX_PAGE = "/index.jsp";
	public static final String URI_HOME_PAGE = "/WEB-INF/jspFiles/home.jsp";
	private static final String CONF_DAO_FACTORY = "daofactory";
	public ProductDAO prodDAO;
	public AbonnementDAO abonnDAO;
	public DAOFactory daoFactory;
	public StockDAO stockDAO;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ServletObjectGMS() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param uri
	 * @param r
	 * @param rs
	 * @throws IOException
	 * @throws ServletException
	 */
	public void forwardLocal(String uri, HttpServletRequest r, HttpServletResponse rs) throws IOException, ServletException{
		this.getServletContext().getRequestDispatcher(uri).forward(r, rs);
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	public void init() throws ServletException{
    	this.prodDAO = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getProductDAO();
    	this.abonnDAO = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getAbonnementDAO();
    	this.stockDAO = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getStockDAO();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void initContext(){
		if(this.getServletContext().getAttribute("erreurListe") == null){
			List<ErrorObject> errorList = new ArrayList<ErrorObject>();
			errorList.add(new ErrorObject(""));
			this.getServletContext().setAttribute("erreurListe", errorList);
		}
	}

}
