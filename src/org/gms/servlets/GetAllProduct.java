package org.gms.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class GetAllProduct extends ServletObjectGMS {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetAllProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Product prdSearch = ServiceUtils.getSearchPrdLibelleForm(request);
//		List<Product> listPrds = null;
//		if (prdSearch.getLibelleProduct() != null) {
//			listPrds = prodDAO.findProducts(prdSearch.getLibelleProduct(), false, request);
//		} else {
//			listPrds = prodDAO.getAllProduct(request);
//		}
//
//		List<HashMap<String, Object>> listToConvertToHtmlTable = ServiceUtils.getListOfMapByProduct(listPrds);
////		Map<String, Object> map = null;
////		if (listPrds != null) {
////			for (Product prd : listPrds) {
////				map = new HashMap<String, Object>();
////				map.put("libelle", prd.getLibelleProduct());
////				map.put("prix", prd.getPrize());
////				map.put("ref", prd.getReferences());
////				listToConvertToHtmlTable.add((HashMap<String, Object>) map);
////			}
////		}
//		response.getWriter().print(ServiceUtils.listToHTMLTable(listToConvertToHtmlTable));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("HEEEEEEEEEEEEEEEEEELLOOOOOOOOOOOOOOOOOOOOOOO");
		Product prdSearch = ServiceUtils.getSearchPrdLibelleFormModal(request);
		List<Product> listPrds = null;
		if (prdSearch.getLibelleProduct() != null) {
			listPrds = prodDAO.findProducts(prdSearch.getLibelleProduct(), false, request);
		} else {
			listPrds = prodDAO.findProductByQuantite(request);
		}

		List<HashMap<String, Object>> listToConvertToHtmlTable = ServiceUtils.getListOfMapByProduct(listPrds, Boolean.TRUE);
//		Map<String, Object> map = null;
//		if (listPrds != null) {
//			for (Product prd : listPrds) {
//				map = new HashMap<String, Object>();
//				map.put("libelle", prd.getLibelleProduct());
//				map.put("prix", prd.getPrize());
//				map.put("ref", prd.getReferences());
//				listToConvertToHtmlTable.add((HashMap<String, Object>) map);
//			}
//		}
		response.getWriter().print(ServiceUtils.listToHTMLTable(listToConvertToHtmlTable));
	}

}
