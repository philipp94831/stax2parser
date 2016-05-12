package com.github.philipp94831.stax2parser;

public interface Stax2Handler {

	void characters(String text);

	void endDocument();

	void endElement(String localPart, String namespaceURI);

	void startDocument();

	void startElement(String localPart, String namespaceURI);
}
