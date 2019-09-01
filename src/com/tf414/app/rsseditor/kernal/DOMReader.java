package com.tf414.app.rsseditor.kernal;

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
	
	private void itemRead(Node node) {
		if(!node.getNodeName().equals("item")) {
    		return;
    	}
		NodeList nl = node.getChildNodes();
		for(int i=0; i<nl.getLength(); ++i) {
        	Node n = nl.item(i);
        	itemRead(n);
		}
	}
	
	private void channelRead(Node node) {
		if(!node.getNodeName().equals("channel")) {
    		return;
    	}
		NodeList nl = node.getChildNodes();
		for(int i=0; i<nl.getLength(); ++i) {
        	Node n = nl.item(i);
        	itemRead(n);
		}
	}
	
	private void rssRead(Node node) {
		if(!node.getNodeName().equals("rss")) {
    		return;
    	}
		NodeList nl = node.getChildNodes();
		for(int i=0; i<nl.getLength(); ++i) {
        	Node n = nl.item(i);
        	channelRead(n);
		}
	}
	
	public void read() throws ParserConfigurationException, IOException, SAXException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream is = HTTPReader.read("https://rsshub.app/005tv/zx/latest");
        Document doc = builder.parse(is);
        NodeList nl = doc.getChildNodes();
        for(int i=0; i<nl.getLength(); ++i) {
        	Node n = nl.item(i);
        	rssRead(n);
        }
	}
}
