package de.devor.docker.eval.volume.model;

/**
 * mplementation of person.
 * @author orapka
 *
 */
class PersonImpl implements Person {

	private String lastName;
	private String firstName;
	
	PersonImpl(String lastName, String firstName) {
		this.lastName = lastName;
		this.firstName = firstName;
	}
	
	/* (non-Javadoc)
	 * @see de.devor.docker.eval.volume.model.Person#getLastName()
	 */
	@Override
	public String getLastName() {
		return lastName;
	}

	/* (non-Javadoc)
	 * @see de.devor.docker.eval.volume.model.Person#getFirstName()
	 */
	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public String toString() {
		return "Person [lastName=" + lastName + ", firstName=" + firstName + "]";
	}

}
