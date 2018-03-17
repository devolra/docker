package de.devor.docker.eval.volume.model;

/**
 * Factotry for persons.
 * 
 * @author orapka
 *
 */
public class PersonFactory {

	/**
	 * Creates a person.
	 * 
	 * @param lastName
	 *            The lastname.
	 * @param firstName
	 *            The firstname.
	 * @return The person.
	 */
	public static Person getPerson(String lastName, String firstName) {
		return new PersonImpl(lastName, firstName);
	}

}
