package com.github.philipp94831.stax2parser;

import java.io.InputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;

import org.codehaus.stax2.XMLInputFactory2;
import org.codehaus.stax2.XMLStreamReader2;

public abstract class Stax2Parser {

	private final Stax2Handler handler;
	private XMLStreamReader2 xmlStreamReader;

	public Stax2Parser(final Stax2Handler handler) {
		this.handler = handler;
	}

	private void parse() throws XMLStreamException {
		handler.startDocument();
		while (xmlStreamReader.hasNext()) {
			final int eventType = xmlStreamReader.next();
			switch (eventType) {
			case XMLStreamConstants.START_ELEMENT:
				handler.startElement(xmlStreamReader.getName().getLocalPart(),
						xmlStreamReader.getName().getNamespaceURI());
				break;
			case XMLStreamConstants.CHARACTERS:
				handler.characters(xmlStreamReader.getText());
				break;
			case XMLStreamConstants.END_ELEMENT:
				handler.endElement(xmlStreamReader.getName().getLocalPart(),
						xmlStreamReader.getName().getNamespaceURI());
				break;
			case XMLStreamConstants.ATTRIBUTE:
				// TODO
				break;
			case XMLStreamConstants.CDATA:
				// TODO
				break;
			case XMLStreamConstants.COMMENT:
				// TODO
				break;
			case XMLStreamConstants.DTD:
				// TODO
				break;
			case XMLStreamConstants.ENTITY_DECLARATION:
				// TODO
				break;
			case XMLStreamConstants.NAMESPACE:
				// TODO
				break;
			case XMLStreamConstants.NOTATION_DECLARATION:
				// TODO
				break;
			case XMLStreamConstants.PROCESSING_INSTRUCTION:
				// TODO
				break;
			case XMLStreamConstants.SPACE:
				// TODO
				break;
			default:
				break;
			}
		}
		handler.endDocument();
	}

	public void parse(final InputStream in) throws XMLStreamException {
		final XMLInputFactory2 xmlInputFactory = (XMLInputFactory2) XMLInputFactory.newFactory();
		xmlStreamReader = (XMLStreamReader2) xmlInputFactory.createXMLStreamReader(in);
		parse();
	}
}
