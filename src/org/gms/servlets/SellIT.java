package org.gms.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gms.beans.Product;
import org.gms.beans.Sell;
import org.gms.clr.ServiceUtils;
import org.gms.clr.ServletObjectGMS;

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
		prd = prodDAO.findProduct(prd.getIdProduct(), Boolean.TRUE, request);
		stockDAO.sellFromStock(prd,1,  request);
		sellDAO.addSellOperation((Sell)request.getAttribute("sellObject"), request);
		request.removeAttribute("sellObject");
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
