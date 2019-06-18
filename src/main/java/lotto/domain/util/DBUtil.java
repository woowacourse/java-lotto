package lotto.domain.util;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;

public class DBUtil {
    private static final String server = "seongmo.synology.me";
    private static final String database = "lotto";
    private static final String userName = "techcourse";
    private static final String password = "8IaSoMTzND7qeNuW";
    private static final String portNumber = ":3307";

    public static DataSource getDataSource() {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setUser(userName);
        ds.setPassword(password);
        ds.setDatabaseName(database);
        ds.setUrl("jdbc:mysql://" + server + portNumber + "/" +
                database + "?useSSL=false&serverTimezone=UTC");

        return ds;
    }
}
