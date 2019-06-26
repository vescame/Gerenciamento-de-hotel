package com.fatec.gerenciamentohotel.control.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectionDB {
	private static ConnectionDB instance;
	private Connection conn;
	private String connectionURL = "jdbc:mariadb://localhost:3306/admin_hotel?allowMultiQueries=true";
	private String user = "admin";
	private String psswrd = "Mysqladmin1*";

	private ConnectionDB() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// e.printStackTrace();
			System.out.println("Erro ao carregar driver mariaDB");
		}
	}

	public static ConnectionDB getInstance() {
		if (instance == null) {
			instance = new ConnectionDB();
		}
		return instance;
	}

	public Connection getConnection() throws SQLException {
		try {
			if (conn == null || conn.isClosed()) {
				conn = DriverManager.getConnection(connectionURL, user, psswrd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"Nao foi possivel conectar ao banco", "Erro ao conectar-se",
					JOptionPane.ERROR_MESSAGE);
		}
		return conn;
	}
}
