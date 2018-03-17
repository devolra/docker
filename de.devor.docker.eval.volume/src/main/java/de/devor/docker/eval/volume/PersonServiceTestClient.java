package de.devor.docker.eval.volume;

import java.io.File;
import java.time.ZonedDateTime;
import java.util.List;

import de.devor.docker.eval.volume.model.Person;
import de.devor.docker.eval.volume.model.PersonFactory;
import de.devor.docker.eval.volume.service.PersonService;
import de.devor.docker.eval.volume.service.PersonServiceException;
import de.devor.docker.eval.volume.service.PersonServiceFactory;

/**
 * Main class to test the service.
 * 
 * Requires the following arguments:
 * <ol>
 * <li>The full path to the XML file where the persons should be stored.</li>
 * <li>Modus to use (read, add)</li>
 * </ol>
 * 
 * @author orapka
 *
 */
public class PersonServiceTestClient {

	private static final String MODUS_READ = "read";
	private static final String MODUS_ADD = "add";

	private static final int LOOPS = 100;

	public static void main(String[] args) {
		if (args.length == 2) {
			String filePath = args[0];
			String modus = args[1];
			if (!(MODUS_ADD.equals(modus) || MODUS_READ.equals(modus))) {
				System.err.println("Usage: {file path}, {modus (read or add)}");
				return;
			}
			System.out.println("Using file: " + new File(filePath).getAbsolutePath() + ", modus: " + modus);

			PersonServiceTestClient testClient = new PersonServiceTestClient();
			if (MODUS_READ.equals(modus)) {
				try {
					testClient.logPersons(filePath);
				} catch (PersonServiceException | InterruptedException e) {
					System.err.println("Error reading the persons:");
					e.printStackTrace();
				}
			} else if (MODUS_ADD.equals(modus)) {
				try {
					testClient.addPersons(filePath);
				} catch (PersonServiceException | InterruptedException e) {
					System.err.println("Error adding persons:");
					e.printStackTrace();
				}
			}
			return;
		}

		System.err.println("Usage: {file path}, {modus (read or add)}");
	}

	private void logPersons(String filePath) throws PersonServiceException, InterruptedException {
		String lineSeparator = System.lineSeparator();

		PersonService service = PersonServiceFactory.getService(filePath);

		for (int i = 0; i < LOOPS; i++) {
			List<Person> persons = service.getPersons();
			StringBuilder personsBuilder = new StringBuilder("------------------------------------")
					.append(lineSeparator);
			personsBuilder.append("Persons (").append(getDateTime()).append("):").append(lineSeparator);
			personsBuilder.append("------------------------------------").append(lineSeparator);
			if (persons.size() == 0) {
				personsBuilder.append("No persons.");
				System.out.println(personsBuilder.toString());
			} else {
				for (Person person : persons) {
					personsBuilder.append("Person: ").append("Lastname: ").append(person.getLastName())
							.append("; Firstname: ").append(person.getFirstName()).append(lineSeparator);
				}
				System.out.println(personsBuilder.toString());
			}
			Thread.sleep(1000);
		}
	}

	private void addPersons(String filePath) throws PersonServiceException, InterruptedException {
		String lastNamePrefix = "Mustermann";
		String firstNamePrefix = "Max";

		PersonService service = PersonServiceFactory.getService(filePath);
		for (int i = 0; i < LOOPS; i++) {
			Person person = PersonFactory.getPerson(lastNamePrefix + (i + 1), firstNamePrefix + (i + 1));
			StringBuilder personBuilder = new StringBuilder("Adding Person (").append(getDateTime()).append("): ")
					.append(person.getLastName()).append(", ").append(person.getFirstName());
			System.out.println(personBuilder.toString());
			service.addPerson(person);
			Thread.sleep(1000);
		}
	}

	private String getDateTime() {
		ZonedDateTime dateTime = ZonedDateTime.now();

		String day = appendZeroToDateTimeValue(dateTime.getDayOfMonth());
		String month = appendZeroToDateTimeValue(dateTime.getMonthValue());
		String year = appendZeroToDateTimeValue(dateTime.getYear());

		String hour = appendZeroToDateTimeValue(dateTime.getHour());
		String minute = appendZeroToDateTimeValue(dateTime.getMinute());
		String second = appendZeroToDateTimeValue(dateTime.getSecond());

		StringBuilder dateTimeBuilder = new StringBuilder(
				day + "." + month + "." + year + " " + hour + ":" + minute + ":" + second);

		return dateTimeBuilder.toString();
	}

	private String appendZeroToDateTimeValue(int dateTimeValue) {
		StringBuilder valueBuilder = new StringBuilder();
		if (dateTimeValue < 10) {
			valueBuilder.append("0");
		}
		valueBuilder.append(dateTimeValue);
		return valueBuilder.toString();
	}

}
