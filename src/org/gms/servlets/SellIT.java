package org.gms.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gms.beans.Product;
import org.gms.clr.ServiceUtils;
import org.gms.clr.ServletObjectGMS;
import org.gms.dao.DAOFactory;
import org.gms.dao.StockDAO;

/**
 * Servlet implementation class GetAllProduct
 */
public class SellIT extends ServletObjectGMS {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SellIT() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Product prd = ServiceUtils.getProductIDToSell(request);
		System.out.println("from GET PRD ??");
		System.out.println(prd.getIdProduct());
		System.out.println("from GET PRD ??");
//		prd.setQnt(0);
		stockDAO.sellFromStock(prd,1,  request);
		this.forwardLocal(URI_HOME_PAGE, request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("SELLIT + POST");
	}

}
