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
import domain.state.ProductState;

public class WinkelDatabaseXMLLezer implements WinkelDatabaseLezer {

	private WinkelDatabaseHandler handler;
	
	public WinkelDatabaseXMLLezer(WinkelDatabaseHandler handler) {
		this.handler = handler;
	}
 
	public void lees() 
			throws DbException{
		try {
			// First create a new XMLInputFactory
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			// Setup a new eventReader
			InputStream in = new FileInputStream(handler.getBestand());
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			// Read the XML document
			String type = null;
			String id = null;
			String title = null;
			int basisprijs = 0;
			Object staat = null;

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
					else if (sE.getName().getLocalPart().equals(XmlTags.BASEPRICE)) {
						event = eventReader.nextEvent();
						basisprijs = Integer.parseInt(event.asCharacters().getData());
					}
					else if(sE.getName().getLocalPart().equals(XmlTags.STATE)) {
						event = eventReader.nextEvent();
						try {
							String stateName = event.asCharacters().getData();
							Class<?> stateClass = Class.forName(stateName);
							staat = stateClass.newInstance();
						} catch (Exception e) {
							throw new DbException("Product could not be created");
						}
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
								product = new Film(id, title, basisprijs, (ProductState) staat);
								break;
							case "M":
								product = new Muziek(id, title, basisprijs, (ProductState) staat);
								break;
							case "S":
								product = new Spel(id, title, basisprijs, (ProductState) staat);
								break;
							default:
								break;
							}
							handler.getWinkel().voegProductToe(product);
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
