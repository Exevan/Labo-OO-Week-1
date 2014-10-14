package db;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import domain.Product;
import domain.state.VerwijderdState;

public class WinkelDatabaseXMLSchrijver implements WinkelDatabaseSchrijver {

	private WinkelDatabaseHandler handler;
	
	public WinkelDatabaseXMLSchrijver(WinkelDatabaseHandler handler){
		this.handler = handler;
	}

	public void schrijf() 
			throws DbException{
		// Create a XMLOutputFactory
		XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
		// Create XMLEventWriter
		XMLEventWriter eventWriter;
		try {
			eventWriter = outputFactory
					.createXMLEventWriter(new FileOutputStream(handler.getBestand()));
		} catch (FileNotFoundException | XMLStreamException e1) {
			throw new DbException("wrong file");
		}
		// Create a EventFactory
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent end = eventFactory.createDTD("\n");
		// Create and write Start Tag
		StartDocument startDocument = eventFactory.createStartDocument();
		try{
			eventWriter.add(startDocument);
			eventWriter.add(end);
			StartElement productenStartElement = eventFactory.createStartElement("","",XmlTags.PRODUCTEN);
			eventWriter.add(productenStartElement);
			eventWriter.add(end);
			// Create product open tag
			ArrayList<Product> producten = handler.getWinkel().getProducten();

			for(Product product: producten)
			{
				if(product.getStaat().getClass().equals(VerwijderdState.class))
					continue;
				eventWriter.add(eventFactory.createStartElement("","",XmlTags.PRODUCT));
				eventWriter.add(end);
				// Write the different nodes
				createNode(eventWriter, XmlTags.TYPE, product.getType());
				createNode(eventWriter, XmlTags.ID, product.getId());
				createNode(eventWriter, XmlTags.TITLE, product.getNaam());
				createNode(eventWriter, XmlTags.BASEPRICE, "" + product.getBasisprijs());
				createNode(eventWriter, XmlTags.STATE, product.getStaat().getClass().getCanonicalName());
				eventWriter.add(eventFactory.createEndElement("", "",XmlTags.PRODUCT));
				eventWriter.add(end);
			}
			eventWriter.add(eventFactory.createEndElement("", "",XmlTags.PRODUCTEN));
			eventWriter.add(eventFactory.createEndDocument());
			eventWriter.close();
		} catch(XMLStreamException e){
			throw new DbException("wrong format");
		}
	}

	private void createNode(XMLEventWriter eventWriter, String name, String value) 
			throws XMLStreamException {
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent end = eventFactory.createDTD("\n");
		XMLEvent tab = eventFactory.createDTD("\t");
		// Create Start node
		StartElement sElement = eventFactory.createStartElement("", "", name);
		eventWriter.add(tab);
		eventWriter.add(sElement);
		// Create Content
		Characters characters = eventFactory.createCharacters(value);
		eventWriter.add(characters);
		// Create End node
		EndElement eElement = eventFactory.createEndElement("", "", name);
		eventWriter.add(eElement);
		eventWriter.add(end);
	}
}
