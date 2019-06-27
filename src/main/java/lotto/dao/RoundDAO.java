package lotto.dao;

import lotto.database.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoundDAO {
    private static RoundDAO roundDAO = new RoundDAO();

    public static RoundDAO getInstance() {
        return roundDAO;
    }

    public int getRound() throws SQLException {
        String query = "SELECT MAX(round) FROM lotto;";

        try (Connection conn = DBConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                return 1;
            }

            return rs.getInt(1) + 1;
        }
    }
}
