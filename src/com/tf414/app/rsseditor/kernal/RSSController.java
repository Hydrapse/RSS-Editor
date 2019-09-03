package com.tf414.app.rsseditor.kernal;

import java.sql.SQLException;

import com.tf414.app.rsseditor.data.REDatabase;
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
	
	
}
