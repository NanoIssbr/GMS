package org.gms.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gms.beans.Client;
import org.gms.clr.ServiceUtils;
import org.gms.clr.ServletObjectGMS;

/**
 * Servlet implementation class NewClient
 */
@WebServlet("/NewClient")
public class NewClient extends ServletObjectGMS {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewClient() {
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
		
		Client client = ServiceUtils.getClientForm(request);
		clientDAO.addClient(client, request);
		String message = (String)request.getAttribute("message");
		PrintWriter out = response.getWriter();
		out.print(message);
	}

}
