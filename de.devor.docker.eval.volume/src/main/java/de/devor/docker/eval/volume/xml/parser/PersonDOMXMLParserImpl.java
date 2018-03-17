package de.devor.docker.eval.volume.xml.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import de.devor.docker.eval.volume.model.Person;
import de.devor.docker.eval.volume.model.PersonFactory;

/**
 * DOM implementation of the parser.
 * 
 * @author orapka
 *
 */
class PersonDOMXMLParserImpl implements PersonXMLParser {

	private static final String ELEMENT_PERSONS = "persons";
	private static final String ELEMENT_PERSON = "person";
	private static final String ATTRIBUTE_LASTNAME = "lastname";
	private static final String ATTRIBUTE_FIRSTNAME = "firstname";

	// Path of the XML file to use
	private String xmlFile;

	PersonDOMXMLParserImpl(String xmlFile) {
		this.xmlFile = xmlFile;
	}

	@Override
	public void addPerson(Person person) throws PersonXMLParserException {
		File personsXMLFile = getPersonsXMLFile();
		// If the file doesn't exist we create it and also create a new document with no persons
		Document personsDocument = null;
		if (!personsXMLFile.exists()) {
			try {
				personsXMLFile.createNewFile();
				personsDocument = getNewPersonsXMLDocument();
			} catch (IOException e) {
				throw new PersonXMLParserException("Error creating the XML file for the persons: " + e.getMessage(), e);
			}
		} else {
			personsDocument = getPersonsDocument(personsXMLFile);
		}
		// Now add the person and store the document in the file
		addPerson(personsDocument, person);
		storePersonsXMLDocument(personsXMLFile, personsDocument);
	}

	@Override
	public List<Person> getPersons() throws PersonXMLParserException {
		File personsXMLFile = getPersonsXMLFile();
		if (personsXMLFile.exists()) {
			Document personsDocument = getPersonsDocument(personsXMLFile);
			return getPersons(personsDocument);
		}

		return new ArrayList<>();
	}

	//
	// Private helpers
	//

	private File getPersonsXMLFile() {
		return new File(xmlFile);
	}

	private Document getPersonsDocument(File personsXMLFile) throws PersonXMLParserException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder documentBuilder = factory.newDocumentBuilder();
			return documentBuilder.parse(personsXMLFile);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			throw new PersonXMLParserException("Error parsing the XML file for persons: " + e.getMessage(), e);
		}
	}

	private Document getNewPersonsXMLDocument() throws PersonXMLParserException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder documentBuilder = factory.newDocumentBuilder();
			Document personsXMLDocument = documentBuilder.newDocument();
			Node personsNode = personsXMLDocument.createElement(ELEMENT_PERSONS);
			personsXMLDocument.appendChild(personsNode);
			return personsXMLDocument;
		} catch (ParserConfigurationException e) {
			throw new PersonXMLParserException("Error parsing the XML file for persons: " + e.getMessage(), e);
		}
	}

	private void storePersonsXMLDocument(File personsXMLFile, Document personsXMLDocument)
			throws PersonXMLParserException {
		// Use a Transformer for output
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.VERSION, "1.0");

			DOMSource domSource = new DOMSource(personsXMLDocument);
			FileOutputStream xmlFileOutputStream = new FileOutputStream(personsXMLFile);
			StreamResult streamResult = new StreamResult(xmlFileOutputStream);
			transformer.transform(domSource, streamResult);
		} catch (FileNotFoundException | TransformerException e) {
			throw new PersonXMLParserException("Error storing the XML document for persons.", e);
		}
	}

	private List<Person> getPersons(Document personsDocument) {
		List<Person> persons = new ArrayList<>();

		Element personsElement = personsDocument.getDocumentElement();
		NodeList personElements = personsElement.getElementsByTagName(ELEMENT_PERSON);
		for (int i = 0; i < personElements.getLength(); i++) {
			Node personNode = personElements.item(i);
			NamedNodeMap personNodeAttributes = personNode.getAttributes();
			String lastName = personNodeAttributes.getNamedItem(ATTRIBUTE_LASTNAME).getTextContent();
			String firstName = personNodeAttributes.getNamedItem(ATTRIBUTE_FIRSTNAME).getTextContent();
			Person person = PersonFactory.getPerson(lastName, firstName);
			persons.add(person);
		}

		return persons;
	}

	private void addPerson(Document personsDocument, Person person) {
		Element personsElement = personsDocument.getDocumentElement();
		Element personElement = personsDocument.createElement(ELEMENT_PERSON);
		personsElement.appendChild(personElement);
		personElement.setAttribute(ATTRIBUTE_LASTNAME, person.getLastName());
		personElement.setAttribute(ATTRIBUTE_FIRSTNAME, person.getFirstName());		
	}

}
