package com.github.philipp94831.stax2parser;

import java.io.InputStream;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;

import org.codehaus.stax2.XMLInputFactory2;
import org.codehaus.stax2.XMLStreamReader2;

public class Stax2Parser {

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
				handler.startElement(xmlStreamReader.getName());
				int c = xmlStreamReader.getAttributeCount();
				for(int i = 0; i < c; i++) {
					handler.attribute(xmlStreamReader.getAttributeName(i), xmlStreamReader.getAttributeValue(i));
				}
				break;
			case XMLStreamConstants.CHARACTERS:
				handler.characters(xmlStreamReader.getText());
				break;
			case XMLStreamConstants.END_ELEMENT:
				handler.endElement(xmlStreamReader.getName());
				break;
			case XMLStreamConstants.ATTRIBUTE:
				break;
			case XMLStreamConstants.CDATA:
				handler.cdata();
				break;
			case XMLStreamConstants.COMMENT:
				handler.comment();
				break;
			case XMLStreamConstants.DTD:
				handler.dtd();
				break;
			case XMLStreamConstants.ENTITY_DECLARATION:
				handler.entityDeclaration();
				break;
			case XMLStreamConstants.NAMESPACE:
				handler.namespace();
				break;
			case XMLStreamConstants.NOTATION_DECLARATION:
				handler.notationDeclaration();
				break;
			case XMLStreamConstants.PROCESSING_INSTRUCTION:
				handler.processingInstruction();
				break;
			case XMLStreamConstants.SPACE:
				handler.space();
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
