package de.devor.docker.eval.volume.xml.parser;

import java.util.List;

import de.devor.docker.eval.volume.model.Person;

/**
 * Can parse the XML for person data.
 * 
 * @author orapka
 *
 */
public interface PersonXMLParser {

	/**
	 * Adds a new person to the XML file.
	 * 
	 * If the file does not exist it is created.
	 * 
	 * @param person
	 *            The person.
	 * @throws PersonXMLParserException
	 *             If an error occurs parsing the XML file.
	 */
	public void addPerson(Person person) throws PersonXMLParserException;

	/**
	 * Returns all persons that are contained in the XML file.
	 * 
	 * If the file does not exist an empty list is returned.
	 * 
	 * @return The persons.
	 * @throws PersonXMLParserException
	 *             If an error occurs parsing the XML file.
	 */
	public List<Person> getPersons() throws PersonXMLParserException;

}
