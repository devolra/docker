package de.devor.docker.eval.volume.xml.parser;

/**
 * Factory for the XML parser.
 * @author orapka
 *
 */
public class PersonXMLParserFactory {

	public static PersonXMLParser getXMLParser(String xmlFile) {
		return new PersonDOMXMLParserImpl(xmlFile);
	}
	
}
