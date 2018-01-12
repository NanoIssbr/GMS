package org.gms.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gms.beans.Product;
import org.gms.clr.ServiceUtils;
import org.gms.clr.ServletObjectGMS;

/**
 * Servlet implementation class GetAllProduct
 */
public class GetAllProductToSell extends ServletObjectGMS {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetAllProductToSell() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("tessssssssssssssssssssssssssssssssssssssssssssssst");
		Product prdSearch = ServiceUtils.getSearchPrdLibelleFormModal(request);
		List<Product> listPrds = null;
		if (prdSearch.getLibelleProduct() != null) {
			listPrds = prodDAO.findProducts(prdSearch.getLibelleProduct(), false, request);
		} else {
			listPrds = prodDAO.getAllProduct(request, Boolean.TRUE);
		}

		List<HashMap<String, Object>> listToConvertToHtmlTable = new ArrayList<>();
		if (listPrds != null) {
			listToConvertToHtmlTable = ServiceUtils.getListOfMapByProduct(listPrds, Boolean.TRUE, Boolean.TRUE);
		}
		response.getWriter().print(ServiceUtils.listToHTMLTableSell(listToConvertToHtmlTable, Boolean.TRUE));
		System.out.println("tessssssssssssssssssssssssssssssssssssssssssssssst 2");
	}

}
