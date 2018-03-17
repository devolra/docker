package de.devor.docker.eval.volume.service;

import java.util.List;

import de.devor.docker.eval.volume.model.Person;
import de.devor.docker.eval.volume.xml.parser.PersonXMLParser;
import de.devor.docker.eval.volume.xml.parser.PersonXMLParserException;
import de.devor.docker.eval.volume.xml.parser.PersonXMLParserFactory;

class PersonServiceXMLFileStorageImpl implements PersonService {

	private PersonXMLParser xmlParser;

	PersonServiceXMLFileStorageImpl(String fileName) {
		xmlParser = PersonXMLParserFactory.getXMLParser(fileName);
	}

	@Override
	public List<Person> getPersons() throws PersonServiceException {
		try {
			return xmlParser.getPersons();
		} catch (PersonXMLParserException e) {
			throw new PersonServiceException("Error reading the persons.", e);
		}
	}

	@Override
	public void addPerson(Person person) throws PersonServiceException {
		try {
			xmlParser.addPerson(person);
		} catch (PersonXMLParserException e) {
			throw new PersonServiceException("Error add the person " + person.toString() + ".", e);
		}
	}

}
