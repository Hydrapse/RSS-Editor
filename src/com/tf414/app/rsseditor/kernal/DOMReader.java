package com.tf414.app.rsseditor.kernal;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public final class DOMReader {
	public void read() throws ParserConfigurationException, IOException, SAXException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream is = HTTPReader.read("https://rsshub.app/005tv/zx/latest");
        Document doc = builder.parse(is);
        NodeList nl = doc.getChildNodes();
        for(int i=0; i<nl.getLength(); ++i) {
        	Node n = nl.item(i);
        	if(!n.getNodeName().equals("rss")) {
        		continue;
        	}
        }
	}
}
