/**
 * 
 */
package org.gms.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author hero
 *
 */
public class DAOFactory {
	
	private static final String FILE_PROP = "/org/gms/dao/dao.properties";
	private static final String URL_PROP = "url";
	private static final String DRIVER_PROP = "driver";
	private static final String USER_NAME_PROP = "username";
	private static final String PWD_PROP = "password";
	private String url;
	private String userName;
	private String password;
	
	public DAOFactory() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param u
	 * @param un
	 * @param pwd
	 */
	public DAOFactory(String u, String un, String pwd) {
		this.url = u;
		this.userName = un;
		this.password = pwd;
	}
	/**
	 * Get data base connection information and load the JDBC driver
	 * @return
	 * @throws DAOConfigurationException
	 */
	public static DAOFactory getInstance() throws DAOConfigurationException{
		Properties prop = new Properties();
		String url;
		String userName;
		String driver;
		String pwd;
		ClassLoader cLoader = Thread.currentThread().getContextClassLoader();
		InputStream fichierProp = cLoader.getResourceAsStream(FILE_PROP);
		if(fichierProp == null){
			throw new DAOConfigurationException("the file "+FILE_PROP+" is invalide !");
		}
		try{
			prop.load(fichierProp);
			url = prop.getProperty(URL_PROP);
			driver = prop.getProperty(DRIVER_PROP);
			userName = prop.getProperty(USER_NAME_PROP);
			pwd = prop.getProperty(PWD_PROP);
		}catch(IOException e){
			throw new DAOConfigurationException("Impossible to open the file "+FILE_PROP+e);
		}
		try{
			Class.forName(driver);
		}catch(ClassNotFoundException e){
			throw new DAOConfigurationException("Impossible to load Connection driver"+DRIVER_PROP+e);
		}
		DAOFactory instance = new DAOFactory(url,userName,pwd);
		return instance;
	}
	
	/**
	 * Set a new Connection to DB
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection( url, userName, password );
	}
	/**
	 * Get Implementation of Current DAO
	 * @return UtilisateurDao
	 */
	public UserDAO getUserDAO() {
		return new UserDAOImpl( this );
	}
	public ProductDAO getProductDAO() {
		return new ProductDAOImpl( this );
	}
	public AbonnementDAO getAbonnementDAO(){
		return new AbonnementDAOImp(this);
	}
	public StockDAO getStockDAO(){
		return new StockDAOImpl(this);
	}

}
