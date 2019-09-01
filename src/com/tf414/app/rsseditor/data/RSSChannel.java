package com.tf414.app.rsseditor.data;

import java.util.ArrayList;
import java.util.List;

import com.sun.xml.internal.ws.util.StringUtils;

public class RSSChannel {
	private String name;
	private String url;
	private String logoPath;
	private String description;
	private List<RSSItem> items;
	
	
	public RSSChannel(String name) {
		super();
		this.name = name;
		items = new ArrayList<RSSItem>();
	}

	public RSSChannel(String name, String url, String logoPath) {
		super();
		this.name = name;
		this.url = url;
		this.logoPath = logoPath;
		items = new ArrayList<RSSItem>();
	}
	
	public RSSChannel(String name, String url, String logoPath, List<RSSItem> items) {
		super();
		this.name = name;
		this.url = url;
		this.logoPath = logoPath;
		this.items = items;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RSSChannel other = (RSSChannel) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	public String getName() {
		if (name.isEmpty()||name==null) {
			return ""; 
		}
		return name;
	}
	
	public String getUrl() {
		if (url.isEmpty()||url==null) {
			return ""; 
		}
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	public String getLogoPath() {
		if (logoPath.isEmpty()||logoPath==null) {
			return ""; 
		}
		return logoPath;
	}
	
	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		if (description.isEmpty()||description==null) {
			return ""; 
		}
		return description;
	}
	
	public List<RSSItem> getItems() {
		return items;
	}
	
	public void addItem(RSSItem item) {
		items.add(item);
	}
	//test
	public void printAllChannel() {
		for (RSSItem rssItem : items) {
			rssItem.printAll();
		}
	}
}
