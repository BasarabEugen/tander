package com.tander.database;/*
 * Created by BASARAB EUGEN on 24.07.2018
 */

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class XSLTTransformer {

    public void transformXMLwithXSLT() throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(new File("transform.xsl"));
        Transformer transformer = factory.newTransformer(xslt);
        Source text = new StreamSource(new File("1.xml"));
        transformer.transform(text, new StreamResult(new File("2.xml")));
        System.out.println("XML transformed");
    }
}
