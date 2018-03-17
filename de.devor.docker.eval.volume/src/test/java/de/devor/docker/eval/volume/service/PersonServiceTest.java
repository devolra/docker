package de.devor.docker.eval.volume.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.devor.docker.eval.volume.model.Person;
import de.devor.docker.eval.volume.model.PersonFactory;

/**
 * Tests for the service.
 * 
 * @author orapka
 *
 */
public class PersonServiceTest {

	private String fileName;
	
	@Before
	public void before() {
		// For running the test standalone we have to delete the file.
		// Instead the test will fail, reading an existing file.
		String fileDir = getClass().getClassLoader().getResource(".").getFile();
		fileName = fileDir + "persons.xml";
		File file = new File(fileName);
		if (file.exists()) {
			file.delete();
		}
	}
	
	@Test
	public void testService() throws Exception {
		PersonService service = PersonServiceFactory.getService(fileName);
		assertNotNull(service);
		
		List<Person> persons = service.getPersons();
		assertNotNull(persons);
		assertEquals(0, persons.size());
		
		service.addPerson(PersonFactory.getPerson("Mustermann1", "Max1"));
		
		persons = service.getPersons();
		assertNotNull(persons);
		assertEquals(1, persons.size());
		
		Person person1 = persons.get(0);
		assertEquals("Mustermann1", person1.getLastName());
		assertEquals("Max1", person1.getFirstName());
		
		service.addPerson(PersonFactory.getPerson("Mustermann2", "Max2"));
		
		persons = service.getPersons();
		assertNotNull(persons);
		assertEquals(2, persons.size());
		
		Person person2 = persons.get(1);
		assertEquals("Mustermann2", person2.getLastName());
		assertEquals("Max2", person2.getFirstName());
	}
	
}
