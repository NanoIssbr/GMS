/**
 * 
 */
package org.gms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.gms.beans.ErrorObject;
import org.gms.beans.Product;
import org.gms.clr.ServiceUtils;

/**
 * @author hero
 *
 */
public class ProductDAOImpl implements ProductDAO {
	
	private static final String ATTR_ID_PRD = "idProduct";
	private static final String ATTR_LIB_PRD = "libelleproduct";
	private static final String ATTR_DATE_PRD = "datecreation";
	private static final String ATTR_REF_PRD = "reference";
	private static final String ATTR_PRIZE_PRD = "prize";
	private static final String ATTR_QNT_MIN_PRD = "qntmin";
	
	private static final String QUERY_ADD_PRODUCT = "INSERT INTO public.product ("+ATTR_LIB_PRD+", "+ATTR_QNT_MIN_PRD+", "+ATTR_DATE_PRD+", "+ATTR_ID_PRD+", "+ATTR_PRIZE_PRD+", "+ATTR_REF_PRD+") VALUES (?, ?, ?, ?, ?, ?);";
	private static final String QUERY_GET_PRODUCT_BY_LIBELLE = "select * from public.product where upper("+ATTR_LIB_PRD+") = upper(?) ;";
	private static final String QUERY_GET_PRODUCT_BY_ID = "select * from public.product where "+ATTR_ID_PRD+" = ? ;";
	private static final String QUERY_GET_PRODUCT_BY_NUMBER_ELEMENT = "select p.*,s.* from public.product p , public.stock s where p."+ATTR_ID_PRD+" = s.idProductStock and p."+ATTR_QNT_MIN_PRD+" > s.quantite ";
	
	private DAOFactory daoFactory;
	/**
	 * 
	 */
	public ProductDAOImpl() {
		// TODO Auto-generated constructor stub
	}
	public ProductDAOImpl(DAOFactory df) {
		this.daoFactory = df;
	}

	/* (non-Javadoc)
	 * @see org.gms.dao.ProductDAO#findProduct(java.lang.String, boolean)
	 */
	@Override
	public Product findProduct(String id, boolean isID, HttpServletRequest req) {
		//isID = true => id
		Connection cnx  = null;
		PreparedStatement pState = null;
		ResultSet rSet = null;
		Product produit = null;
		try{
			cnx = daoFactory.getConnection();
			if(isID){
				pState = initRequestPrepared( cnx, QUERY_GET_PRODUCT_BY_ID, false, id);
			}else{
				pState = initRequestPrepared( cnx, QUERY_GET_PRODUCT_BY_LIBELLE, false, id);
			}
			rSet = pState.executeQuery();
			if(rSet.next()){
				produit = mapProduct(rSet);
			}
		}catch(SQLException e){
			throw new DAOConfigurationException("Can't execute the query "+e);
		}finally {
			ServiceUtils.closeResources(cnx, pState, rSet);
		}
		return produit;
	}

