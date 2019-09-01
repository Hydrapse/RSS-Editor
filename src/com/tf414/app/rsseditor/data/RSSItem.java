package com.tf414.app.rsseditor.data;

import java.util.Date;

public class RSSItem {
	private long uID;
	private String title;
	private String description;
	private Date dateCreated;
	private String author;
	private RSSChannel channel;
	private String link;
	
	
	public RSSItem(long uID, String title) {
		super();
		this.uID = uID;
		this.title = title;
	}

	public RSSItem(long uID, String title, String description, Date dateCreated, String author, RSSChannel channel,
			String link) {
		super();
		this.uID = uID;
		this.title = title;
		this.description = description;
		this.dateCreated = dateCreated;
		this.author = author;
		this.channel = channel;
		this.link = link;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		RSSItem other = (RSSItem) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (uID != other.uID)
			return false;
		return true;
	}

	public long getuID() {
		return uID;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Date getDateCreated() {
		return dateCreated;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public RSSChannel getChannel() {
		return channel;
	}
	
	public String getLink() {
		return link;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public void setChannel(RSSChannel channel) {
		this.channel = channel;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	
}
