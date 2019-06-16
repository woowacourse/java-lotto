package lotto.dao;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBCPDataSource {
    private static BasicDataSource basicDataSource = new BasicDataSource();

    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String DBMS = "mysql";
    private static final String SERVER = "localhost";
    private static final String DATABASE = "lottodb";
    private static final String CONNECTION_PROPERTIES = "?characterEncoding=UTF-8&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";
    private static final String URL = "jdbc:" + DBMS + "://" + SERVER + "/" + DATABASE + CONNECTION_PROPERTIES;
    private static final String USER_NAME = "bmo";
    private static final String PASSWORD = "0000";
    private static final int MIN_IDLE = 20;
    private static final int MAX_IDLE = 30;
    private static final int MAX_OPEN_PREPARED_STATEMENTS = 100;

    static {
        basicDataSource.setDriverClassName(DRIVER_CLASS_NAME);
        basicDataSource.setUrl(URL);
        basicDataSource.setUsername(USER_NAME);
        basicDataSource.setPassword(PASSWORD);
        basicDataSource.setMinIdle(MIN_IDLE);
        basicDataSource.setMaxIdle(MAX_IDLE);
        basicDataSource.setMaxOpenPreparedStatements(MAX_OPEN_PREPARED_STATEMENTS);
    }

    public static Connection getConnection() throws SQLException {
        return basicDataSource.getConnection();
    }
}
