package com.tf414.app.rsseditor.data;

import java.sql.Connection;
import java.sql.DriverManager;
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
	}

	public void insertItem(RSSItem item) throws SQLException {

	}

	public void insertChannel(RSSChannel item) throws SQLException {

	}

	public void insertLabel(RSSLabel item) throws SQLException {

	}
	
	public void updateItem(RSSItem item) throws SQLException {

	}

	public void updateChannel(RSSChannel item) throws SQLException {

	}

	public void updateLabel(RSSLabel item) throws SQLException {

	}
	
	public void removeItem(int channelID, String name) throws SQLException {

	}

	public void removeChannel(String channel) throws SQLException {

	}

	public void removeLabel(String label) throws SQLException {

	}

	private void createItemTable() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS item\n" + "(\n" + "label varchar(20),\n" + "channelID integer\n"
				+ ");";
		executeCreateSQL(sql);
	}

	private void createLabelTable() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS label\n" + "(\n" + "label varchar(20),\n" + "channelID integer\n"
				+ ");";
		executeCreateSQL(sql);
	}

	private void createChannelTable() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS channel\n" + "(\n" + "channelID integer,\n" + "title varchar(20),\n"
				+ "description varchar(q)\n" + ");";
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
