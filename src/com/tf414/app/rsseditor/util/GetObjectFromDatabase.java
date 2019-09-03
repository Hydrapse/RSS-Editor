package com.tf414.app.rsseditor.util;

import java.sql.SQLException;

import com.tf414.app.rsseditor.data.REDatabase;
import com.tf414.app.rsseditor.data.RSSChannel;

public class GetObjectFromDatabase {
	public static void getChannel(int channelID) {
		
		REDatabase.init();
		REDatabase database = REDatabase.getInstance();
		try {
			database.createChannelTable();
			database.createItemTable();
			database.createLabelTable();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			System.out.println(database.selectAllChannel());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
