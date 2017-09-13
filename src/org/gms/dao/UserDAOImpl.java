/**
 * 
 */
package org.gms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.gms.beans.Users;

/**
 * @author hero
 *
 */
public class UserDAOImpl implements UserDAO {

	private DAOFactory daoFactory;
	private static final String SQL_QUERY_MAIL_PWD_2 = "select * from getUserLogin(?,?)";
	private static final String SQL_QUERY_UPDATE = "select public.updateUser(?, ?);";
	public UserDAOImpl(DAOFactory d){
		this.daoFactory = d;
	}
	@Override
	public void update(String userName , String mdp){
		
	}
	@Override
	public Users find(String mail, String pwd) throws DAOException {
		Connection cnx = null;
		PreparedStatement pState = null;
		ResultSet rSet = null;
		Users user = null;
		try{
			cnx = daoFactory.getConnection();
			pState = initRequestPrepared( cnx, SQL_QUERY_MAIL_PWD_2, false, mail, pwd );
			rSet = pState.executeQuery();
			if(rSet.next()){
				user = mapUser(rSet);
			}
		}catch(SQLException e){
			throw new DAOConfigurationException("Can't execute the query "+e);
		}finally {
			closeResources(cnx);
			closeResources(rSet);
			closeResources(pState);
		}
		return user;
	}
	
	/**
	 * Initial the Prepared statement with the SQL Request, Connection and Indefined Argument Objects
	 * @param connexion
	 * @param sql
	 * @param returnGeneratedKeys
	 * @param objets
	 * @return
	 * @throws SQLException
	 */
	public static PreparedStatement initRequestPrepared(Connection connexion, String sql, boolean returnGeneratedKeys, Object... objets ) throws SQLException {
		PreparedStatement preparedStatement = connexion.prepareStatement( sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS );
		int nbrParam = preparedStatement.getParameterMetaData().getParameterCount();
		for ( int i = 0; i < nbrParam; i++ ) {
			System.out.println( objets.length+" "+(String)objets[i]);
			preparedStatement.setObject( i+1 , objets[i] );
		}
		return preparedStatement;
	}
	/**
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	private static Users mapUser( ResultSet resultSet ) throws SQLException {
		Users user = null;
		if(resultSet.getString("id") != null){
			user = new Users();
			user.setId( resultSet.getString("id"));
			user.setUserName( resultSet.getString("userName"));
			user.setDateLastCnx( resultSet.getDate("dateLastCnx"));
		}
		return user;
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
	 */
	public static void closeResources(Connection cnx){
		if(cnx != null){
			try{
				cnx.close();
			}catch(SQLException e){
				System.out.println("Can't close the ResultSet "+e);
			}
		}
	}
	/**
	 * Close Statement's resources
	 * @param rSet
	 */
	public static void closeResources(Statement state){
		if(state != null){
			try{
				state.close();
			}catch(SQLException e){
				System.out.println("Can't close the ResultSet "+e);
			}
		}
	}



}
