package lotto.domain.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoundDao {
    private DataSource dataSource;

    public RoundDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int addNextRound() throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            String query = "INSERT INTO round VALUE (?)";
            return executeAddNextRoundQuery(query, con);
        }
    }

    private int executeAddNextRoundQuery(String query, Connection con) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            int addedRound = getMaxRound() + 1;
            pstmt.setInt(1, addedRound);
            pstmt.executeUpdate();
            return addedRound;
        }
    }

    public int getMaxRound() throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            String query = "SELECT max(round) FROM round";
            return executeGetMaxRoundQuery(query, con);
        }
    }

    private int executeGetMaxRoundQuery(String query, Connection con) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            return getResultMaxRound(pstmt);
        }
    }

    private int getResultMaxRound(PreparedStatement pstmt) throws SQLException {
        try (ResultSet rs = pstmt.executeQuery()) {
            return (rs.next() ? rs.getInt("max(round)") : -1);
        }
    }
}
