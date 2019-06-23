package lotto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionManager {

    public static PreparedStatement prepareStatement(Connection con, String quary) throws SQLException {
        return con.prepareStatement(quary);
    }

}
