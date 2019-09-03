package com.tf414.app.rsseditor.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class REDatabase {
	public static void main(String[] args) {
		REDatabase.init();
	}
	
	private static final String HOST = "127.0.0.1";
	private static final int PORT = 3306;
	private static final String DATABASE_NAME = "RSSEditor";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "123456";

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
	
	private RSSItem resultSetToRSSItem(ResultSet rs) throws SQLException {
		int channelID = rs.getInt("channelID");
		String sql = "SELECT * FROM channels WHERE channelID = " + channelID + ";";
		ResultSet rs1 = executeSelectSQL(sql);
		if(rs.getFetchSize() == 0) {
			throw new IllegalArgumentException("No channel use id " + channelID);
		}
		RSSChannel channel = new RSSChannel(rs1.getString("name"), rs1.getString("link"), rs1.getString("logoPath"), rs1.getString("description"), rs1.getString("generator"), rs1.getString("webMaster"),
				rs1.getString("language"), rs1.getDate("lastBuildDate"));
		return new RSSItem(channel, rs.getString("title"), rs.getString("description"), rs.getDate("dateCreated"), rs.getString("author"), rs.getString("link"));
	}
	
	private RSSChannel resultSetToRSSChannel(ResultSet rs) throws SQLException {
		int channelID = rs.getInt("channelID");
		RSSChannel channel = new RSSChannel(rs.getString("name"), rs.getString("link"), rs.getString("logoPath"), rs.getString("description"), rs.getString("generator"), rs.getString("webMaster"),
				rs.getString("language"), rs.getDate("lastBuildDate"));
		 for(RSSItem item: selectItemFromChannel(channel)) {
			 channel.addItem(item);
		 }
		 channel.setId(channelID);
		return channel;
	}
	
	private int getChannelIDByName(String name) throws SQLException {
		String sql = "SELECT channelID FROM channels WHERE name = " + name + ";";
		ResultSet rs = executeSelectSQL(sql);
		return rs.getInt("channelID");
	}
	
	public RSSChannel selectChannelByChannelID(int channelID) throws SQLException {
		String sql = "SELECT * FROM channels WHERE channelID = " + channelID + ";";
		ResultSet rs = executeSelectSQL(sql);
		if(rs.getFetchSize() == 0) {
			throw new IllegalArgumentException("No channel use id " + channelID);
		}
		 RSSChannel channel = new RSSChannel(rs.getString("name"), rs.getString("link"), rs.getString("logoPath"), rs.getString("description"), rs.getString("generator"), rs.getString("webMaster"),
				rs.getString("language"), rs.getDate("lastBuildDate"));
		 channel.setId(channelID);
		 for(RSSItem item: selectItemFromChannel(channel)) {
			 channel.addItem(item);
		 }
		 return channel;
	}
	
	public List<RSSItem> selectItemAll() throws SQLException{
		List<RSSItem> allItems = new ArrayList<RSSItem>();
		String sql = "SELECT * FROM items;";
		ResultSet rs = executeSelectSQL(sql);
		for(int i = 0; i < rs.getFetchSize(); ++i) {
			allItems.add(resultSetToRSSItem(rs));
			rs.next();
		}
		return allItems;
	}
	
	public List<RSSChannel> selectChannelAll() throws SQLException{
		List<RSSChannel> allChannels = new ArrayList<RSSChannel>();
		String sql = "SELECT * FROM channels;";
		ResultSet rs = executeSelectSQL(sql);
		for(int i = 0; i < rs.getFetchSize(); ++i) {
			allChannels.add(resultSetToRSSChannel(rs));
			rs.next();
		}
		return allChannels;
	}
	
	public List<RSSLabel> selectLabelAll() throws SQLException{
		Map<String, RSSLabel> maps = new HashMap<String, RSSLabel>();
		String sql = "SELECT * FROM labels;";
		ResultSet rs = executeSelectSQL(sql);
		for(int i = 0; i < rs.getFetchSize(); ++i) {
			String name = rs.getString("name");
			if(maps.containsKey(name)) {
				maps.get(name).addChannel(selectChannelByChannelID(rs.getInt("channelID")));
			}else {
				maps.put(name, new RSSLabel(name));
			}
			rs.next();
		}
		return new ArrayList<RSSLabel>(maps.values());
	}
	
	public List<RSSItem> selectItemFromChannel(RSSChannel channel) throws SQLException {
		List<RSSItem> items = new ArrayList<RSSItem>();
		String sql = "SELECT * FROM items WHERE channelID=" + channel.getId() + ";";
		ResultSet rs = executeSelectSQL(sql);
		for(int i = 0; i < rs.getFetchSize(); ++i) {
			items.add(resultSetToRSSItem(rs));
			rs.next();
		}
		return items;
	}

	public void insertItem(RSSItem item) throws SQLException {
			int channelID = getChannelIDByName(item.getChannel().getName());
			String sql="INSERT INTO items VALUES ("
					+ channelID +","
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
				+ channel.getName()+","
				+ channel.getDescription()+","
				+ channel.getLink()+","
				+ channel.getGenerator()+","
				+ channel.getWebMaster()+","
				+ channel.getLogoPath()+","
				+ channel.getLanguage()+","
				+ channel.getLastBuildDate() + ","
				+ channel.isLike() +","
				+")";
		executeInsertSQL(sql);
	}

	public void insertLabel(RSSLabel label, RSSChannel channel) throws SQLException {
		int channelID = getChannelIDByName(channel.getName());
		String sql="INSERT INTO labels VALUES ("
				+ label.getName()+","
				+ channelID
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
					+ "isLike = "+channel.isLike()+","
					+" WHERE name ='"+channel.getName()+"'";
			executeUpdateSQL(sql);
	}

	public void updateLabel(RSSLabel label) throws SQLException {
		List<RSSChannel> list=label.getChannelList();
		for(int i=0 ; i<list.size() ; ++i) {
			RSSChannel channel = list.get(i);
			String select = "SELECT channelID FROM channels WHERE name='"+channel.getName()+"'";
			ResultSet rs = executeSelectSQL(select);
			int channelID = rs.getInt(0);
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

	public boolean removeChannel(String channel) throws SQLException {
		String selectChannelID = "SELECT channelID FROM channels WHERE name='"+channel+"'";
		ResultSet rs = executeSelectSQL(selectChannelID);
		if(rs.getRow() == 0) {
			return false;
		}
		int channelID = rs.getInt(0);
		String delItem = "DELETE FROM items WHERE channelID="+channelID;
		String delLab = "DELETE FROM labels WHERE channelID="+channelID;
		String delCha = "DELETE FROM channels WHERE channelID="+channelID;
		executeRemoveSQL(delItem);
		executeRemoveSQL(delLab);
		executeRemoveSQL(delCha);
		return true;
		
	}

	public void removeLabel(String label) throws SQLException {
		String sql = "DELETE FROM labels WHERE label='"+label+"'";
		executeRemoveSQL(sql);
	}

	public List selectLikeChannel() throws SQLException{
		String sql = "SELECT * FROM channels WHERE isLike = 1";
		ResultSet result = executeSelectSQL(sql);
		return resultSetToList(result);
	}
	
	public List selectChannelItems(int channelID) throws SQLException{
		String sql="SELECT * FROM items WHERE channelID="+channelID;
		ResultSet result=executeSelectSQL(sql);
		return resultSetToList(result);
	}
	public List selectHasRead() throws SQLException{
		String sql="SELECT * FROM items WHERE hasRead=1";
		ResultSet result=null;
		result = executeSelectSQL(sql);
		return resultSetToList(result);
	}
	
	public List resultSetToList(ResultSet result) throws SQLException {
		ResultSetMetaData metaData=null;
		int row =0;
		metaData = result.getMetaData();
		row = metaData.getColumnCount();
		
		List list=new ArrayList();
		while (result.next()) {
				Map rowData = new HashMap();
				for (int i = 1; i <= row; i++) {
					rowData.put(metaData.getColumnName(i), result.getObject(i));//获取键名及值
				}
				list.add(rowData);
			}
		return list;
	}
	public List selectAllChannel() throws SQLException {
		String sql = "SELECT * FROM channels";
		ResultSet result = executeSelectSQL(sql);
		return resultSetToList(result);
	}
	
	public List selectItem(int channelID,String title) throws SQLException {
		String sql="SELECT * FROM items WHERE channelID="+channelID+",title='"+title+"'";
		ResultSet result = executeSelectSQL(sql);
		return resultSetToList(result);
	}
	
	public void createChannelTable() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS channels\n"
				+ "(\n"
				+ "channelID integer NOT NULL PRIMARY KEY,\n"
				+ "name varchar(50) NOT NULL,\n"
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

	public void createLabelTable() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS labels\n"
				+ "(\n"
				+ "label varchar(50) NOT NULL,\n"
				+ "channelID integer NOT NULL,\n"
				+ "FOREIGN KEY (channelID) REFERENCES channels(channelID)\n"
				+ ");";
		executeCreateSQL(sql);
	}

	public void createItemTable() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS items\n"
				+ "(\n" 
				+ "channelID integer PRIMARY KEY,\n"
				+ "title varchar(50),\n"
				+ "description varchar(2000),\n"
				+ "link varchar(255),\n"
				+ "pubDate date,\n"
				+ "author varchar(255),\n"
				+ "hasRead tinyint,\n"
				+ "FOREIGN KEY (channelID) REFERENCES channels(channelID)\n"
				+ ");";
		executeCreateSQL(sql);
	}
  
	private REDatabase(String host, int port, String databaseName, String username, String password) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
		con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + databaseName + "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", username, password);
		createChannelTable();
		createLabelTable();
		createItemTable();
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
