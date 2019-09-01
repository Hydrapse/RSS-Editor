package com.tf414.app.rsseditor.data;

import java.util.Date;

public class RSSItem {
	private String title;
	private String description;
	private Date dateCreated;
	private String author;
	private RSSChannel channel;
	private String link;
	
	
	public RSSItem(String title) {
		super();
		this.title = title;
	}

	public RSSItem(String title, String description, Date dateCreated, String author, RSSChannel channel,
			String link) {
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
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	public String getTitle() {
		if (title.isEmpty()||title==null) {
			return ""; 
		}
		return title;
	}
	
	public String getDescription() {
		if (description.isEmpty()||description==null) {
			return ""; 
		}
		return description;
	}
	
	public Date getDateCreated() {
		if (dateCreated==null) {
			System.out.println("Date is blank");
			long defTime = 0;
			dateCreated.setTime(defTime);
		}
		return dateCreated;
	}
	
	public String getAuthor() {
		if (author.isEmpty()||author==null) {
			return ""; 
		}
		return author;
	}
	
	public RSSChannel getChannel() {
		if (channel==null) {
			System.out.println("channel is blank"); 
		}
		return channel;
	}
	
	public String getLink() {
		if (link.isEmpty()||link==null) {
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
	
	//test 
	public void printAll() {
		System.out.println("title:"+title+"\ndes:"+description+"\ndate:"+dateCreated.toString()+"\naut:"+author+"\nchannel"+channel.getName()
		+"\nlink"+link);
	}
//	private String title;
//	private String description;
//	private Date dateCreated;
//	private String author;
//	private RSSChannel channel;
//	private String link;
}
