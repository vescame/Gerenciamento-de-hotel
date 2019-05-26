package com.fatec.gerenciamentohotel.control.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	private static ConnectionDB instance;
	private Connection conn;
	// alterar a string de connexão !!!!! 
	private String connectionURL = "jdbc:mariadb://localhost:3306/hoteldb?allowMultiQueries=true";
	private String user = "root";
	private String psswrd = "";

	private ConnectionDB() {
		try {
			Class.forName("org.maria.db.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static ConnectionDB getInstance() {
		if (instance == null) {
			instance = new ConnectionDB();
		}
		return instance;
	}
	
	public Connection getConnection() throws SQLException {
		if (conn == null || conn.isClosed()) {
			conn = DriverManager.getConnection(connectionURL, user, psswrd);
		}
		return conn;
	}
}
