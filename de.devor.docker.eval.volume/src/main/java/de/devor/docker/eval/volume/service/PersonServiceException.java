package de.devor.docker.eval.volume.service;

/**
 * Exception that indicates that an error occurred working with the service.
 * 
 * @author orapka
 *
 */
public class PersonServiceException extends Exception {
	
	private static final long serialVersionUID = 1987916129679769768L;

	public PersonServiceException(String message, Throwable cause) {
		super(message, cause);
	}
	
	

}
