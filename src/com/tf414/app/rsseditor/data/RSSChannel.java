package com.tf414.app.rsseditor.data;

import java.util.ArrayList;
import java.util.List;

public class RSSChannel {
	private long uID;
	private String name;
	private String url;
	private String logoPath;
	private List<RSSItem> items;
	
	
	public RSSChannel(long uID, String name) {
		super();
		this.uID = uID;
		this.name = name;
		items = new ArrayList<RSSItem>();
	}

	public RSSChannel(long uID, String name, String url, String logoPath) {
		super();
		this.uID = uID;
		this.name = name;
		this.url = url;
		this.logoPath = logoPath;
		items = new ArrayList<RSSItem>();
	}
	
	public RSSChannel(long uID, String name, String url, String logoPath, List<RSSItem> items) {
		super();
		this.uID = uID;
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
		result = prime * result + (int) (uID ^ (uID >>> 32));
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
		if (uID != other.uID)
			return false;
		return true;
	}

	public long getuID() {
		return uID;
	}
	
	public String getName() {
		return name;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	public String getLogoPath() {
		return logoPath;
	}
	
	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
	
	public List<RSSItem> getItems() {
		return items;
	}
	
	public void addItem(RSSItem item) {
		items.add(item);
	}
}
