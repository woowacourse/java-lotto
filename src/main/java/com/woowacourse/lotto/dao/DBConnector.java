package com.woowacourse.lotto.dao;

import java.sql.*;

public class DBConnector {
	private Connection connection;

	public DBConnector() {
		String server = "localhost";
		String database = "woowa";
		String userName = "tiber";
		String password = "tiber123";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			this.connection = DriverManager.getConnection(String.join("\n",
					"jdbc:mysql://"
							+ server + "/" + database
							+ "?serverTimezone=UTC" +
							"&useSSL=false" +
							"&allowPublicKeyRetrieval=true",
					userName, password));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return this.connection;
	}

	public void closeConnection(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			System.err.println("con 오류" + e.getMessage());
		}
	}
}
