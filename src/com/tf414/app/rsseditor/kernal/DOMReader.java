package com.tf414.app.rsseditor.kernal;

import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.tf414.app.rsseditor.data.RSSChannel;
import com.tf414.app.rsseditor.data.RSSItem;
import com.tf414.app.rsseditor.util.TimeConv;

public final class DOMReader {
//	public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
//		DOMReader test = new DOMReader();
//		test.read();
//	}
	
	
	private static RSSItem itemRead(Node node,RSSChannel channel) {
		String title = null;
		String description = null;
		Date dateCreated = null;
		String author = null;
		String link = null;
		if(!node.getNodeName().equals("item")) {
    		return null;
    	}
		NodeList nl = node.getChildNodes();
		for(int i=0; i<nl.getLength(); ++i) {
        	Node n = nl.item(i);
        	if(n.getNodeName().equals("title")) {
//        		System.out.print(n.getTextContent());
        		title = n.getTextContent();
        	}
        	else if(n.getNodeName().equals("description")) {
        		description = n.getTextContent();
        	}
        	else if(n.getNodeName().equals("pubDate")) {
        		String date = n.getTextContent();
        		dateCreated = TimeConv.strToDate(date);
        	}
        	else if(n.getNodeName().equals("author")) {
        		author = n.getTextContent();
        	}
        	else if(n.getNodeName().equals("link")) {
        		link = n.getTextContent();
        	}
		}
		
		RSSItem item = new RSSItem(channel,title,description,dateCreated,author,link);
		return item;
	}
	
	private static RSSChannel channelRead(Node node) {
		if(!node.getNodeName().equals("channel")) {
    		return null;
    	}
		NodeList nl = node.getChildNodes();
		String name = null;
		for(int i=0; i<nl.getLength(); ++i) {
        	Node n = nl.item(i);
        	if(n.getNodeName().equals("title")) {
        		name = n.getTextContent();
        		break;
        	}
		}
		RSSChannel channel = new RSSChannel(name);
		for(int i=0; i<nl.getLength(); ++i) {
        	Node n = nl.item(i);
        	if(n.getNodeName().equals("link")) {
        		channel.setLink(n.getTextContent());
        	}
        	else if(n.getNodeName().equals("description")) {
        		channel.setDescription(n.getTextContent());
        	}
        	else if(n.getNodeName().equals("image")) {
        		channel.setLogoPath(n.getTextContent());
        	}
        	else if(n.getNodeName().equals("generator")) {
        		channel.setGenerator(n.getTextContent());
        	}
        	else if(n.getNodeName().equals("webMaster")) {
        		channel.setWebMaster(n.getTextContent());
        	}
        	else if(n.getNodeName().equals("lastBuildDate")) {
        		channel.setLastBuildDate(TimeConv.strToDate(n.getTextContent()));
        	}
        	else if(n.getNodeName().equals("language")) {
        		channel.setLanguage(n.getTextContent());
        	}
        	channel.addItem(itemRead(n,channel));
		}
		return channel;
	}
	
	private static RSSChannel rssRead(Node node) {
		if(!node.getNodeName().equals("rss")) {
    		return null;
    	}
		System.out.println(node.getNodeName());
		NodeList nl = node.getChildNodes();
		for(int i=0; i<nl.getLength(); ++i) {
			Node n = nl.item(i);
			RSSChannel c = channelRead(n);
			if(c!=null) {
				return c;
			}
		}
		return null;
	}
	
	public String deSigle(String str) {
		String fin;
		fin = str.replaceAll("'", "\'\'");
		return fin;
	}
	
	public static RSSChannel read(String url) throws ParserConfigurationException, IOException, SAXException{ 
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream is = HTTPReader.read(url);
        Document doc = builder.parse(is);
        NodeList nl = doc.getChildNodes();
        for(int i=0; i<nl.getLength(); ++i) {
        	Node n = nl.item(i);
        	RSSChannel c = rssRead(n);
        	if(c != null) {
        		return c;
        	}
        }
        return null;
	}
}
