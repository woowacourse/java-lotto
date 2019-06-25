package model;

import java.sql.*;

public class DAO {
    private static final String server = "localhost";
    private static final String database = "woowa";
    private static final String userName = "donut";
    private static final String password = "qwer1234";

    private static DAO instance = null;

    private Connection con = null;

    public static DAO getInstance() {
        if (instance == null) {
            instance = new DAO();
        }
        return instance;
    }

    private DAO() {}

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
