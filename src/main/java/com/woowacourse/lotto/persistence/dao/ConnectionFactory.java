package com.woowacourse.lotto.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    private static final String DB_HOST = "localhost";
    private static final String DB_NAME = "wtc_lotto_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    public static Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException("JDBC 드라이버 로드 실패: " + e.getMessage());
        }

        try {
            conn = DriverManager.getConnection(
                "jdbc:mysql://" + DB_HOST + "/" + DB_NAME + "?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                DB_USER, DB_PASSWORD
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("JDBC 연결 실패: " + e.getMessage());
        }
        return conn;
    }
}
