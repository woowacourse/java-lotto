package com.woowacourse.lotto.persistence;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;

public class DataSourceFactory {
    private static final String DB_HOST = "localhost";
    private static final String DB_NAME = "wtc_lotto_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    private static DataSource dataSource;

    public static DataSource createDataSource() {
        if (dataSource == null) {
            MysqlDataSource ds = new MysqlDataSource();
            ds.setUrl("jdbc:mysql://" + DB_HOST + "/" + DB_NAME + "?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC");
            ds.setUser(DB_USER);
            ds.setPassword(DB_PASSWORD);
            ds.setDatabaseName(DB_NAME);
            dataSource = ds;
        }

        return dataSource;
    }
}
