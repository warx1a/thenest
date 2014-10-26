package com.engine.database;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connection {
	
	private String dbms,database,port,username,password,host;
	
	private java.sql.Connection connection = null;
	
	public Connection(String dbms, String database, String port, String username, String password) {
		this.dbms = dbms;
		this.database = database;
		this.port = port;
		this.username = username;
		this.password = password;
	}
	
	public void connect() throws SQLException {
		if(dbms == null) {
			connection = DriverManager.getConnection("jdbc:mysql://" + host.toString() + ":" + port.toString() + "/" + database.toString(),username,password);
		} else {
			connection = DriverManager.getConnection("jdbc:" + dbms.toString() + "://" + host.toString() + ":" + port.toString() + "/" + database.toString(),username,password);
		}	
	}
	
	public boolean execute(final String statement) {
		try {
			final Statement stmt = connection.createStatement();
			stmt.executeQuery(statement);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

}
