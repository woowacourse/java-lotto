package lotto.dao;

import java.sql.Connection;

class DAO {
    private static DatabaseUtil databaseUtil;
    static Connection connection;

    static {
        databaseUtil = DatabaseUtil.getInstance();
        connection = databaseUtil.getConnection();
    }
}
