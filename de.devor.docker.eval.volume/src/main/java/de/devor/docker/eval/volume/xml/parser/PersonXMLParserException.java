package de.devor.docker.eval.volume.xml.parser;

/**
 * Exception that indicates that there was an error parsing the XML file for person data.
 * 
 * @author orapka
 *
 */
public class PersonXMLParserException extends Exception {

	private static final long serialVersionUID = -8394680506109638612L;

	public PersonXMLParserException(String message, Throwable cause) {
		super(message, cause);
	}

}
