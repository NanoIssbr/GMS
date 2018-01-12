package org.gms.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gms.beans.Client;
import org.gms.clr.ServiceUtils;
import org.gms.clr.ServletObjectGMS;

import com.sun.faces.renderkit.html_basic.ListboxRenderer;

/**
 * Servlet implementation class GetAllClients
 */
public class GetAllClients extends ServletObjectGMS {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllClients() {
        super();
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
		List<Client> listClients = clientDAO.getAllClients(request);
		List<HashMap<String, Object>> listToConvertToHtmlTable = new ArrayList<>();
		if (listClients != null) {
			listToConvertToHtmlTable = ServiceUtils.getListOfMapByClient(listClients);
		}
		response.getWriter().print(ServiceUtils.listToHTMLTableClients(listToConvertToHtmlTable));
	}

}
