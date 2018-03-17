package de.devor.docker.eval.volume.service;

import java.util.List;

import de.devor.docker.eval.volume.model.Person;

/**
 * Service to work with persons.
 * 
 * @author orapka
 *
 */
public interface PersonService {

	public List<Person> getPersons() throws PersonServiceException;

	public void addPerson(Person person) throws PersonServiceException;
}
