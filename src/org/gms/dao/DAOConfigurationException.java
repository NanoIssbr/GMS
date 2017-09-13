/**
 * 
 */
package org.gms.dao;

/**
 * @author hero
 *
 */
public class DAOConfigurationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3209798156219551802L;
	/**
	 * @param message
	 */
	public DAOConfigurationException(String message) {
		super(message);
	}
	/**
	 * @param message
	 * @param cause
	 */
	public DAOConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
	/**
	 * @param cause
	 */
	public DAOConfigurationException(Throwable cause) { 
		super(cause); 
	}

}
