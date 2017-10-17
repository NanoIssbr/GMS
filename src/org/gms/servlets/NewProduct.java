package org.gms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gms.beans.ErrorObject;
import org.gms.beans.Product;
import org.gms.clr.ServiceUtils;
import org.gms.clr.ServletObjectGMS;
import org.gms.dao.DAOFactory;
import org.gms.dao.ProductDAO;

/**
 * Servlet implementation class NewProduct
 */
public class NewProduct extends ServletObjectGMS {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    /* (non-Javadoc)
     * @see javax.servlet.GenericServlet#init()
     */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*Product prod = new Product();
		prod = ServiceUtils.getProductForm(request);
		System.out.println("im in the servlet = "+prod.getLibelleProduct());*/
		
		Product produit = ServiceUtils.getProductForm(request);
		prodDAO.addProduct(produit, request);
		@SuppressWarnings("unchecked")
		List<ErrorObject> erreurList = (List<ErrorObject>) request.getServletContext().getAttribute("erreurListe");
		if(erreurList.isEmpty()){
			System.out.println("product "+produit.getLibelleProduct()+" id created");
			//this.forwardLocal(URI_HOME_PAGE, request, response);
			PrintWriter out = response.getWriter();
			out.println("Produit Sauvgarder avec succes !");
		}else{
			System.out.println(erreurList.toString());
			PrintWriter out = response.getWriter();
			//out.println("Produit Sauvgarder avec succes !");
			out.print(ServiceUtils.getErreurs(erreurList));
		}
		erreurList.clear();
		
	}
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.forwardLocal(URI_HOME_PAGE, req, resp);
	}

}
