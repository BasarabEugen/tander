package com.tander.database;
/*
 * Created by BASARAB EUGEN on 22.07.2018
 */

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

public class MainDB {
    public static void main(String[] args) throws IOException, XMLStreamException, TransformerException, SAXException, ParserConfigurationException {

        DBWorker dbworker = new DBWorker();
        dbworker.setUser("root");
        dbworker.setPassword("roott");

        dbworker.insertData(10000);

        List<Integer> numbers = dbworker.readData();

        System.out.println("Numbers: " + numbers);

        XMLWorker xmlWorker = new XMLWorker();
        xmlWorker.createXml(numbers);

        XSLTTransformer xslt = new XSLTTransformer();
        xslt.transformXMLwithXSLT();

        xmlWorker.parseXml();
    }
}
