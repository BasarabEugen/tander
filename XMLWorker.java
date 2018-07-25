package com.tander.database;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLWorker {

    public void createXml(List<Integer> numbers) throws XMLStreamException, FileNotFoundException {
        XMLOutputFactory factory = XMLOutputFactory.newFactory();
        XMLStreamWriter writer = factory.createXMLStreamWriter(new FileOutputStream("1.xml"));

        writer.writeStartDocument();
        writer.writeProcessingInstruction("xml-stylesheet type=\"text/xsl\" href=\"transform.xsl\"");
        writer.writeStartElement("entries");
        for (Integer number : numbers) {
            writer.writeStartElement("entry");
            writer.writeStartElement("field");
            writer.writeCharacters(String.valueOf(number));
            writer.writeEndElement();
            writer.writeEndElement();
        }
        writer.writeEndElement();
        writer.writeEndDocument();
        System.out.println("XML created");
    }

    public void parseXml() throws ParserConfigurationException, SAXException, IOException {
        final List<Integer> elements = new ArrayList<>();
        DefaultHandler handler = new DefaultHandler() {
            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                String field = attributes.getValue("field");
                if (field != null && !field.isEmpty()) {
                    elements.add(Integer.parseInt(field));
                }
            }
        };
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(new File("2.xml"), handler);

        int sum = 0;
        for (Integer element : elements) {
            sum += element;
        }
        System.out.println("Sum of numbers = " + sum);
    }
}
