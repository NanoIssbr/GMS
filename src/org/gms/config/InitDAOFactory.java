/**
 * 
 */
package org.gms.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.gms.dao.DAOFactory;
/**
 * @author hero
 *
 */
public class InitDAOFactory implements ServletContextListener {

	private static final String ATTR_DAO_FACTORY = "daofactory";
	private DAOFactory daoFactory;
	/**
	 * 
	 */
	public InitDAOFactory() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();
		//instantiate a First and only factory
		this.daoFactory = DAOFactory.getInstance();
		//set the factiory in servlet context
		servletContext.setAttribute(ATTR_DAO_FACTORY, this.daoFactory);
		
	}

}
