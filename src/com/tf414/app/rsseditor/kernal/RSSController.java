package com.tf414.app.rsseditor.kernal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tf414.app.rsseditor.data.REDatabase;
import com.tf414.app.rsseditor.data.RSSChannel;
import com.tf414.app.rsseditor.data.RSSItem;
import com.tf414.app.rsseditor.data.RSSLabel;

public class RSSController {
	private static RSSController controller;
	private static boolean isInit = false;
	
	public static void init() {
		controller = new RSSController();
		isInit = true;
	}
	
	public static RSSController getInstance() {
		if(!isInit) {
			throw new IllegalAccessError("RSSController should be initialized!");
		}
		return controller;
	}
	
	private RSSController() {
		
	}

	
	public RSSLabel getAllLabel() {
		RSSLabel label = new RSSLabel("ALL");
		try {
			label.setChannelList(REDatabase.getInstance().selectChannelAll());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return label;
	}
	
	public List<RSSLabel> getLabels() {
		try {
			return REDatabase.getInstance().selectLabelAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public RSSChannel addRSSChannel(RSSLabel label, String url) {
		try {
			RSSChannel c = DOMReader.read(url);
			REDatabase.getInstance().insertChannel(c);
			for(RSSItem item: c.getItems()) {
				REDatabase.getInstance().insertItem(item);
			}
			REDatabase.getInstance().insertChannel(c);
			REDatabase.getInstance().insertLabel(label.getId(), label.getName(), c);
			return c;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public RSSChannel addRSSChannel(String url) {
		try {
			RSSChannel c = DOMReader.read(url);
			REDatabase.getInstance().insertChannel(c);
			for(RSSItem item: c.getItems()) {
				REDatabase.getInstance().insertItem(item);
			}
			REDatabase.getInstance().insertChannel(c);
			return c;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void removeRSSChannel(String name) {
		try {
			REDatabase.getInstance().removeChannel(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public RSSChannel getRSSChannel(String name) {
		try {
			return REDatabase.getInstance().selectChannelByChannelID(REDatabase.getInstance().getChannelIDByName(name));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public RSSChannel setLike(String name, boolean isLike) {
		try {
			return REDatabase.getInstance().updateLike(name, isLike);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

