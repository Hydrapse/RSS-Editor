package com.tf414.app.rsseditor.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class REDatabase {
	private static final String HOST = "127.0.0.1";
	private static final int PORT = 3306;
	private static final String DATABASE_NAME = "RSSEditor";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "g123456.0";

	public static boolean isInit = false;
	private static REDatabase instance;

	public static REDatabase getInstance() {
		if (!isInit)
			throw new IllegalAccessError("The database had not already initialization");
		return instance;
	}

	public static void init() {
		try {
			instance = new REDatabase(HOST, PORT, DATABASE_NAME, USERNAME, PASSWORD);
		} catch (Exception e) {
			System.out.println("Occurred an error when initializing the database!");
			e.printStackTrace();
		}
		isInit = true;
	}

	private Connection con = null;

	private void executeCreateSQL(String sql) throws SQLException {
		Statement s = con.createStatement();
		s.execute(sql);
		s.close();
	}
	
	private int executeInsertSQL(String sql) throws SQLException {
		Statement s = con.createStatement();
		int i = s.executeUpdate(sql);
		s.close();
		return i;
	}
	
	private int executeUpdateSQL(String sql) throws SQLException {
		Statement s = con.createStatement();
		int i = s.executeUpdate(sql);
		s.close();
		return i;
	}
	
	private int executeRemoveSQL(String sql) throws SQLException {
		Statement s = con.createStatement();
		int i = s.executeUpdate(sql);
		s.close();
		return i;
	}
	
	private ResultSet executeSelectSQL(String sql) throws SQLException {
		Statement s = con.createStatement();
		ResultSet r = s.executeQuery(sql);
		s.close();
		return r;
	}

	public void insertItem(RSSItem item) throws SQLException {
			String sql="INSERT INTO items VALUES ("
					+ "uuid(),"
					+ item.getTitle()+","
					+ item.getDescription()+","
					+ item.getLink()+","
					+ item.getDateCreated()+","
					+ item.getAuthor()+","
					+ item.hasRead()+","
					+")";
			executeInsertSQL(sql);
	}

	public void insertChannel(RSSChannel channel) throws SQLException {
		String sql="INSERT INTO channnels VALUES ("
				+ "uuid(),"
				+ channel.getTitle()+","
				+ channel.getDescription()+","
				+ channel.getLink()+","
				+ channel.getGenerator()+","
				+ channel.getWebMaster()+","
				+ channel.getLogoPath()+","
				+ channel.getLanguage()+","
				+ channel.getLastBuildDate() + ","
				+ channel.isLink() +","
				+")";
		executeInsertSQL(sql);
	}

	public void insertLabel(RSSLabel label, RSSChannel channel) throws SQLException {
		String select = "SELECT channels * WHERE name='"+channel.getName()+"'";
		
		executeSelectSQL(select);
		
		String sql="INSERT INTO labels VALUES ("
				+ label.getName()+","
				+ channel.getName()
				+")";
		executeInsertSQL(sql);
	}
	
	public void updateItem(RSSItem item) throws SQLException {
			
			String sql = "UPDATE items SET "
					+ "title = '"+item.getTitle()+"',"
					+ "description = '"+item.getDescription()+"',"
					+ "link = '"+item.getLink()+"',"
					+ "pubDate = "+item.getDateCreated()
					+ "author = '"+item.getAuthor()+"',"
					+ "hasRead = "+item.hasRead()
					+ " WHERE channelID='"+item.getChannel()+"'";
			executeUpdateSQL(sql);
			
	}

	public void updateChannel(RSSChannel channel) throws SQLException {
			String sql="UPDATE channels SET "
					+ "description = '"+channel.getDescription()+"',"
					+ "link = '" +channel.getLink()+"',"
					+ "generator = '"+channel.getGenerator()+"',"
					+ "webMaster = '"+channel.getWebMaster()+"',"
					+ "logoPath = '"+channel.getLogoPath()+"',"
					+ "language = '"+channel.getLanguage()+"',"
					+ "lastBuildDate = "+channel.getLastBuildDate()+","
					+ "isLike = "+channel.isLink()+","
					+" WHERE name ='"+channel.getName()+"'";
			executeUpdateSQL(sql);
	}

	public void updateLabel(RSSLabel label) throws SQLException {
		ArrayList<RSSChannel> list=label.getChannelList();
		for(int i=0 ; i<list.size() ; ++i) {
			RSSChannel channel = list.get(i);
			String select = "SELECT channelID FROM channels WHERE name='"+channel.getName()+"'";
			int channelID = executeSelectSQL(select);
			String sql="UPDATE labels SET "
				+ "label ='"+label.getName()+"' "
				+ "WHERE channelID="+channelID;
			executeUpdateSQL(sql);
		}
		
	}
	
	public void removeItem(int channelID, String name) throws SQLException {
		String sql="DELETE FROM items WHERE channelID="+channelID+" AND name='"+name+"'";
		executeRemoveSQL(sql);
	}

	public void removeChannel(String channel) throws SQLException {
		String selectChannelID = "SELECT channelID FROM channels WHERE name='"+channel+"'";
		int channelID=executeSelectSQL(selectChannelID);
		String delItem = "DELETE FROM items WHERE in channelID="+channelID;
		String delLab = "DELETE FROM labels WHERE in channelID="+channelID;
		String delCha="DELETE FROM channels WHERE name='"+channel+"'";
		executeRemoveSQL(delItem);
		executeRemoveSQL(delLab);
		executeRemoveSQL(delCha);
		
	}

	public void removeLabel(String label) throws SQLException {
		String sql = "DELETE FROM labels WHERE label='"+label+"'";
		executeRemoveSQL(sql);
	}

	private void createChannelTable() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS channels\n"
				+ "(\n"
				+ "channelID integer NOT NULL PRIMARY KEY,\n"
				+ "name varchar(50) NOT NULL PRIMARY KEY,\n"
				+ "description varchar(2000),\n"
				+ "link varchar(255),\n"
				+ "generator varchar(255),\n"
				+ "webMaster varchar(255),\n"
				+ "logoPath varchar(255),\n"
				+ "language varchar(10),\n"
				+ "lastBuildDate date,\n"
				+ "isLike tinyint\n"
				+ ");";
		executeCreateSQL(sql);
	}

	private void createLabelTable() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS labels\n"
				+ "(\n"
				+ "label varchar(50) NOT NULL,\n"
				+ "channelID integer NOT NULL FOREIGN KEY REFERENCES channels(channelID)\n"
				+ ");";
		executeCreateSQL(sql);
	}

	private void createItemTable() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS items\n"
				+ "(\n" 
				+ "channelID integer FOREIGN KEY REFERENCES channels(channelID) PRIMARY KEY,\n"
				+ "title varchar(50),\n"
				+ "description varchar(2000),\n"
				+ "link varchar(255),\n"
				+ "pubDate date,\n"
				+ "author varchar(255),\n"
				+ "hasRead tinyint\n"
				+ ");";
		executeCreateSQL(sql);
	}

	public REDatabase(String host, int port, String databaseName, String username, String password) throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + databaseName, username, password);
		System.out.println("Successfully connected to mysql");
	}

	@Override
	public void finalize() {
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("Occurred an error when close the database connection!");
			e.printStackTrace();
		}
	}

}