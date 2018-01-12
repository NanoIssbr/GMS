package org.gms.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gms.beans.Sell;
import org.gms.clr.ServiceUtils;
import org.gms.clr.ServletObjectGMS;

/**
 * Servlet implementation class GetSellHistory
 */
public class GetSellHistory extends ServletObjectGMS {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSellHistory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Sell> listOfSellsOperations = sellDAO.getHistOfSellOperations(Calendar.getInstance(), Calendar.getInstance());
		List<HashMap<String, Object>> listToConvertToHtmlTable = new ArrayList<>();
		if (listOfSellsOperations != null) {
			listToConvertToHtmlTable = ServiceUtils.getListOfMapBySell(listOfSellsOperations);
		}
		response.getWriter().print(ServiceUtils.listToHTMLTableSellOperations(listToConvertToHtmlTable));
	}

}
