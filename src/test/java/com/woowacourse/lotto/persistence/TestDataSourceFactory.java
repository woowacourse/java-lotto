package com.woowacourse.lotto.persistence;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;

public class TestDataSourceFactory implements AbstractDataSourceFactory {
    private static final String DB_HOST = "localhost";
    private static final String DB_NAME = "wtc_lotto_test_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    public DataSource createDataSource() {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setUrl("jdbc:mysql://" + DB_HOST + "/" + DB_NAME + "?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC");
        ds.setUser(DB_USER);
        ds.setPassword(DB_PASSWORD);
        ds.setDatabaseName(DB_NAME);
        return ds;
    }
}