	/* (non-Javadoc)
	 * @see org.gms.dao.ProductDAO#findProduct(java.lang.String, java.lang.String)
	 */
	@Override
	public Product findProduct(String libelle, String id, HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.gms.dao.ProductDAO#addProduct(org.gms.beans.Product)
	 */
	@Override
	public void addProduct(Product produit, HttpServletRequest req) {
		@SuppressWarnings("unchecked")
		List<ErrorObject> errorList = (List<ErrorObject>)req.getServletContext().getAttribute("erreurListe");
		Connection cnx  = null;
		PreparedStatement pState = null;
		Product prod = findProduct(produit.getLibelleProduct(), false, req);
		if(prod == null){
			Calendar cal = Calendar.getInstance();
			String idProduct = "prod/"+cal.get(Calendar.HOUR)+"-"+cal.get(Calendar.MINUTE)+"-"+cal.get(Calendar.YEAR)+"/"+cal.get(Calendar.SECOND);
			produit.setIdProduct(idProduct);
			produit.setDateCreation(cal.get(Calendar.DAY_OF_WEEK)+"-"+cal.get(Calendar.MONTH)+"-"+cal.get(Calendar.YEAR));
			try{
				cnx = daoFactory.getConnection();
				pState = initRequestPrepared( cnx, QUERY_ADD_PRODUCT, false, produit.getLibelleProduct(), (int)produit.getQuantiteMin(),  produit.getDateCreation(), idProduct, produit.getPrize(), produit.getReferences() );
				int status = pState.executeUpdate();
				if(status == 0){
					throw new DAOException("Can't create the product !");
				}
			}catch(SQLException e){
				throw new DAOException(e);
			}finally{
				ServiceUtils.closeResources(cnx, pState);
			}
		}else{
			errorList.add(new ErrorObject("Erreur","Produit exist dejas !"));
		}
	}
	
	public List<Product> findProductByQuantite(HttpServletRequest req){
		List<Product> listeProduct = new ArrayList<Product>();
		Connection cnx  = null;
		PreparedStatement pState = null;
		ResultSet rSet = null;
		try{
			cnx = daoFactory.getConnection();
			pState = cnx.prepareStatement( QUERY_GET_PRODUCT_BY_NUMBER_ELEMENT );
			rSet = pState.executeQuery();
			if(rSet != null){
				listeProduct = mapMultiProduct(rSet);
			}
			
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			ServiceUtils.closeResources(cnx, rSet, pState);
		}
		return listeProduct;
	}

	/* (non-Javadoc)
	 * @see org.gms.dao.ProductDAO#updateProduct(org.gms.beans.Product)
	 */
	@Override
	public void updateProduct(Product produit, HttpServletRequest req) {
		// TODO Auto-generated method stub

	}
	
	public static PreparedStatement initRequestPrepared(Connection connexion, String sql, boolean returnGeneratedKeys, Object... objets ) throws SQLException {
		PreparedStatement preparedStatement = connexion.prepareStatement( sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS );
		int nbrParam = preparedStatement.getParameterMetaData().getParameterCount();
		for ( int i = 0; i < nbrParam; i++ ) {
			System.out.println( i+" "+objets[i]);
			preparedStatement.setObject( i+1 , objets[i] );
		}
		return preparedStatement;
	}
	/**
	 * Close ResultSet's resources
	 * @param rSet
	 */
	public static void closeResources(ResultSet rSet){
		if(rSet != null){
			try{
				rSet.close();
			}catch(SQLException e){
				System.out.println("Can't close the ResultSet "+e);
			}
		}
	}
	/**
	 * Close Connection's resources
	 * @param rSet
	 * @throws SQLException 
	 */
	
	private Product mapProduct(ResultSet rSet) throws SQLException{
		Product produit = new Product();
		produit.setIdProduct(rSet.getString(ATTR_ID_PRD));
		produit.setLibelleProduct(rSet.getString(ATTR_LIB_PRD));
		produit.setDateCreation(rSet.getString(ATTR_DATE_PRD));
		produit.setReferences(rSet.getString(ATTR_REF_PRD));
		produit.setPrize(rSet.getDouble(ATTR_PRIZE_PRD));
		produit.setQuantiteMin(rSet.getInt(ATTR_QNT_MIN_PRD));
		return produit;
	}
	private List<Product> mapMultiProduct(ResultSet rSet) throws SQLException{
		List<Product> listProduct = new ArrayList<Product>();
		Product product = null;
		while(rSet.next()){
			product = new Product();
			product.setIdProduct(rSet.getString(ATTR_ID_PRD));
			product.setLibelleProduct(rSet.getString(ATTR_LIB_PRD));
			product.setDateCreation(rSet.getString(ATTR_DATE_PRD));
			product.setReferences(rSet.getString(ATTR_REF_PRD));
			product.setPrize(rSet.getDouble(ATTR_PRIZE_PRD));
			product.setQuantiteMin(rSet.getInt(ATTR_QNT_MIN_PRD));
			listProduct.add(product);
		}
		return listProduct;
	}

}
