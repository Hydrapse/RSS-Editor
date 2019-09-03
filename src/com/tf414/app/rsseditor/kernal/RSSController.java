package com.tf414.app.rsseditor.kernal;

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
	
	
}
