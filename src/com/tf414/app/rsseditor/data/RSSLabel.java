package com.tf414.app.rsseditor.data;

import java.util.ArrayList;
import java.util.List;

public class RSSLabel {
	private int id;
	private String name;
	private List<RSSChannel> channelList;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public RSSLabel() {
		channelList = new ArrayList<RSSChannel>();
	}
	
	public RSSLabel(String name) {
		this.name = name;
		channelList = new ArrayList<RSSChannel>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<RSSChannel> getChannelList() {
		return channelList;
	}
	public void setChannelList(List<RSSChannel> channelList) {
		this.channelList = channelList;
	}
	
	public void addChannel(RSSChannel channel) {
		channelList.add(channel);
	}
	
	public boolean deleteChannel(RSSChannel channel) {
		if(channelList.remove(channel))
			return true;
		else 
			return false;
	}
	
	public boolean contains(RSSChannel channel) {
		for(RSSChannel ch: channelList) {
			if(channel.getLink().equals(ch.getLink()))
				return true;
		}
		return false;
	}
}
