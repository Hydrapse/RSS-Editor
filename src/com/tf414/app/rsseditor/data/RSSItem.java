package com.tf414.app.rsseditor.data;

import java.util.Date;

public class RSSItem {
	private String title;
	private String description;
	private Date dateCreated;
	private String author;
	private RSSChannel channel;
	private String link;
	private boolean hasRead;

	public RSSItem(RSSChannel channel, String title) {
		super();
		this.title = title;
	}

	public RSSItem(RSSChannel channel, String title, String description, Date dateCreated, String author, String link) {
		super();
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
		result = prime * result + ((channel == null) ? 0 : channel.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		if (channel == null) {
			if (other.channel != null)
				return false;
		} else if (!channel.equals(other.channel))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	public String getTitle() {
		if (title == null) {
			return "";
		}
		return title;
	}

	public String getDescription() {
		if (description == null) {
			return "";
		}
		return description;
	}

	public Date getDateCreated() {
		if (dateCreated == null) {
			System.out.println("Date is blank");
			long defTime = 0;
			dateCreated.setTime(defTime);
		}
		return dateCreated;
	}

	public String getAuthor() {
		if (author == null) {
			return "";
		}
		return author;
	}

	public RSSChannel getChannel() {
		if (channel == null) {
			System.out.println("channel is blank");
		}
		return channel;
	}

	public String getLink() {
		if (link == null) {
			return "";
		}
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
	
	public boolean hasRead() {
		return hasRead;
	}
	
	public void setRead(boolean hasRead) {
		this.hasRead = hasRead;
	}
	
	@Override
	public String toString() {
		return "RSSItem [title=" + title + ", description=" + description + ", dateCreated=" + dateCreated + ", author="
				+ author + ", link=" + link + ", hasRead=" + hasRead + "]";
	}
	//there is some mistake in toString
	/**
	 * test
	 */
	public void printAll() {
		System.out.println(this);
	}

}
