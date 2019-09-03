package com.tf414.app.rsseditor.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RSSChannel {
	private int id;
	private String name;
	private String link;
	private String logoPath;
	private String description;
	private String generator;
	private String webMaster;
	private String language;
	private Date lastBuildDate;
	private List<RSSItem> items;
	private boolean isLike;
	
	public RSSChannel(String name) {
		super();
		this.name = name;
		items = new ArrayList<RSSItem>();
	}
	
	public RSSChannel(String name, String link, String logoPath, String description, String generator, String webMaster,
			String  language, Date lastBuildDate) {
		super();
		this.name = name;
		this.link = link;
		this.logoPath = logoPath;
		this.description = description;
		this.generator = generator;
		this.webMaster = webMaster;
		this.language = language;
		this.lastBuildDate = lastBuildDate;
		items = new ArrayList<RSSItem>();
	}
	
	public RSSChannel(int id, String name, String link, String logoPath, String description, String generator, String webMaster,
			String  language, Date lastBuildDate) {
		super();
		this.id = id;
		this.name = name;
		this.link = link;
		this.logoPath = logoPath;
		this.description = description;
		this.generator = generator;
		this.webMaster = webMaster;
		this.language = language;
		this.lastBuildDate = lastBuildDate;
		items = new ArrayList<RSSItem>();
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
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		if (name==null) {
			System.out.println("???"); 
		}
		return name;
	}
	
	public String getLink() {
		if (link==null) {
			return ""; 
		}
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}

	public String getLogoPath() {
		if (logoPath==null) {
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
		if (description==null) {
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
	
	public String getGenerator() {
		if(generator==null) {
			return "";
		}
		return generator;
	}

	public void setGenerator(String generator) {
		this.generator = generator;
	}

	public String getWebMaster() {
		if(webMaster==null) {
			return "";
		}
		return webMaster;
	}

	public void setWebMaster(String webMaster) {
		this.webMaster = webMaster;
	}

	public String getLanguage() {
		if(language == null) {
			return "";
		}
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Date getLastBuildDate() {
		return lastBuildDate;
	}

	public void setLastBuildDate(Date lastBuildDate) {
		this.lastBuildDate = lastBuildDate;
	}
	
	public boolean isLike() {
		return isLike;
	}
	
	public void setLike(boolean isLike) {
		this.isLike = isLike;
	}

	@Override
	public String toString() {
		return "RSSChannel [name=" + name + ", url=" + link + ", logoPath=" + logoPath + ", description=" + description
				+ ", generator=" + generator + ", webMaster=" + webMaster + ", language=" + language
				+ ", lastBuildDate=" + lastBuildDate + ", items=" + items + ", isLike=" + isLike + "]";
	}
	//there some mistake with toString
	
	public void printAllItems() {
		for (RSSItem rssItem : items) {
			rssItem.printAll();
		}
	}
}
