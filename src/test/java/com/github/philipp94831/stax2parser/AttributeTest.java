package com.github.philipp94831.stax2parser;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;

import org.junit.Test;


public class AttributeTest {

	private static final String XML = "<foo bar=\"1234\" baz=\"lorem\" />";
	
	@Test
	public void test() throws XMLStreamException {
		MyHandler handler = new MyHandler();
		Stax2Parser parser = new Stax2Parser(handler);
		parser.parse(new ByteArrayInputStream(XML.getBytes(StandardCharsets.UTF_8)));
		assertEquals(2, handler.attrCount);
	}
	
	private class MyHandler extends DefaultHandler {
		
		private int attrCount = 0;
		
		@Override
		public void attribute(QName qName, String value) {
			if(qName.getLocalPart().equals("bar")) {
				assertEquals("1234", value);
				attrCount++;
			}
			if(qName.getLocalPart().equals("baz")) {
				assertEquals("lorem", value);
				attrCount++;
			}
		}
	}

}
