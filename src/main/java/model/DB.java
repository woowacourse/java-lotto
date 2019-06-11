package model;

import java.sql.*;

public class DB {
    private static final String server = "localhost";
    private static final String database = "woowa";
    private static final String userName = "donut";
    private static final String password = "qwer1234";

    private static DB instance = null;

    private Connection con = null;

    public static DB getInstance() {
        if (instance == null) {
            instance = new DB();
        }
        return instance;
    }

    private DB() {}

    public Connection connect() {
        if (this.con == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                this.con = DriverManager.getConnection(
                        "jdbc:mysql://" + server + "/" + database + "?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false",
                        userName,
                        password
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return con;
    }

    public void close() {
        if (this.con != null) {
            try {
                this.con.close();
                this.con = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
