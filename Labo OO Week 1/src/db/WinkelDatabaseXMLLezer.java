package db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import domain.DomainException;
import domain.Film;
import domain.Muziek;
import domain.Product;
import domain.Spel;
import domain.Winkel;

public class WinkelDatabaseXMLLezer extends WinkelDatabaseHandler implements WinkelDatabaseLezer {

	public WinkelDatabaseXMLLezer(String filename, Winkel winkel)
			throws DbException {
		super(filename, winkel);
	}
 
	public void lees() 
			throws DbException{
		try {
			// First create a new XMLInputFactory
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			// Setup a new eventReader
			InputStream in = new FileInputStream(getBestand());
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			// Read the XML document
			String type = null;
			String id = null;
			String title = null;

			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				if (event.isStartElement()) {
					StartElement sE = event.asStartElement();
					// If we have a item element we create a new item
					if (sE.getName().getLocalPart() == (XmlTags.PRODUCT)) {

					}
					else if (sE.getName().getLocalPart().equals(XmlTags.TYPE)) {
						event = eventReader.nextEvent();
						type = event.asCharacters().getData();
					}
					else if (sE.getName().getLocalPart().equals(XmlTags.ID)) {
						event = eventReader.nextEvent();
						id = event.asCharacters().getData();
					}
					else if (sE.getName().getLocalPart().equals(XmlTags.TITLE)) {
						event = eventReader.nextEvent();
						title = event.asCharacters().getData();
					}
				}		       
				// If we reach the end of an item element we add it to the list
				else if (event.isEndElement()) {
					EndElement endElement = event.asEndElement();
					if (endElement.getName().getLocalPart() == (XmlTags.PRODUCT)) {
						Product product = null;
						try {
							switch (type) {
							case "F":
								product = new Film(id, title);
								break;
							case "M":
								product = new Muziek(id, title);
								break;
							case "S":
								product = new Spel(id, title);
								break;
							default:
								break;
							}
							getWinkel().voegProductToe(product);
						} catch (DomainException e) {
							throw new DbException("product from file cannot be added");
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			throw new DbException("file not found");
		} catch (XMLStreamException e) {
			throw new DbException("file wrong format");
		}
	}
}
