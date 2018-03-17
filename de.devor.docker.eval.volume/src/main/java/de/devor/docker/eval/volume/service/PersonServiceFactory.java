package de.devor.docker.eval.volume.service;

/**
 * Factory for the service.
 * 
 * @author orapka
 *
 */
public class PersonServiceFactory {

	private static PersonService service;

	public static PersonService getService(String fileName) {
		if (service == null) {
			service = new PersonServiceXMLFileStorageImpl(fileName);
		}
		return service;
	}

}
