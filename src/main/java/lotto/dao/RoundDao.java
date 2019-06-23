package lotto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoundDao {

    private static final String queryForAddRound = "INSERT INTO round () VALUES ()";
    private static final String queryForFindLast = "SELECT * FROM round ORDER BY id DESC LIMIT 1";
    private static final String queryForFindAllRound = "SELECT * FROM round";

    private Connection con = null;

    private RoundDao() {
    }

    private static class RoundDaoHolder {
        private static final RoundDao INSTANCE = new RoundDao();
    }

    public static RoundDao getInstance() {
        return RoundDaoHolder.INSTANCE;
    }

    public void addRound() throws SQLException {
        PreparedStatement pstmt = ConnectionManager.prepareStatement(DBConnection.getConnection(), queryForAddRound);
        pstmt.executeUpdate();
        DBConnection.closeConnection(con);
    }

    public int findlast() throws SQLException {
        PreparedStatement pstmt = ConnectionManager.prepareStatement(DBConnection.getConnection(), queryForFindLast);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("id");
        }
        DBConnection.closeConnection(con);
        return 1;
    }

    public List<Integer> findAll() throws SQLException {
        PreparedStatement pstmt = ConnectionManager.prepareStatement(DBConnection.getConnection(), queryForFindAllRound);
        ResultSet rs = pstmt.executeQuery();
        List<Integer> rounds = new ArrayList<>();
        while (rs.next()) {
            rounds.add(rs.getInt("id"));
        }
        DBConnection.closeConnection(con);
        return rounds;
    }

}
