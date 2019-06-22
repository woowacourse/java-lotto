package lotto.dao;

import java.sql.Connection;

abstract class Dao {
    private static DatabaseConnect databaseConnect;
    static Connection connection;

    static {
        databaseConnect = DatabaseConnect.getInstance();
        connection = databaseConnect.getConnection();
    }
}
