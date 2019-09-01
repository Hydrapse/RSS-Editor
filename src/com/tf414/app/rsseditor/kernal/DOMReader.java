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
	public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
		DOMReader test = new DOMReader();
		test.read();
	}
	
	
	private static void itemRead(Node node,RSSChannel channel) {
		String title = null;
		String description = null;
		Date dateCreated = null;
		String author = null;
		RSSChannel pchannel = channel;
		String link = null;
		if(!node.getNodeName().equals("item")) {
    		return;
    	}
		NodeList nl = node.getChildNodes();
		for(int i=0; i<nl.getLength(); ++i) {
        	Node n = nl.item(i);
        	if(n.getNodeName().contentEquals("title")) {
//        		System.out.print(n.getTextContent());
        		title = n.getTextContent();
        	}
        	else if(n.getNodeName().contentEquals("description")) {
        		description = n.getTextContent();
        	}
        	else if(n.getNodeName().contentEquals("pubDate")) {
        		String date = n.getTextContent();
        		dateCreated = TimeConv.strToDate(date);
        	}
        	else if(n.getNodeName().contentEquals("author")) {
        		author = n.getTextContent();
        	}
        	else if(n.getNodeName().contentEquals("link")) {
        		link = n.getTextContent();
        	}
		}
		RSSItem item = new RSSItem(title, description, dateCreated, author, pchannel, link);
//		System.out.println(item.getTitle());
//		item.printAll();
		channel.addItem(item);
	}
	
	private static RSSChannel channelRead(Node node) {
		if(!node.getNodeName().equals("channel")) {
    		return null;
    	}
		NodeList nl = node.getChildNodes();
		String name = null;
		for(int i=0; i<nl.getLength(); ++i) {
        	Node n = nl.item(i);
        	if(n.getNodeName().contentEquals("title")) {
        		System.out.print(n.getTextContent());
        		name = n.getTextContent();
        		break;
        	}
		}
		RSSChannel channel = new RSSChannel(name);
		for(int i=0; i<nl.getLength(); ++i) {
        	Node n = nl.item(i);
        	if(n.getNodeName().contentEquals("link")) {
        		channel.setUrl(n.getTextContent());
        	}
        	else if(n.getNodeName().contentEquals("description")) {
        		channel.setDescription(n.getTextContent());
        	}
        	else if(n.getNodeName().contentEquals("image")) {
        		channel.setLogoPath(n.getTextContent());
        	}
        	itemRead(n,channel);
		}
		return channel;
	}
	
	private static void rssRead(Node node) {
		if(!node.getNodeName().equals("rss")) {
    		return;
    	}
		NodeList nl = node.getChildNodes();
		for(int i=0; i<nl.getLength(); ++i) {
//			List<Channel> channelList = new ArrayList<Channel>();
        	Node n = nl.item(i);
		}
	}
	
	public static void read() throws ParserConfigurationException, IOException, SAXException{
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
