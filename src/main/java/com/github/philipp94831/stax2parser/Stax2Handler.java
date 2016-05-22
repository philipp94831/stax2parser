package com.github.philipp94831.stax2parser;

import javax.xml.namespace.QName;

public interface Stax2Handler {

	void characters(String text);

	void endDocument();

	void endElement(QName qname);

	void startDocument();

	void startElement(QName qname);

	void attribute(QName qName, String value);

	void comment();

	void cdata();

	void dtd();

	void entityDeclaration();

	void namespace();

	void notationDeclaration();

	void processingInstruction();

	void space();
}
